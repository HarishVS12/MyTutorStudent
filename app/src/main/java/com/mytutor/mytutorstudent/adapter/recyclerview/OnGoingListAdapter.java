package com.mytutor.mytutorstudent.adapter.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.mytutor.mytutorstudent.R;
import com.mytutor.mytutorstudent.ui.utils.TeacherMap;

import java.util.ArrayList;
import java.util.HashMap;

/*
@Author cr7
@CreatedOn 3/29/2020
*/public class OnGoingListAdapter extends RecyclerView.Adapter<OnGoingListAdapter.OnGoingViewHolder> {
    private ArrayList<HashMap<String, Object>> appointmentList;
    private OnGoingInteractionListener onGoingInteractionListener;

    public OnGoingListAdapter(ArrayList<HashMap<String, Object>> appointmentList, OnGoingInteractionListener onGoingInteractionListener) {
        this.appointmentList = appointmentList;
        this.onGoingInteractionListener = onGoingInteractionListener;
    }

    @NonNull
    @Override
    public OnGoingListAdapter.OnGoingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnGoingListAdapter.OnGoingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.container_ongoing, parent, false), onGoingInteractionListener);
    }

    @Override
    public void onBindViewHolder(@NonNull OnGoingListAdapter.OnGoingViewHolder holder, int position) {
        HashMap<String, Object> hashMap = appointmentList.get(position);
        holder.teacherName.setText((String) hashMap.get(TeacherMap.NAME));
        holder.prefferedTime.setText((String) hashMap.get(TeacherMap.PREFFERED_TIME));
        holder.costPerSession.setText("$ " + String.valueOf(hashMap.get(TeacherMap.COST_PER_SESSION) + " per Session"));
        holder.specialisedArea.setText((String) hashMap.get(TeacherMap.SPECIALISED_IN));
        holder.ratingLabel.setText((String.valueOf(hashMap.get(TeacherMap.RATING) + "/5")));
        holder.ratingBar.setRating(Float.valueOf((String.valueOf(hashMap.get(TeacherMap.RATING)))));

    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    public static class OnGoingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView teacherName;
        private TextView specialisedArea;
        private TextView costPerSession;
        private TextView prefferedTime;
        private MaterialButton makeAppointment;
        private RatingBar ratingBar;
        private TextView ratingLabel;
        private OnGoingInteractionListener teacherInteractionListner;

        public OnGoingViewHolder(@NonNull View itemView, OnGoingInteractionListener teacherInteractionListner) {
            super(itemView);
            this.teacherInteractionListner = teacherInteractionListner;
            teacherName = itemView.findViewById(R.id.container_ongoing_name);
            specialisedArea = itemView.findViewById(R.id.container_ongoing_specialised_area);
            costPerSession = itemView.findViewById(R.id.container_ongoing_session_cost);
            prefferedTime = itemView.findViewById(R.id.container_ongoing_preffered_time);
            makeAppointment = itemView.findViewById(R.id.container_ongoing_cancel_appointment);
            ratingBar = itemView.findViewById(R.id.container_ongoing_rating_bar);
            ratingLabel = itemView.findViewById(R.id.container_ongoing_rating_label);
            specialisedArea.setSelected(true);
            makeAppointment.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            teacherInteractionListner.onJoined(getAdapterPosition());
        }

    }

    public interface OnGoingInteractionListener {
        void onJoined(int position);
    }
}
