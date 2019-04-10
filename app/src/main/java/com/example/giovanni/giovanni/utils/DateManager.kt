package com.example.giovanni.giovanni.utils

import java.text.SimpleDateFormat
import java.util.*

class DateManager {

    companion object {

        var BASE_FORMAT: String = "E MMM dd HH:mm:ss z yyyy"
        var REQUEST_DATE_FORMAT: String = "yyyy-MM-dd HH:mm:ss"

        fun getStrDateTitle(start_date: String, end_date: String): StringBuilder {
            val startDate = SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.UK).parse(start_date)
            val endDate = SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.UK).parse(end_date)

            return StringBuilder()
                    .append(SimpleDateFormat("dd MMMM yyyy", Locale.ITALY).format(startDate))
                    .append(" | ")
                    .append(SimpleDateFormat("HH:mm", Locale.ITALY).format(startDate))
                    .append(" - ")
                    .append(SimpleDateFormat("HH:mm", Locale.ITALY).format(endDate))
        }

        // Example: 13:00 - 15:30
        fun getMeetingHour(startDate: String, endDate: String): StringBuilder {
            val dataInizio = SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.UK).parse(startDate)
            val dataFine = SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.UK).parse(endDate)

            return StringBuilder()
                    .append(SimpleDateFormat("HH:mm", Locale.ITALY).format(dataInizio))
                    .append(" - ")
                    .append(SimpleDateFormat("HH:mm", Locale.ITALY).format(dataFine))
        }
    }

    private var dataString: String = ""
    private var data: Date? = null

    constructor(date: Date) {
        val sdf = SimpleDateFormat(BASE_FORMAT, Locale.UK)
        sdf.timeZone = TimeZone.getDefault()
        dataString = sdf.format(date)
        data = date
    }

    constructor(millis: Long) {
        val date = Date(millis)
        val sdf = SimpleDateFormat(BASE_FORMAT, Locale.UK)
        sdf.timeZone = TimeZone.getDefault()
        dataString = sdf.format(date)
        data = date
    }

    constructor(dateString: String) {
        dataString = dateString
        val sdf = SimpleDateFormat(BASE_FORMAT, Locale.UK)
        sdf.timeZone = TimeZone.getDefault()
        data = sdf.parse(dataString)
    }

    // Formato Data delle response del server.
    fun getServerDateFormat(): String {
        return dataString
    }

    // Formato Data da usare nelle request YYYY-MM-dd HH:mm:ss
    fun getRequestDateFormat(): String {
        val sdf = SimpleDateFormat(BASE_FORMAT, Locale.UK)
        sdf.timeZone = TimeZone.getDefault()

        val sdfTo = SimpleDateFormat(REQUEST_DATE_FORMAT, Locale.UK)
        sdfTo.timeZone = TimeZone.getDefault()

        val dateFromFormatted = sdf.parse(dataString)
        val dateToFormatted = StringBuilder().append(sdfTo.format(dateFromFormatted)).toString()
        return dateToFormatted
    }

//    fun getFormatDate(toFormat: String): String {
//        val date_formatted = SimpleDateFormat(BASE_FORMAT, Locale.UK).parse(dataString)
//        val retValue = StringBuilder().append(SimpleDateFormat(toFormat, Locale.UK).format(date_formatted)).toString()
//        return retValue
//    }

    // Return dd MMMM yyyy
    fun getDatePickerFormatDate(): StringBuilder {
        val sdf = SimpleDateFormat(BASE_FORMAT, Locale.UK)
        sdf.timeZone = TimeZone.getDefault()
        val formattedDate = sdf.parse(dataString)
        return StringBuilder().append(SimpleDateFormat("dd MMMM yyyy", Locale.ITALY).format(formattedDate))
    }

    // Return HH:mm
    fun getDatePickerFormatTime(): StringBuilder {
        val sdf = SimpleDateFormat(BASE_FORMAT, Locale.UK)
        sdf.timeZone = TimeZone.getDefault()
        val formattedHour = sdf.parse(dataString)
        return StringBuilder().append(SimpleDateFormat("HH:mm", Locale.ITALY).format(formattedHour))
    }

    fun getDatePickerHour(): Int {
        val sdf = SimpleDateFormat(BASE_FORMAT, Locale.UK)
        sdf.timeZone = TimeZone.getDefault()
        val formattedHour = sdf.parse(dataString)
        return SimpleDateFormat("HH", Locale.UK).format(formattedHour).toInt()
    }

    fun getDatePickerMinute(): Int {
        val sdf = SimpleDateFormat(BASE_FORMAT, Locale.UK)
        sdf.timeZone = TimeZone.getDefault()
        val formattedHour = sdf.parse(dataString)
        return SimpleDateFormat("mm", Locale.UK).format(formattedHour).toInt()
    }

    fun setHourDate(hourOfDay: Int, minute: Int) {
        val sdf = SimpleDateFormat(BASE_FORMAT, Locale.UK)
        sdf.timeZone = TimeZone.getDefault()
        val formattedHour = sdf.parse(dataString)
        var baseFormat = BASE_FORMAT;
        baseFormat = baseFormat.replace("HH", hourOfDay.toString())
        baseFormat = baseFormat.replace("mm", minute.toString())
        dataString = SimpleDateFormat(baseFormat, Locale.UK).format(formattedHour)
        data = sdf.parse(dataString)
    }

    fun setDateFromPicker(year: Int, month: Int, dayOfMonth: Int) {
        val calendar: Calendar = Calendar.getInstance()
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        calendar.set(Calendar.HOUR_OF_DAY, getDatePickerHour())
        calendar.set(Calendar.MINUTE, getDatePickerMinute())
        val sdf = SimpleDateFormat(BASE_FORMAT, Locale.UK)
        sdf.timeZone = TimeZone.getDefault()
        dataString = sdf.format(calendar.time)
        data = calendar.time
        // dataString = SimpleDateFormat(BASE_FORMAT, Locale.UK).parse(formattedHour)
    }

    fun getDate(): Date {
        return this.data!!
    }
}