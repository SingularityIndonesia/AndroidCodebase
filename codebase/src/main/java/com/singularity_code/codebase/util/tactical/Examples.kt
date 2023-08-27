package com.singularity_code.codebase.util.tactical

import com.singularity_code.codebase.pattern.v2.CDBS_V1

/**
 * Created by: stefanus
 * 27/08/23 09.31
 * Design by: stefanus.ayudha@gmail.com
 */

context (CDBS_V1)
fun this_function_should_only_be_visible_in_ver_1(): Boolean {
    return true
}

fun this_function_should_always_be_visible_in_ver_latest(): Boolean {
    return true
}