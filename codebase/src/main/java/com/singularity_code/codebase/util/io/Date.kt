package com.singularity_code.codebase.util.io

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun getStartDayOfCurrentMonth(): Int {
    // Step 1: Create a Calendar instance
    val calendar = Calendar.getInstance()

    // Step 2: Set the calendar's date to the current date (optional)
    // If you don't want to use the current date, you can set the desired date here.
    calendar.time = java.util.Date()

    // Step 3: Set the day of the month to 1 (start of the month)
    calendar.set(Calendar.DAY_OF_MONTH, 1)

    // Step 4: Get the day of the week for the current date (start of the month)
    return calendar.get(Calendar.DAY_OF_WEEK)
}

fun getTotalDaysInCurrentMonth(): Int {
    // Step 1: Create a Calendar instance
    val calendar = Calendar.getInstance()

    // Step 2: Set the calendar's date to the current date (optional)
    // If you don't want to use the current date, you can set the desired date here.
    calendar.time = java.util.Date()

    // Step 3: Get the maximum day of the month (total days in the month)
    return calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
}

fun getTotalDaysInPreviousMonth(): Int {
    // Step 1: Create a Calendar instance
    val calendar = Calendar.getInstance()

    // Step 2: Set the calendar's date to the current date (optional)
    // If you don't want to use the current date, you can set the desired date here.
    calendar.time = java.util.Date()

    // Step 3: Subtract one month to move to the previous month
    calendar.add(Calendar.MONTH, -1)

    // Step 4: Get the maximum day of the previous month (total days in the previous month)
    return calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
}

fun getCurrentMonth(): Int {
    // Step 1: Create a Calendar instance
    val calendar = Calendar.getInstance()

    // Step 2: Set the calendar's date to the current date (optional)
    // If you don't want to use the current date, you can set the desired date here.
    calendar.time = java.util.Date()

    // Step 3: Get the current month
    return calendar.get(Calendar.MONTH) + 1 // adding 1 because month are based on 0
}

fun getCurrentMonthName(): String {
    // Step 1: Create a Calendar instance
    val calendar = Calendar.getInstance()

    // Step 2: Set the calendar's date to the current date (optional)
    // If you don't want to use the current date, you can set the desired date here.
    calendar.time = java.util.Date()

    // Step 3: Format the date as the month name
    val sdf = SimpleDateFormat("MMMM", Locale.getDefault())
    return sdf.format(calendar.time)
}

fun getCurrentYear(): Int {
    // Step 1: Create a Calendar instance
    val calendar = Calendar.getInstance()

    // Step 2: Set the calendar's date to the current date (optional)
    // If you don't want to use the current date, you can set the desired date here.
    calendar.time = java.util.Date()

    // Step 3: Format the date as the year
    val sdf = SimpleDateFormat("yyyy", Locale.getDefault())
    val formattedDate = sdf.format(calendar.time)

    // Step 4: Convert the formatted date to an integer (year)
    return formattedDate.toInt()
}

fun getTodayDate(): Int {
    // Step 1: Create a Calendar instance
    val calendar = Calendar.getInstance()

    // Step 2: Set the calendar's date to the current date (optional)
    // If you don't want to use the current date, you can set the desired date here.
    calendar.time = java.util.Date()

    // Step 3: Get the day of the month (current day's date)
    return calendar.get(Calendar.DAY_OF_MONTH)
}

fun getCurrentTime(
    pattern: String = "dd-MM-yyyy HH:mm"
): String {
    // Step 1: Create a Calendar instance
    val calendar = Calendar.getInstance()

    // Step 2: Set the calendar's date to the current date (optional)
    // If you don't want to use the current date, you can set the desired date here.
    calendar.time = java.util.Date()

    // Step 3: Get the current time in the pattern "hh:mm"
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    return sdf.format(calendar.time)
}

fun String.getDayDate(pattern: String = "yyyy-MM-dd"): Int {
    val formatter = SimpleDateFormat(pattern)
    val date = formatter.parse(this)
    val calendar = java.util.Calendar.getInstance()
    calendar.time = date
    return calendar.get(java.util.Calendar.DAY_OF_MONTH)
}

fun String.getMonth(pattern: String = "yyyy-MM-dd"): Int {
    val formatter = SimpleDateFormat(pattern)
    val date = formatter.parse(this)
    val calendar = java.util.Calendar.getInstance()
    calendar.time = date
    return calendar.get(java.util.Calendar.MONTH) + 1 // Months are 0-based, so add 1.
}

fun Long.dateString(pattern: String = "dd-MM-yyyy"): String {
    val formatter = SimpleDateFormat(pattern, Locale.getDefault())
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    return formatter.format(calendar.time)
}