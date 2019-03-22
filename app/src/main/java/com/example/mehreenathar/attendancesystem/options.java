package com.example.mehreenathar.attendancesystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class options extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Button add_classButton=(Button)findViewById(R.id.addClassBtn);
        Button viewClassBtn=(Button)findViewById(R.id.viewClassesBtn);
        Button markAttendanceBtnn=(Button)findViewById(R.id.markAttendanceBtn);
        Button addStudentBtnn=(Button)findViewById(R.id.addStudentBtn);
        Button logoutBtn=(Button)findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(options.this,MainLogoActivity.class);
                startActivity(intent);

            }
        });
        //addStudentBtn
        viewClassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Teacher teacher=(Teacher)getIntent().getSerializableExtra("teacher_arr");
                Intent intent=new Intent(options.this,viewCourses.class);
                intent.putExtra("teacher_arr", teacher);
                startActivity(intent);
            }
        });
        addStudentBtnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Teacher teacher=(Teacher)getIntent().getSerializableExtra("teacher_arr");
                Intent intent=new Intent(options.this,RegisterStudent.class);
                intent.putExtra("teacher_arr", teacher);
                startActivity(intent);
            }
        });
        add_classButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Teacher teacher=(Teacher)getIntent().getSerializableExtra("teacher_arr");
                Intent intent=new Intent(options.this,Add_Class.class);
                intent.putExtra("teacher_arr", teacher);
                startActivity(intent);
            }
        });
        markAttendanceBtnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Teacher teacher=(Teacher)getIntent().getSerializableExtra("teacher_arr");
                Intent intent=new Intent(options.this,selectCourseToMarkAttendance.class);
                intent.putExtra("teacher_arr", teacher);
                startActivity(intent);
            }
        });
    }



}
