package com.example.giovanni.giovanni.viewpagertablayout

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.example.giovanni.giovanni.R
import com.example.giovanni.giovanni.utils.BaseFragment
import com.example.giovanni.giovanni.utils.DateManager
import kotlinx.android.synthetic.main.fragment_picker_kotlin.*
import java.text.SimpleDateFormat
import java.util.*

class FragmentPickerKotlin : BaseFragment(), DatePickerDialog.OnDateSetListener {

    private var meetingDateStart: DateManager? = null
    private var meetingDateEnd: DateManager? = null

    private var calendar: Calendar? = null
    private var datePickerDialog: DatePickerDialog? = null
    private var pickerOra: String? = null
    private var pickerData: String? = null

    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_picker_kotlin, container, false)

        // set default hour data
        meetingDateStart = DateManager(Date())
        meetingDateEnd = DateManager(Date().time + (60 * 60 * 1000))
        data_meeting_default?.text = meetingDateStart?.getDatePickerFormatDate()
        ora_inizio_meeting_default?.text = meetingDateStart?.getDatePickerFormatTime()
        ora_fine_meeting_default?.text = meetingDateEnd?.getDatePickerFormatTime()

        ora_inizio_meeting_request?.text = meetingDateStart?.getRequestDateFormat()
        ora_fine_meeting_request?.text = meetingDateEnd?.getRequestDateFormat()
        data_inizio_fine_request?.text = DateManager.getStrDateTitle(meetingDateStart!!.getServerDateFormat(), meetingDateEnd!!.getServerDateFormat())
        ora_inizio_fine_request?.text = DateManager.getMeetingHour(meetingDateStart!!.getServerDateFormat(), meetingDateEnd!!.getServerDateFormat())

        val dataInizio = "25/12/2018 09:30:00"
        val dataFine = "25/12/2018 11:45:00"
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        val dateInizio = dateFormat.parse(dataInizio)
        val dateFine = dateFormat.parse(dataFine)
        meetingDateStart = DateManager(dateInizio)
        meetingDateEnd = DateManager(dateFine)

        ora_inizio_meeting_response?.text = meetingDateStart?.getRequestDateFormat()
        ora_fine_meeting_response?.text = meetingDateEnd?.getRequestDateFormat()
        data_inizio_fine_response?.text = DateManager.getStrDateTitle(meetingDateStart!!.getServerDateFormat(), meetingDateEnd!!.getServerDateFormat())
        ora_inizio_fine_response?.text = DateManager.getMeetingHour(meetingDateStart!!.getServerDateFormat(), meetingDateEnd!!.getServerDateFormat())

        calendar = Calendar.getInstance()
        datePickerDialog = DatePickerDialog(context!!, R.style.PickerDialogTheme, this, calendar?.get(Calendar.YEAR)!!, calendar?.get(Calendar.MONTH)!!, calendar?.get(Calendar.DAY_OF_MONTH)!!)
        data_meeting?.setOnClickListener(buttonDataCalendarClickListener)

        ora_inizio_meeting?.setOnClickListener(oraInizioCalendarClickListener) // Riprendere dalla riga 269 di W3AppDipendenti
        ora_fine_meeting?.setOnClickListener(oraFineCalendarClickListener)

        multiSwipeRefreshLayout = view.findViewById(R.id.multi_swipe_refresh_layout)
        multiSwipeRefreshLayout?.setSwipeableChildren(R.id.linear_layout_child)
        multiSwipeRefreshLayout?.setOnRefreshListener {

            meetingDateStart = DateManager(Date())
            meetingDateEnd = DateManager(Date().time + (60 * 60 * 1000))
            data_meeting_default?.text = meetingDateStart?.getDatePickerFormatDate()
            ora_inizio_meeting_default?.text = meetingDateStart?.getDatePickerFormatTime()
            ora_fine_meeting_default?.text = meetingDateEnd?.getDatePickerFormatTime()

            ora_inizio_meeting_request?.text = meetingDateStart?.getRequestDateFormat()
            ora_fine_meeting_request?.text = meetingDateEnd?.getRequestDateFormat()
            data_inizio_fine_request?.text = DateManager.getStrDateTitle(meetingDateStart!!.getServerDateFormat(), meetingDateEnd!!.getServerDateFormat())
            ora_inizio_fine_request?.text = DateManager.getMeetingHour(meetingDateStart!!.getServerDateFormat(), meetingDateEnd!!.getServerDateFormat())

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
        val timePickerDialog = TimePickerDialog(context, R.style.PickerDialogTheme, { _, hourOfDay, minute ->

            pickerOra = String.format("%02d:%02d", hourOfDay, minute) + ":00"
            ora_inizio_meeting!!.text = pickerOra
        }, calendar!!.get(Calendar.HOUR_OF_DAY), calendar!!.get(Calendar.MINUTE), true)
        timePickerDialog.show()
    }

    private val oraFineCalendarClickListener = View.OnClickListener {
        calendar = Calendar.getInstance()
        val timePickerDialog = TimePickerDialog(context, R.style.PickerDialogTheme, { _, hourOfDay, minute ->

            pickerOra = String.format("%02d:%02d", hourOfDay, minute) + ":00"
            ora_fine_meeting!!.text = pickerOra
        }, calendar!!.get(Calendar.HOUR_OF_DAY), calendar!!.get(Calendar.MINUTE), true)
        timePickerDialog.show()
    }
}