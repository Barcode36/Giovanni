package com.example.giovanni.giovanni.viewpagertablayout

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import com.example.giovanni.giovanni.R
import com.example.giovanni.giovanni.utils.BaseFragment
import java.text.SimpleDateFormat
import java.util.*

class FragmentPickerKotlin : BaseFragment(), DatePickerDialog.OnDateSetListener {

    var meetingDate_start: DateManager? = null
    var meetingDate_end: DateManager? = null

    private var data_meeting_default: TextView? = null
    private var ora_inizio_meeting_default: TextView? = null
    private var ora_fine_meeting_default: TextView? = null

    private var ora_inizio_meeting_request: TextView? = null
    private var ora_fine_meeting_request: TextView? = null
    private var data_inizio_fine_request: TextView? = null
    private var ora_inizio_fine_request: TextView? = null

    private var ora_inizio_meeting_response: TextView? = null
    private var ora_fine_meeting_response: TextView? = null
    private var data_inizio_fine_response: TextView? = null
    private var ora_inizio_fine_response: TextView? = null

    private var data_meeting: TextView? = null
    private var ora_inizio_meeting: TextView? = null
    private var ora_fine_meeting: TextView? = null

    private var calendar: Calendar? = null
    private var datePickerDialog: DatePickerDialog? = null
    private var pickerOra: String? = null
    private var pickerData: String? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_picker_kotlin, container, false)

        data_meeting_default = view.findViewById(R.id.data_meeting_default)
        ora_inizio_meeting_default = view.findViewById(R.id.ora_inizio_meeting_default)
        ora_fine_meeting_default = view.findViewById(R.id.ora_fine_meeting_default)

        ora_inizio_meeting_request = view.findViewById(R.id.ora_inizio_meeting_request)
        ora_fine_meeting_request = view.findViewById(R.id.ora_fine_meeting_request)
        data_inizio_fine_request = view.findViewById(R.id.data_inizio_fine_request)
        ora_inizio_fine_request = view.findViewById(R.id.ora_inizio_fine_request)

        ora_inizio_meeting_response = view.findViewById(R.id.ora_inizio_meeting_response)
        ora_fine_meeting_response = view.findViewById(R.id.ora_fine_meeting_response)
        data_inizio_fine_response = view.findViewById(R.id.data_inizio_fine_response)
        ora_inizio_fine_response = view.findViewById(R.id.ora_inizio_fine_response)

        data_meeting = view.findViewById(R.id.data_meeting)
        ora_inizio_meeting = view.findViewById(R.id.ora_inizio_meeting)
        ora_fine_meeting = view.findViewById(R.id.ora_fine_meeting)

        // set default hour data
        meetingDate_start = DateManager(Date())
        meetingDate_end = DateManager(Date().time + (60 * 60 * 1000))
        data_meeting_default?.text = meetingDate_start?.getDatePickerFormatDate()
        ora_inizio_meeting_default?.text = meetingDate_start?.getDatePickerFormatTime()
        ora_fine_meeting_default?.text = meetingDate_end?.getDatePickerFormatTime()

        ora_inizio_meeting_request?.text = meetingDate_start?.getRequestDateFormat()
        ora_fine_meeting_request?.text = meetingDate_end?.getRequestDateFormat()
        data_inizio_fine_request?.text = DateManager.getStrDateTitle(meetingDate_start!!.getServerDateFormat(), meetingDate_end!!.getServerDateFormat())
        ora_inizio_fine_request?.text = DateManager.getMeetingHour(meetingDate_start!!.getServerDateFormat(), meetingDate_end!!.getServerDateFormat())

        val dataInizio = "25/12/2018 09:30:00"
        val dataFine = "25/12/2018 11:45:00"
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        val dateInizio = dateFormat.parse(dataInizio)
        val dateFine = dateFormat.parse(dataFine)
        meetingDate_start = DateManager(dateInizio)
        meetingDate_end = DateManager(dateFine)

        ora_inizio_meeting_response?.text = meetingDate_start?.getRequestDateFormat()
        ora_fine_meeting_response?.text = meetingDate_end?.getRequestDateFormat()
        data_inizio_fine_response?.text = DateManager.getStrDateTitle(meetingDate_start!!.getServerDateFormat(), meetingDate_end!!.getServerDateFormat())
        ora_inizio_fine_response?.text = DateManager.getMeetingHour(meetingDate_start!!.getServerDateFormat(), meetingDate_end!!.getServerDateFormat())

        calendar = Calendar.getInstance()
        datePickerDialog = DatePickerDialog(context, R.style.PickerDialogTheme, this, calendar?.get(Calendar.YEAR)!!, calendar?.get(Calendar.MONTH)!!, calendar?.get(Calendar.DAY_OF_MONTH)!!)
        data_meeting?.setOnClickListener(buttonDataCalendarClickListener)

        ora_inizio_meeting?.setOnClickListener(oraInizioCalendarClickListener) // Riprendere dalla riga 269 di W3AppDipendenti
        ora_fine_meeting?.setOnClickListener(oraFineCalendarClickListener)

        multiSwipeRefreshLayout = view.findViewById(R.id.multi_swipe_refresh_layout)
        multiSwipeRefreshLayout?.setSwipeableChildren(R.id.linear_layout_child)
        multiSwipeRefreshLayout?.setOnRefreshListener {

            meetingDate_start = DateManager(Date())
            meetingDate_end = DateManager(Date().time + (60 * 60 * 1000))
            data_meeting_default?.text = meetingDate_start?.getDatePickerFormatDate()
            ora_inizio_meeting_default?.text = meetingDate_start?.getDatePickerFormatTime()
            ora_fine_meeting_default?.text = meetingDate_end?.getDatePickerFormatTime()

            ora_inizio_meeting_request?.text = meetingDate_start?.getRequestDateFormat()
            ora_fine_meeting_request?.text = meetingDate_end?.getRequestDateFormat()
            data_inizio_fine_request?.text = DateManager.getStrDateTitle(meetingDate_start!!.getServerDateFormat(), meetingDate_end!!.getServerDateFormat())
            ora_inizio_fine_request?.text = DateManager.getMeetingHour(meetingDate_start!!.getServerDateFormat(), meetingDate_end!!.getServerDateFormat())

            stopSwipeRefresh()
        }

        /*

        // ---------- //
        // -- JAVA -- //
        // ---------- //

        multiSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
        */

        return view
    }

    private val buttonDataCalendarClickListener = View.OnClickListener {
        datePickerDialog!!.datePicker.minDate = calendar!!.timeInMillis
        datePickerDialog!!.show()
    }

    override fun onDateSet(datePicker: DatePicker, year: Int, month: Int, day: Int) {
        // "dd/MM/yyyy"
        pickerData = String.format("%02d/%02d/%04d", day, month + 1, year)
        data_meeting!!.text = pickerData
    }

    private val oraInizioCalendarClickListener = View.OnClickListener {
        calendar = Calendar.getInstance()
        val timePickerDialog = TimePickerDialog(context, R.style.PickerDialogTheme, { view, hourOfDay, minute ->

            pickerOra = String.format("%02d:%02d", hourOfDay, minute) + ":00"
            ora_inizio_meeting!!.text = pickerOra
        }, calendar!!.get(Calendar.HOUR_OF_DAY), calendar!!.get(Calendar.MINUTE), true)
        timePickerDialog.show()
    }

    private val oraFineCalendarClickListener = View.OnClickListener {
        calendar = Calendar.getInstance()
        val timePickerDialog = TimePickerDialog(context, R.style.PickerDialogTheme, { view, hourOfDay, minute ->

            pickerOra = String.format("%02d:%02d", hourOfDay, minute) + ":00"
            ora_fine_meeting!!.text = pickerOra
        }, calendar!!.get(Calendar.HOUR_OF_DAY), calendar!!.get(Calendar.MINUTE), true)
        timePickerDialog.show()
    }
}