package com.nexus.nurseryteacher.fragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.widget.EditText;
import android.widget.TimePicker;

import com.nexus.nurseryteacher.R;
import com.nexus.nurseryteacher.activity.EditScheduleActivity;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment{

    EditScheduleActivity parentActivity;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Calendar cal = Calendar.getInstance();
        int minute = cal.get(Calendar.MINUTE);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        parentActivity = (EditScheduleActivity) getActivity();
        return new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener) getActivity(), hour, minute, false);
    }

}
