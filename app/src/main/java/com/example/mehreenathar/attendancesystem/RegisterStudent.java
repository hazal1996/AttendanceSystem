package com.example.mehreenathar.attendancesystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RegisterStudent extends AppCompatActivity {
    private Spinner courseSpinner;
    private Course courseSelected;
    private Spinner genderSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);
        //get courses from intent
        final Teacher teacher=(Teacher)getIntent().getSerializableExtra("teacher_arr");
        final EditText studentName=(EditText)findViewById(R.id.fullName);
        final EditText fatherName=(EditText)findViewById(R.id.fatherName);
        final EditText contactNo=(EditText)findViewById(R.id.contactNumber);
       // Intent intent=new Intent(Add_Class.this,options.class);
        //intent.putExtra("teacher_arr", teacher);
       // startActivity(intent);

        /*********************Courses Drop Down Logic************************************/
        courseSpinner=findViewById(R.id.selectCourse);  //get the selectCourse spinner id from xml
        genderSpinner=findViewById(R.id.selectGender);
        //get list of course objects to display in drop down
        List<Course> coursesToShow=teacher.getCourses();//get courses the teacher is teaching
        //to get data into spinner we use array adapter that by default works on strings
        ArrayAdapter<Course> adapter=new ArrayAdapter<Course>(this,android.R.layout.simple_spinner_item,coursesToShow);//tell how spinner should look (chose from default themes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//when drop down opened how spinner should look
        courseSpinner.setAdapter(adapter);

        //what t do when item in spinner is selected
        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                courseSelected =(Course)parent.getSelectedItem();
                // Toast.makeText(getApplicationContext(), "Course !"+selectedCourse.getCourseName()+"Selected", Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*****************Register Student Btn Click Logic****************************/
        //get Register Student Button
        Button registerStudent=(Button)findViewById(R.id.addStudent);

        registerStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //register student in a class logic added here
                String courseName=courseSelected.getCourseName();//considering course name unique
                String gender = genderSpinner.getSelectedItem().toString();
                String stuName=studentName.getText().toString();
                String fatheName=fatherName.getText().toString();
                String contact=contactNo.getText().toString();
                boolean enrolled=courseSelected.enrollInCourse(new Student(stuName,fatheName,gender,contact));
                if(enrolled)
                {
                    Toast.makeText(getApplicationContext(), "Enrolled Successfully!", Toast.LENGTH_SHORT ).show();
                    Intent intent=new Intent(RegisterStudent.this,options.class);
                    intent.putExtra("teacher_arr", teacher);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Student is Already Enrolled!", Toast.LENGTH_SHORT ).show();
                }
            }
        });


    }
}
