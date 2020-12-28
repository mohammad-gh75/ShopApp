package org.maktab36.finalproject.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;

import org.maktab36.finalproject.R;

public class NotificationTimePickerFragment extends DialogFragment {


    public NotificationTimePickerFragment() {
        // Required empty public constructor
    }

    public static NotificationTimePickerFragment newInstance() {
        NotificationTimePickerFragment fragment = new NotificationTimePickerFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification_time_picker, container, false);
    }
}