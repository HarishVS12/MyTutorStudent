package com.mytutor.mytutorstudent.ui.dashboard.ongoing;

import android.content.Intent;
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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mytutor.mytutorstudent.R;
import com.mytutor.mytutorstudent.adapter.recyclerview.OnGoingListAdapter;
import com.mytutor.mytutorstudent.ui.classroom.VideoChatViewActivity;
import com.mytutor.mytutorstudent.ui.utils.AppointmentMap;
import com.mytutor.mytutorstudent.ui.utils.Collection;

import java.util.ArrayList;
import java.util.HashMap;

/*
@Author cr7
@CreatedOn 3/28/2020
*/
public class OnGoingFragment extends Fragment implements OnGoingListAdapter.OnGoingInteractionListener {
    public static final String FRAGMENT_TYPE = "fragment_type";
    private RecyclerView mRecyclerview;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth auth;
    private ArrayList<HashMap<String, Object>> appointmentList = new ArrayList();
    private OnGoingListAdapter onGoingListAdapter;

    public static OnGoingFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString(FRAGMENT_TYPE, type);
        OnGoingFragment fragment = new OnGoingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        onGoingListAdapter = new OnGoingListAdapter(appointmentList, this);
    }

    @Override
    public void onResume() {
        super.onResume();

        firebaseFirestore.collection(Collection.APPOINTMENTS).whereEqualTo(AppointmentMap.STATUS_CODE, 1).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    if (!appointmentList.isEmpty()) {
                        appointmentList.clear();
                    }
                    for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                        HashMap<String, Object> map = (HashMap<String, Object>) queryDocumentSnapshot.getData();
                        appointmentList.add(map);
                    }
                    onGoingListAdapter.notifyDataSetChanged();

                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_ongoing, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerview = view.findViewById(R.id.ongoing_recyclerview);
        mRecyclerview.setAdapter(onGoingListAdapter);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onJoined(int position) {
        HashMap<String, Object> map = appointmentList.get(position);
        Intent intent = new Intent(getContext(), VideoChatViewActivity.class);
        intent.putExtra(AppointmentMap.TEACHER_ID, (String) map.get(AppointmentMap.TEACHER_ID));
        intent.putExtra(AppointmentMap.COST_PER_SESSION, (String) map.get(AppointmentMap.COST_PER_SESSION));
        intent.putExtra(AppointmentMap.APPOINTMENT_ID, (String) map.get(AppointmentMap.APPOINTMENT_ID));
        startActivity(intent);
    }
}
