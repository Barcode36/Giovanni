package com.example.giovanni.giovanni.viewpagertablayout;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.giovanni.giovanni.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FragmentPicker extends Fragment implements DatePickerDialog.OnDateSetListener {

    private Calendar calendar;
    private DatePickerDialog datePickerDialog;
    private TextView ora;
    private TextView data;
    private TextView textformattedDate;

    private String pickerOra;
    private String pickerData;

    // Messaggio per testare il commit + la push

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picker, container, false);

        Button buttonOra = view.findViewById(R.id.buttonOraCalendar);
        ora = view.findViewById(R.id.ora);
        Button buttonData = view.findViewById(R.id.buttonDataCalendar);
        data = view.findViewById(R.id.data);
        textformattedDate = view.findViewById(R.id.formattedDate);

        buttonOra.setOnClickListener(buttonOraCalendarClickListener);

        calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(getContext(), this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        buttonData.setOnClickListener(buttonDataCalendarClickListener);

        TextView tDataAttuale = view.findViewById(R.id.textDataAttuale);
        TextView tDataGenerica = view.findViewById(R.id.textDataGenerica);
        TextView tData = view.findViewById(R.id.textData);

        /*
        pattern:
        yyyy-MM-dd
        dd-MM-yyyy
        dd/MM/yyyy
        hh:mm:ss
        dd/MM/yyyy hh:mm
        dd/MM/yyyy hh:mm:ss
        */

        // PER TRASFORMARE LA DATA ATTUALE DAL TIPO DATE AL TIPO STRING
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a", Locale.ITALY);
        String data1 = sdf1.format(new Date());
        tDataAttuale.setText(data1);

        // NOTA 1: Locale.ITALY può anche essere omesso.
        // NOTA 2: la a fa apparire AM o PM a seconda dei casi.

        String dataModificata = data1.replaceFirst("01", "02");
        Log.i("TAG", "Data: " + data1 + "\nData modificata: " + dataModificata);

        // PER TRASFORMARE UNA DATA GENERICA DAL TIPO DATE AL TIPO STRING
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALY);
        Date date = sdf2.parse("06/02/2018", new ParsePosition(0));
        String data2 = formatDate(date);
        tDataGenerica.setText(data2);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.UK);
        String data3 = mdformat.format(calendar.getTime());
        tData.setText(data3);

        return view;
    }

    private View.OnClickListener buttonOraCalendarClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            calendar = Calendar.getInstance();
            TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), (view, hourOfDay, minute) -> {

                // "HH:mm" (02 indica che HH e mm vanno riempiti al massimo con due zeri.
                pickerOra = String.format("%02d:%02d", hourOfDay, minute) + ":00";
                ora.setText(pickerOra);
                showDataCompleta();
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
            timePickerDialog.show();
        }
    };

    private View.OnClickListener buttonDataCalendarClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
            datePickerDialog.show();
        }
    };

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        // "dd/MM/yyyy"
        pickerData = String.format("%02d/%02d/%04d", day, month + 1, year);
        data.setText(pickerData);
        showDataCompleta();
    }

    private void showDataCompleta() {
        if (pickerOra != null && pickerData != null) {
            String dataCompleta = pickerData + " " + pickerOra;
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            try {
                Date date = dateFormat.parse(dataCompleta);
                String formattedDate = date.toString();
                textformattedDate.setText(formattedDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public String formatDate(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALY);
        return sdf.format(data);
    }
}