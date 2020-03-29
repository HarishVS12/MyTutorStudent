package com.mytutor.mytutorstudent.ui.dashboard.appointments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mytutor.mytutorstudent.R;
import com.mytutor.mytutorstudent.adapter.recyclerview.AppointmentListAdapter;

import java.util.ArrayList;

/*
@Author cr7
@CreatedOn 3/28/2020
*/
public class AppointmentFragment extends Fragment {
    public static final String FRAGMENT_TYPE = "fragment_type";
    private RecyclerView mRecyclerview;
    private AppointmentListAdapter appointmentListAdapter;

    public static AppointmentFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString(FRAGMENT_TYPE, type);
        AppointmentFragment fragment = new AppointmentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appointmentListAdapter=new AppointmentListAdapter(new ArrayList());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_appointment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerview = view.findViewById(R.id.appointment_recyclerview);
        mRecyclerview.setAdapter(appointmentListAdapter);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}