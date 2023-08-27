package com.singularity_code.codebase.pattern.v2

/**
 * Created by: stefanus
 * 27/08/23 09.38
 * Design by: stefanus.ayudha@gmail.com
 */

interface CodebaseVersion

interface CDBS_V1 : CodebaseVersion {
    companion object {
        operator fun invoke() = object : CDBS_V1 {}
    }
}