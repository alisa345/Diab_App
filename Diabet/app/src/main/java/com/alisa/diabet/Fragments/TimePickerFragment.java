package com.alisa.diabet.Fragments;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.alisa.diabet.R;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    EditText time;

    public TimePickerFragment() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(), R.style.PickerTheme, this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }


    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        time = (EditText) getActivity().findViewById(R.id.timeText);


        if (minute < 10 && minute != 0) {
            time.setText(Integer.toString(hourOfDay) + ":0" + Integer.toString(minute));
        }
        if (minute == 0) {
            time.setText(Integer.toString(hourOfDay) + ":00");
        } else {
            time.setText(Integer.toString(hourOfDay) + ":" + Integer.toString(minute));
        }

    }
}
