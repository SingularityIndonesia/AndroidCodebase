/**
 * Design and developed by : St. Ayudha Junior.
 * [mail](stefanus.ayudha@gmail.com)
 * [github](https://github.com/stefanusayudha)
 */

package com.singularity_code.codebase.util

@Deprecated("use NOT_EMPTY_PATTERN_INSTEAD")
val NOT_EMPTY get() = Regex("^(?!\\s*\$).+")
val NOT_EMPTY_PATTERN get() = Regex("^(?!\\s*\$).+")
val EMAIL_PATTERN get() = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$")