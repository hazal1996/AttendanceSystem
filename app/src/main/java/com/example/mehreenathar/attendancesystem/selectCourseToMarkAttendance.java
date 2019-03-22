package com.example.mehreenathar.attendancesystem;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;
public class selectCourseToMarkAttendance extends AppCompatActivity {
private Spinner courseToMarkSpinner;
private Course courseSelected;
private Teacher teacher;
private boolean set=false;
private Intent data1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_course_to_mark_attendance);
        final Button mark=(Button)findViewById(R.id.mark);
        if(data1==null)
        {
            teacher=(Teacher)getIntent().getSerializableExtra("teacher_arr");
        }
        courseToMarkSpinner=findViewById(R.id.courseToMark);
        //get list of course objects to display in drop down
        List<Course> coursesToShow=teacher.getCourses();//get courses the teacher is teaching
        //to get data into spinner we use array adapter that by default works on strings
        ArrayAdapter<Course> adapter=new ArrayAdapter<Course>(this,android.R.layout.simple_spinner_item,coursesToShow);//tell how spinner should look (chose from default themes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//when drop down opened how spinner should look
        courseToMarkSpinner.setAdapter(adapter);

        //what t do when item in spinner is selected
        courseToMarkSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                courseSelected =(Course)parent.getSelectedItem();

                // Toast.makeText(getApplicationContext(), "Course !"+selectedCourse.getCourseName()+"Selected", Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(selectCourseToMarkAttendance.this,MarkAttendance.class);
                intent.putExtra("teacher_arr", teacher);
                courseSelected=teacher.getCourseObj(courseSelected);
                intent.putExtra("courseSelected",courseSelected);
                startActivityForResult(intent,1);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK)
            {
                this.data1=data;
                //teacher=(Teacher)getIntent().getSerializableExtra("teacher_arr");

                teacher=(Teacher) data.getSerializableExtra("teacher_arr");
                set=true;
            }

            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
}
