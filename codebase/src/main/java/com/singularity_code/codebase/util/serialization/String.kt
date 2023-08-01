package com.singularity_code.codebase.util.serialization

fun String.capitalizeEachWord(): String {
    val words = this.split(" ")
    val capitalizedWords = words.map {
        it.lowercase().replaceFirstChar { char ->
            char.uppercase()
        }
    }
    return capitalizedWords.joinToString(" ")
}