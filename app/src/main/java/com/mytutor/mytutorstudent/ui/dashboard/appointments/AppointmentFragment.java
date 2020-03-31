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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mytutor.mytutorstudent.R;
import com.mytutor.mytutorstudent.adapter.recyclerview.AppointmentListAdapter;
import com.mytutor.mytutorstudent.ui.utils.AppointmentMap;
import com.mytutor.mytutorstudent.ui.utils.Collection;

import java.util.ArrayList;
import java.util.HashMap;

/*
@Author cr7
@CreatedOn 3/28/2020
*/
public class AppointmentFragment extends Fragment implements AppointmentListAdapter.AppointmentInteractionListener {
    public static final String FRAGMENT_TYPE = "fragment_type";
    private RecyclerView mRecyclerview;
    private AppointmentListAdapter appointmentListAdapter;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth auth;
    private ArrayList<HashMap<String, Object>> appointmentList = new ArrayList();

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
        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        appointmentListAdapter = new AppointmentListAdapter(appointmentList, this);
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

    @Override
    public void onResume() {
        super.onResume();

        firebaseFirestore.collection(Collection.APPOINTMENTS).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    if (!appointmentList.isEmpty()) {
                        appointmentList.clear();
                    }
                    for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                        HashMap<String, Object> map = (HashMap<String, Object>) queryDocumentSnapshot.getData();
                        Long statusCode = (Long) map.get(AppointmentMap.STATUS_CODE);
                        if ((Math.round(statusCode) == 0) || (Math.round(statusCode) == 2))
                            appointmentList.add(map);
                    }
                    appointmentListAdapter.notifyDataSetChanged();

                }
            }
        });
    }

    @Override
    public void onAppointmentCancelled(int position) {

        final HashMap<String, Object> map = appointmentList.get(position);

        firebaseFirestore.collection(Collection.APPOINTMENTS).document((String) map.get(AppointmentMap.APPOINTMENT_ID)).update(AppointmentMap.STATUS_CODE, -1).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                onResume();
            }
        });

    }
}
