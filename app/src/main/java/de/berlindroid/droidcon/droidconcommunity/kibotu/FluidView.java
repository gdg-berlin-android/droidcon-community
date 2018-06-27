/*
 * Copyright 2018 GDG Community
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package de.berlindroid.droidcon.droidconcommunity.kibotu;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;


import androidx.core.content.ContextCompat;
import de.berlindroid.droidcon.droidconcommunity.R;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.HONEYCOMB;
import static android.os.Build.VERSION_CODES.JELLY_BEAN_MR2;
import static androidx.core.math.MathUtils.clamp;

/**
 * Created by <a href="https://about.me/janrabe">Jan Rabe</a>.
 */
public class FluidView extends View {
    private static final float MIN_FILL_LEVEL = 0.125f;
    private static final float MAX_FILL_LEVEL = 0.80f;

    private static final int FRAGMENT_SIZE = 70;
    private static final float EXCITE_SPEED = 1f;
    private static final float CALM_SPEED = 0.3f;

    private final Interpolator mInterpolator = new DecelerateInterpolator(0.5f);

    private long mLastDrawTime;
    private Path mMaskPath;
    private float mTargetIntensity = 0f;
    private float mShakeIntensity = 0f;
    private float mFillLevel = 1f;

    private Wave[] mWaves;

    final ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    public FluidView(Context context) {
        super(context);
        init(context);
    }

    public FluidView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FluidView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        if (isInEditMode())
            return;

        if (SDK_INT >= HONEYCOMB && SDK_INT < JELLY_BEAN_MR2)
            setLayerType(LAYER_TYPE_SOFTWARE, null);



        mWaves = new Wave[]{
                new Wave(ContextCompat.getColor(context, R.color.fluidBackWaveStart), ContextCompat.getColor(context, R.color.fluidBackWaveEnd)) {
                    @Override
                    public void update(Canvas canvas, float deltaTime, float factor) {
                        waveLength = 2f + 2f * factor;
                        yOffset = 30f * factor;
                        amplitude = 5f + 30f * factor;
                        speed = canvas.getWidth() * 3;
                        xOffset = -permXOffset;
                    }
                },
                new Wave(ContextCompat.getColor(context, R.color.fluidFrontWaveStart), ContextCompat.getColor(context, R.color.fluidFrontWaveEnd)) {
                    @Override
                    public void update(Canvas canvas, float deltaTime, float factor) {
                        waveLength = 2.5f + 2f * factor;
                        yOffset = 30f * factor;
                        amplitude = 5f + 25f * factor;
                        speed = canvas.getWidth() * 4;
                        xOffset = permXOffset - canvas.getWidth();
                    }
                }
        };
    }

    public float getFillLevel() {
        return mFillLevel;
    }

    /**
     * Set Fill level
     *
     * @param fillLevel from 0 to 1
     */
    public void setFillLevel(float fillLevel) {
        this.mFillLevel = clamp(fillLevel, 0, 1);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (isInEditMode())
            return;

        canvas.save();

        updateMask(canvas);

        float deltaTime = (float) (System.currentTimeMillis() - mLastDrawTime) / 1000f;
        mLastDrawTime = System.currentTimeMillis();

        float factor = 1f - mInterpolator.getInterpolation(1f - mShakeIntensity);

        for (Wave wave : mWaves) {
            wave.draw(canvas, deltaTime, factor);
        }

        canvas.restore();

        if (mTargetIntensity - mShakeIntensity > 0.02) {
            mShakeIntensity = Math.min(mTargetIntensity, mShakeIntensity + EXCITE_SPEED * deltaTime);
            invalidate();
        } else if (mShakeIntensity > 0) {
            mShakeIntensity = Math.max(0, mShakeIntensity - CALM_SPEED * deltaTime);
            mTargetIntensity = mShakeIntensity;
            invalidate();
        }
    }

    private void updateMask(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        // Create mask if necessary
        if (mMaskPath == null) {
            float radius = getContext().getResources().getDimension(R.dimen.fluidRadius);

            mMaskPath = new Path();
            mMaskPath.addRoundRect(new RectF(0, 0, width, height), new float[]{radius, radius, radius, radius, radius, radius, radius, radius}, Path.Direction.CW);
        }

        // Apply mask
        try {
            canvas.clipPath(mMaskPath);
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        super.onSizeChanged(width, height, oldw, oldh);

        if (isInEditMode())
            return;

        mMaskPath = null;

        for (Wave wave : mWaves) {
            wave.invalidate();
        }

        invalidate();
    }

    /**
     * Increase shake intensity
     *
     * @param intensity from 0 to 1
     */
    public void onShake(float intensity) {
        if (intensity > mTargetIntensity) {
            mLastDrawTime = System.currentTimeMillis();
            mTargetIntensity = clamp(intensity, 0, 1);
            invalidate();
        }
    }

    private abstract class Wave {
        private final Paint mPaint = new Paint();
        private Path mPath = new Path();

        private LinearGradient[] mGradients;

        private final int mStartColor;
        private final int mEndColor;

        protected float xOffset;
        protected float yOffset;
        protected float waveLength;
        protected float amplitude;
        protected float speed;

        protected float permXOffset;

        public Wave(int startColor, int endColor) {
            mStartColor = startColor;
            mEndColor = endColor;
            mPaint.setAntiAlias(true);
        }

        protected abstract void update(Canvas canvas, float deltaTime, float factor);

        public final void invalidate() {
            mGradients = null;
        }

        public final void draw(Canvas canvas, float deltaTime, float factor) {
            int width = canvas.getWidth();
            int height = canvas.getHeight();

            update(canvas, deltaTime, factor);
            permXOffset = (float) ((permXOffset + deltaTime * factor * speed) % (width * 2 * Math.PI / waveLength));
            float correctedFillLevel = ((MAX_FILL_LEVEL - MIN_FILL_LEVEL) * mFillLevel) + MIN_FILL_LEVEL;
            float yOffset = this.yOffset + correctedFillLevel * height - 10f;

            //mPaint.setColor(color);

            int lastX = -1;
            float lastY = -1;

            int fragmentCount = width / FRAGMENT_SIZE + 2;
            if (mGradients == null || mGradients.length != fragmentCount) {
                mGradients = new LinearGradient[fragmentCount];
            }

            // Draw sine wave
            int fragmentIndex = 0;
            for (int x = 0; x < width + FRAGMENT_SIZE; x += FRAGMENT_SIZE) {
                float y = ((float) Math.sin((x - xOffset) / width * waveLength) + 0.5f - 2 * mFillLevel) * amplitude;

                if (lastX >= 0) {
                    mPath.reset();
                    mPath.moveTo(lastX, height - lastY - yOffset);
                    mPath.lineTo(x, height - y - yOffset);
                    mPath.lineTo(x, height);
                    mPath.lineTo(lastX, height);
                    mPath.close();

                    LinearGradient gradient = mGradients[fragmentIndex];
                    if (gradient == null) {
                        int localStartColor = (int) argbEvaluator.evaluate((float) lastX / width, mStartColor, mEndColor);
                        int localEndColor = (int) argbEvaluator.evaluate((float) x / width, mStartColor, mEndColor);
                        gradient = new LinearGradient(lastX, 0, x, 0, localStartColor, localEndColor, Shader.TileMode.CLAMP);
                        mGradients[fragmentIndex] = gradient;
                    }

                    mPaint.setShader(gradient);

                    canvas.drawPath(mPath, mPaint);
                }

                lastX = x;
                lastY = y;
                fragmentIndex++;
            }
        }
    }
}
