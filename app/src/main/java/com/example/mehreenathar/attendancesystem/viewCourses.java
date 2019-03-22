package com.example.mehreenathar.attendancesystem;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class viewCourses extends AppCompatActivity {

    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Course>courses;
    private  CourseAdapter courseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_courses);
        //lets set our array list to the courses the Teacher is taking
        //Now recognize the recycler view here
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.displayCoursesRV);//name of recycler view in activity xml

        Teacher teacher=(Teacher)getIntent().getSerializableExtra("teacher_arr");
        courses=teacher.getCourses();
        courseAdapter=new CourseAdapter(this,courses);
        linearLayoutManager=new LinearLayoutManager(this);

        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
    public void yes()
    {

    }
}
