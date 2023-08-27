package com.singularity_code.codebase

import com.singularity_code.codebase.pattern.v2.CDBS_V1
import com.singularity_code.codebase.util.tactical.this_function_should_always_be_visible_in_ver_latest
import com.singularity_code.codebase.util.tactical.this_function_should_only_be_visible_in_ver_1
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val codebaseV1Scope = CDBS_V1()

        with(codebaseV1Scope) {
            this_function_should_always_be_visible_in_ver_latest()
            this_function_should_only_be_visible_in_ver_1()
        }

    }
}