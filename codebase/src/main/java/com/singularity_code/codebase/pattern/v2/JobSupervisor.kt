package com.singularity_code.codebase.pattern.v2

import kotlin.coroutines.CoroutineContext

/**
 * Created by: stefanus
 * 27/08/23 08.58
 * Design by: stefanus.ayudha@gmail.com
 */

interface JobSupervisor {
    val superVisorContext: CoroutineContext
}