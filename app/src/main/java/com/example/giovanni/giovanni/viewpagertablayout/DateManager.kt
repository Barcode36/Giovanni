package com.example.giovanni.giovanni.viewpagertablayout

import java.text.SimpleDateFormat
import java.util.*

class DateManager {

    companion object {
        var BASE_FORMAT: String = "E MMM dd HH:mm:ss z yyyy"
        var REQUEST_DATE_FORMAT: String = "yyyy-MM-dd HH:mm:ss"

        fun getStrDateTitle(start_date: String, end_date: String): StringBuilder {
            var startDate = SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.UK).parse(start_date)
            var endDate = SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.UK).parse(end_date)

            return StringBuilder()
                    .append(SimpleDateFormat("dd MMMM yyyy", Locale.ITALY).format(startDate))
                    .append(" | ")
                    .append(SimpleDateFormat("HH:mm", Locale.ITALY).format(startDate))
                    .append(" - ")
                    .append(SimpleDateFormat("HH:mm", Locale.ITALY).format(endDate))
        }

        //example: 13:00 - 15:30
        fun getMeetingHour(start_date: String, end_date: String): StringBuilder {
            var startDate = SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.UK).parse(start_date)
            var endDate = SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.UK).parse(end_date)

            return StringBuilder()
                    .append(SimpleDateFormat("HH:mm", Locale.ITALY).format(startDate))
                    .append(" - ")
                    .append(SimpleDateFormat("HH:mm", Locale.ITALY).format(endDate))
        }
    }

    private var _str_date: String = ""
    private var _date: Date? = null

    constructor(date: Date) {
        var sdf = SimpleDateFormat(BASE_FORMAT, Locale.UK)
        sdf.timeZone = TimeZone.getDefault()
        _str_date = sdf.format(date)
        _date = date
    }

    constructor(millis: Long) {
        var d: Date = Date(millis)
        var sdf = SimpleDateFormat(BASE_FORMAT, Locale.UK)
        sdf.timeZone = TimeZone.getDefault()
        _str_date = sdf.format(d)
        _date = d
    }

    constructor(dateStr: String) {
        _str_date = dateStr
        var sdf = SimpleDateFormat(BASE_FORMAT, Locale.UK)
        sdf.timeZone = TimeZone.getDefault()
        _date = sdf.parse(_str_date)
    }

    // Formato Data delle response del server.
    public fun getServerDateFormat(): String {
        return _str_date
    }

    // Formato Data da usare nelle request YYYY-MM-dd HH:mm:ss
    public fun getRequestDateFormat(): String {
        var sdf = SimpleDateFormat(BASE_FORMAT, Locale.UK)
        sdf.timeZone = TimeZone.getDefault()

        var sdfTo = SimpleDateFormat(REQUEST_DATE_FORMAT, Locale.UK)
        sdfTo.timeZone = TimeZone.getDefault()

        var date_from_formatted = sdf.parse(_str_date)
        var date_to_formatted = StringBuilder().append(sdfTo.format(date_from_formatted)).toString()
        return date_to_formatted
    }

//    fun getFormatDate(toFormat: String): String {
//        val date_formatted = SimpleDateFormat(BASE_FORMAT, Locale.UK).parse(_str_date)
//        val retValue = StringBuilder().append(SimpleDateFormat(toFormat, Locale.UK).format(date_formatted)).toString()
//        return retValue
//    }

    // Return dd MMMM yyyy
    fun getDatePickerFormatDate(): StringBuilder {
        var sdf = SimpleDateFormat(BASE_FORMAT, Locale.UK)
        sdf.timeZone = TimeZone.getDefault()
        var formattedDate = sdf.parse(_str_date)
        return StringBuilder().append(SimpleDateFormat("dd MMMM yyyy", Locale.ITALY).format(formattedDate))
    }

    // Return HH:mm
    fun getDatePickerFormatTime(): StringBuilder {
        var sdf = SimpleDateFormat(BASE_FORMAT, Locale.UK)
        sdf.timeZone = TimeZone.getDefault()
        var formattedHour = sdf.parse(_str_date)
        return StringBuilder().append(SimpleDateFormat("HH:mm", Locale.ITALY).format(formattedHour))
    }

    fun getDatePickerHour(): Int {
        var sdf = SimpleDateFormat(BASE_FORMAT, Locale.UK)
        sdf.timeZone = TimeZone.getDefault()
        var formattedHour = sdf.parse(_str_date)
        return SimpleDateFormat("HH", Locale.UK).format(formattedHour).toInt()
    }

    fun getDatePickerMinute(): Int {
        var sdf = SimpleDateFormat(BASE_FORMAT, Locale.UK)
        sdf.timeZone = TimeZone.getDefault()
        var formattedHour = sdf.parse(_str_date)
        return SimpleDateFormat("mm", Locale.UK).format(formattedHour).toInt()
    }

    fun setHourDate(hourOfDay: Int, minute: Int) {
        var sdf = SimpleDateFormat(BASE_FORMAT, Locale.UK)
        sdf.timeZone = TimeZone.getDefault()
        var formattedHour = sdf.parse(_str_date)
        var baseFormat = BASE_FORMAT;
        baseFormat = baseFormat.replace("HH", hourOfDay.toString())
        baseFormat = baseFormat.replace("mm", minute.toString())
        _str_date = SimpleDateFormat(baseFormat, Locale.UK).format(formattedHour)
        _date = sdf.parse(_str_date)
    }

    fun setDateFromPicker(year: Int, month: Int, dayOfMonth: Int) {
        var c: Calendar = Calendar.getInstance()
        c.set(Calendar.MONTH, month)
        c.set(Calendar.YEAR, year)
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        c.set(Calendar.HOUR_OF_DAY, getDatePickerHour())
        c.set(Calendar.MINUTE, getDatePickerMinute())
        var sdf = SimpleDateFormat(BASE_FORMAT, Locale.UK)
        sdf.timeZone = TimeZone.getDefault()
        _str_date = sdf.format(c.time)
        _date = c.time
        //_str_date = SimpleDateFormat(BASE_FORMAT, Locale.UK).parse(formattedHour)
    }

    fun getDate(): Date {
        return this._date!!
    }
}