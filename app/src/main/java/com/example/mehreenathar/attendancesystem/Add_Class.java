package com.example.mehreenathar.attendancesystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Add_Class extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__class);
    }
    public void add(View view) {
        EditText c_name = (EditText) findViewById(R.id.editText2);
        String cn = c_name.getText().toString();
        EditText c_code = (EditText) findViewById(R.id.editText3);
        String cc = c_code.getText().toString();
        EditText s_cap = (EditText) findViewById(R.id.editText);
        String lens = s_cap.getText().toString();
        int len = Integer.parseInt(lens);
        String seats = s_cap.getText().toString();
        String not_added=null;

        int seat = Integer.parseInt(seats);
        if (((seat > 9) && (seat < 51)) && (cn != null) && (cc != null) ) {
           // login=new loginteacher();
            Course course1=new Course(cn,cc,seat);
            Teacher teacher=(Teacher)getIntent().getSerializableExtra("teacher_arr");
            teacher.addCourse(course1);
            Toast.makeText(getApplicationContext(), "Course added", Toast.LENGTH_LONG).show();
            Intent intent=new Intent(Add_Class.this,options.class);
            intent.putExtra("teacher_arr", teacher);
            startActivity(intent);
        }
        if ((seat < 10) || (seat > 50)) {
            not_added = "Seats should be greater than 10 but not be greater than 50";
        }
        if (cn.isEmpty()) {
            if(not_added.isEmpty())
            {
                not_added = "Course name should not be empty";
            }
            else
            {
                String in=" and";
                String make=" Course name should not be empty";
                not_added=not_added.concat(in);
                not_added=not_added.concat(make);
            }
        }
        if (cc.isEmpty())
        {
            if (not_added.isEmpty()) {
                not_added = "Course code should not be empty";
            }
            else {
                String in = " and";
                String make = " Course code should not be empty";
                not_added = not_added.concat(in);
                not_added = not_added.concat(make);
            }
        }
        Toast.makeText(this,not_added, Toast.LENGTH_LONG).show();
    }
}


