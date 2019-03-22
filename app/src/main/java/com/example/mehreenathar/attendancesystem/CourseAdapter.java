package com.example.mehreenathar.attendancesystem;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder>
{
    Context mContext;
    ArrayList<Course>courses;

    public CourseAdapter(Context mContext, ArrayList<Course> courses) {
        this.mContext = mContext;
        this.courses = courses;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.course_row,viewGroup,false);

        return new CourseViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder courseViewHolder, int i) {
        //bind data to view here
        String displayText=courses.get(i).getCourseName()+" : "+Integer.toString(courses.get(i).getSeats());
        courseViewHolder.courseDetail.setText(displayText);

    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder
    {
        //here we put stuff we display in our view..in our case for each course we display its name and seats in a button
        Button courseDetail;
        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            courseDetail=(Button)itemView.findViewById(R.id.courseAndSeatsBtn);
        }
    }
}
