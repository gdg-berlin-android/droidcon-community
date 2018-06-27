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

class GameOfLifeEngine(val width: Int, val height: Int) {

    fun tick(input: Grid) : Grid {

        val output = Grid(width, height)

        for (row in 0..input.value.size) {
            for (column in 0..input.value.get(row).size)
                calculateCell(input, output, row, column)
        }

        return output
    }

    private fun calculateCell(input: Grid, output: Grid, i: Int, j: Int) {
        if (input.value[i][j] == 0) {
            if (getAliveAround(input, output, i, j) > 3) {
                output.value[i][j] = 1
            }
        } else {
            if (getAliveAround(input, output, i, j) < 2) {
                output.value[i][j] = 0
            } else if (getAliveAround(input, output, i, j) > 3) {
                output.value[i][j] = 0
            }
        }
    }

    private fun getAliveAround(input: Grid, output: Grid, i: Int, j: Int): Int {
        var outputAlive = 0

        if (input.value[i][j - 1] == 1) {
            outputAlive++
        } else if (input.value[i - 1][j - 1] == 1) {
            outputAlive++
        } else if (input.value[i + 1][j + 1] == 1) {
            outputAlive++
        } else if (input.value[i + 1][j - 1] == 1) {
            outputAlive++
        } else if (input.value[i][j - 1] == 1) {
            outputAlive++
        } else if (input.value[i][j - 1] == 1) {
            outputAlive++
        } else if (input.value[i][j - 1] == 1) {
            outputAlive++
        }
        return outputAlive
    }


    data class Grid(val width: Int, val height: Int) {
        var value: ArrayList<Array<Int>> = ArrayList()

        init {
            for (i in 0..height) {
                val row = emptyArray<Int>()
                for (j in 0..width) {
                    row[j] = 0
                }
                value[i] = row
            }
        }
    }
}