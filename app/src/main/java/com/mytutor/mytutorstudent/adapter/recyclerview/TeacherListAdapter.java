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
*/public class TeacherListAdapter extends RecyclerView.Adapter<TeacherListAdapter.TeacherViewHolder> {
    private ArrayList<HashMap<String, Object>> teacherArrayList;
    private TeacherViewHolder.TeacherInteractionListner teacherInteractionListner;

    public TeacherListAdapter(ArrayList<HashMap<String, Object>> teacherArrayList, TeacherViewHolder.TeacherInteractionListner teacherInteractionListner) {
        this.teacherArrayList = teacherArrayList;
        this.teacherInteractionListner = teacherInteractionListner;
    }

    @NonNull
    @Override
    public TeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TeacherViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.container_teacher, parent, false), teacherInteractionListner);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherViewHolder holder, int position) {
        HashMap<String, Object> hashMap = teacherArrayList.get(position);
        holder.teacherName.setText((String) hashMap.get(TeacherMap.NAME));
        holder.prefferedTime.setText((String) hashMap.get(TeacherMap.PREFFERED_TIME));
        holder.costPerSession.setText("$ " + String.valueOf(hashMap.get(TeacherMap.COST_PER_SESSION) + " per Session"));
        holder.specialisedArea.setText((String) hashMap.get(TeacherMap.SPECIALISED_IN));
        holder.ratingLabel.setText((String.valueOf(hashMap.get(TeacherMap.RATING) + "/5")));
        holder.ratingBar.setRating(Float.valueOf((String.valueOf(hashMap.get(TeacherMap.RATING)))));

    }

    @Override
    public int getItemCount() {
        return teacherArrayList.size();
    }

    public static class TeacherViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView teacherName;
        private TextView specialisedArea;
        private TextView costPerSession;
        private TextView prefferedTime;
        private MaterialButton makeAppointment;
        private RatingBar ratingBar;
        private TextView ratingLabel;
        private TeacherInteractionListner teacherInteractionListner;

        public TeacherViewHolder(@NonNull View itemView, TeacherInteractionListner teacherInteractionListner) {
            super(itemView);
            this.teacherInteractionListner = teacherInteractionListner;
            teacherName = itemView.findViewById(R.id.container_teacher_name);
            specialisedArea = itemView.findViewById(R.id.container_teacher_specialised_area);
            costPerSession = itemView.findViewById(R.id.container_teacher_session_cost);
            prefferedTime = itemView.findViewById(R.id.container_teacher_preffered_time);
            makeAppointment = itemView.findViewById(R.id.container_teacher_make_appointment);
            ratingBar = itemView.findViewById(R.id.container_teacher_rating_bar);
            ratingLabel = itemView.findViewById(R.id.container_teacher_rating_label);
            specialisedArea.setSelected(true);
            makeAppointment.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            teacherInteractionListner.onAppointed(getAdapterPosition());
        }

        public interface TeacherInteractionListner {
            void onAppointed(int position);
        }
    }
}
