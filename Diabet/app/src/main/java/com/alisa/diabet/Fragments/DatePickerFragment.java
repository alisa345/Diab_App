package com.alisa.diabet.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.EditText;

import com.alisa.diabet.R;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    EditText date;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Использовать текущую дату в качестве даты по умолчанию
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), R.style.PickerTheme, this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        date = (EditText) getActivity().findViewById(R.id.dataText);

        date.setText(Integer.toString(day) + "." + Integer.toString(month) + "." +
                Integer.toString(year));
    }
}