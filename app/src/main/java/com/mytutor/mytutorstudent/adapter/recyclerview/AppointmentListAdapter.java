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
import com.mytutor.mytutorstudent.ui.utils.TeacherMap;

import java.util.ArrayList;
import java.util.HashMap;

/*
@Author cr7
@CreatedOn 3/29/2020
*/public class AppointmentListAdapter extends RecyclerView.Adapter<AppointmentListAdapter.AppointmentViewHolder> {
    private ArrayList<HashMap<String, Object>> appointmentList;
    private AppointmentInteractionListener appointmentInteractionListener;

    public AppointmentListAdapter(ArrayList<HashMap<String, Object>> appointmentList, AppointmentInteractionListener appointmentInteractionListener) {
        this.appointmentList = appointmentList;
        this.appointmentInteractionListener = appointmentInteractionListener;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AppointmentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.container_appointments, parent, false), appointmentInteractionListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        HashMap<String, Object> hashMap = appointmentList.get(position);
        holder.teacherName.setText((String) hashMap.get(AppointmentMap.TEACHER_NAME));
        holder.prefferedTime.setText((String) hashMap.get(AppointmentMap.PREFFERED_TIME));
        holder.costPerSession.setText("$ " + String.valueOf(hashMap.get(AppointmentMap.COST_PER_SESSION) + " per Session"));
        holder.specialisedArea.setText((String) hashMap.get(AppointmentMap.SPECIALISED_IN));
        holder.ratingLabel.setText((String.valueOf(hashMap.get(AppointmentMap.RATING) + "/5")));
        holder.ratingBar.setRating(Float.valueOf((String.valueOf(hashMap.get(AppointmentMap.RATING)))));

    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    static class AppointmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView teacherName;
        private TextView specialisedArea;
        private TextView costPerSession;
        private TextView prefferedTime;
        private MaterialButton makeAppointment;
        private RatingBar ratingBar;
        private TextView ratingLabel;
        private AppointmentInteractionListener appointmentInteractionListener;

        public AppointmentViewHolder(@NonNull View itemView, AppointmentInteractionListener appointmentInteractionListener) {
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

        }
    }

    public interface AppointmentInteractionListener {
        void onAppointmentCancelled(int position);
    }
}
