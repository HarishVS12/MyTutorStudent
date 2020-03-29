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
import com.mytutor.mytutorstudent.ui.utils.AppointmentMap;

import java.util.ArrayList;
import java.util.HashMap;

/*
@Author cr7
@CreatedOn 3/29/2020
*/public class AppointmentListAdapter extends RecyclerView.Adapter<AppointmentListAdapter.AppointBaseHolder> {
    private ArrayList<HashMap<String, Object>> appointmentList;
    private AppointmentInteractionListener appointmentInteractionListener;
    private static final int CANCEL_TYPE = 0;
    private static final int COMPLETED_TYPE = 1;

    public AppointmentListAdapter(ArrayList<HashMap<String, Object>> appointmentList, AppointmentInteractionListener appointmentInteractionListener) {
        this.appointmentList = appointmentList;
        this.appointmentInteractionListener = appointmentInteractionListener;
    }

    @Override
    public int getItemViewType(int position) {
        HashMap<String, Object> map = appointmentList.get(position);
        if (map.get(AppointmentMap.STATUS_CODE) == (Integer) 0) {
            return CANCEL_TYPE;
        } else {
            return COMPLETED_TYPE;
        }
    }

    static class AppointBaseHolder extends RecyclerView.ViewHolder {

        public AppointBaseHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public AppointBaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == CANCEL_TYPE) {
            return new AppointmentCancelHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.container_cancel_appointments, parent, false), appointmentInteractionListener);
        } else {
            return new AppointmentCompletedHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.container_completed_appointments, parent, false), appointmentInteractionListener);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull AppointBaseHolder appointBaseHolder, int position) {
        if (appointBaseHolder.getItemViewType() == CANCEL_TYPE &&
                appointBaseHolder instanceof AppointmentCancelHolder) {
            AppointmentCancelHolder cancelHolder = (AppointmentCancelHolder) appointBaseHolder;
            HashMap<String, Object> hashMap = appointmentList.get(position);
            cancelHolder.teacherName.setText((String) hashMap.get(AppointmentMap.TEACHER_NAME));
            cancelHolder.prefferedTime.setText((String) hashMap.get(AppointmentMap.PREFFERED_TIME));
            cancelHolder.costPerSession.setText("$ " + String.valueOf(hashMap.get(AppointmentMap.COST_PER_SESSION) + " per Session"));
            cancelHolder.specialisedArea.setText((String) hashMap.get(AppointmentMap.SPECIALISED_IN));
            cancelHolder.ratingLabel.setText((String.valueOf(hashMap.get(AppointmentMap.RATING) + "/5")));
            cancelHolder.ratingBar.setRating(Float.valueOf((String.valueOf(hashMap.get(AppointmentMap.RATING)))));
        }

        if (appointBaseHolder.getItemViewType() == COMPLETED_TYPE &&
                appointBaseHolder instanceof AppointmentCompletedHolder) {
            AppointmentCompletedHolder completedHolder = (AppointmentCompletedHolder) appointBaseHolder;
            HashMap<String, Object> hashMap = appointmentList.get(position);
            completedHolder.teacherName.setText((String) hashMap.get(AppointmentMap.TEACHER_NAME));
            completedHolder.prefferedTime.setText((String) hashMap.get(AppointmentMap.PREFFERED_TIME));
            if (hashMap.get(AppointmentMap.STATUS_CODE) ==(Integer) 1) {
                completedHolder.appointmentStatus.setText("Completed");
            } else {
                completedHolder.appointmentStatus.setText("Cancelled");
            }

            completedHolder.specialisedArea.setText((String) hashMap.get(AppointmentMap.SPECIALISED_IN));
            completedHolder.ratingLabel.setText((String.valueOf(hashMap.get(AppointmentMap.RATING) + "/5")));
            completedHolder.ratingBar.setRating(Float.valueOf((String.valueOf(hashMap.get(AppointmentMap.RATING)))));
        }


    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    static class AppointmentCancelHolder extends AppointBaseHolder implements View.OnClickListener {
        private TextView teacherName;
        private TextView specialisedArea;
        private TextView costPerSession;
        private TextView prefferedTime;
        private MaterialButton makeAppointment;
        private RatingBar ratingBar;
        private TextView ratingLabel;
        private AppointmentInteractionListener appointmentInteractionListener;

        public AppointmentCancelHolder(@NonNull View itemView, AppointmentInteractionListener appointmentInteractionListener) {
            super(itemView);
            this.appointmentInteractionListener = appointmentInteractionListener;
            teacherName = itemView.findViewById(R.id.container_appointment_name);
            specialisedArea = itemView.findViewById(R.id.container_appointment_specialised_area);
            costPerSession = itemView.findViewById(R.id.container_appointment_session_cost);
            prefferedTime = itemView.findViewById(R.id.container_appointment_preffered_time);
            makeAppointment = itemView.findViewById(R.id.container_appointment_cancel_appointment);
            ratingBar = itemView.findViewById(R.id.container_appointment_rating_bar);
            ratingLabel = itemView.findViewById(R.id.container_appointment_rating_label);
            specialisedArea.setSelected(true);
            makeAppointment.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
appointmentInteractionListener.onAppointmentCancelled(getAdapterPosition());
        }
    }

    static class AppointmentCompletedHolder extends AppointBaseHolder implements View.OnClickListener {
        private TextView teacherName;
        private TextView specialisedArea;

        private TextView prefferedTime;
        private TextView appointmentStatus;
        private RatingBar ratingBar;
        private TextView ratingLabel;
        private AppointmentInteractionListener appointmentInteractionListener;

        public AppointmentCompletedHolder(@NonNull View itemView, AppointmentInteractionListener appointmentInteractionListener) {
            super(itemView);
            this.appointmentInteractionListener = appointmentInteractionListener;
            teacherName = itemView.findViewById(R.id.container_completed_appointment_name);
            specialisedArea = itemView.findViewById(R.id.container_completed_appointment_specialised_area);
            prefferedTime = itemView.findViewById(R.id.container_completed_appointment_preffered_time);
            appointmentStatus = itemView.findViewById(R.id.container_completed_appointment_cancel_appointment);
            ratingBar = itemView.findViewById(R.id.container_completed_appointment_rating_bar);
            ratingLabel = itemView.findViewById(R.id.container_completed_appointment_rating_label);
            specialisedArea.setSelected(true);
            appointmentStatus.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            appointmentInteractionListener.onAppointmentCancelled(getAdapterPosition());
        }
    }

    public interface AppointmentInteractionListener {
        void onAppointmentCancelled(int position);
    }
}
