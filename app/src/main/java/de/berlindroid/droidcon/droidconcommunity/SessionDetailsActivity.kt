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

package de.berlindroid.droidcon.droidconcommunity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SessionDetailsActivity : AppCompatActivity() {

    companion object {
        val EXTRA_SESSION = "sessionExtra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_session_details)

        val session = intent.getSerializableExtra(EXTRA_SESSION) as Session

        fillUI(session)
    }

    private fun fillUI(session: Session) {
        TODO("fill ui with data")
    }

}
