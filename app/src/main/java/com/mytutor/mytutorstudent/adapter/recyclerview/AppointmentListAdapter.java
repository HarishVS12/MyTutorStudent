package com.mytutor.mytutorstudent.adapter.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mytutor.mytutorstudent.R;
import com.mytutor.mytutorstudent.model.Teacher;

import java.util.ArrayList;

/*
@Author cr7
@CreatedOn 3/29/2020
*/public class AppointmentListAdapter extends RecyclerView.Adapter<AppointmentListAdapter.AppointmentViewHolder> {
    private ArrayList<Teacher> teacherArrayList;

    public AppointmentListAdapter(ArrayList<Teacher> teacherArrayList) {
        this.teacherArrayList = teacherArrayList;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AppointmentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.container_teacher, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return teacherArrayList.size();
    }

    static class AppointmentViewHolder extends RecyclerView.ViewHolder {

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
