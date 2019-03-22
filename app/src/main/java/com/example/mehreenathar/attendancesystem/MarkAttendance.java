package com.example.mehreenathar.attendancesystem;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MarkAttendance extends AppCompatActivity {
    private TextView displayDate;
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private ArrayList<Course> courses;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Student> get_students;
    private Course getCourseObject;
    private Teacher teacher;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);
        /*****************Recycler View SetUP**********************************/
        getCourseObject = (Course) getIntent().getSerializableExtra("courseSelected");
        teacher=(Teacher)getIntent().getSerializableExtra("teacher_arr");
        get_students = getCourseObject.getEnrolledStudents();
        Button submit = (Button) findViewById(R.id.submit);
        Button shortBtn=(Button)findViewById(R.id.shortBtn);
        recyclerView = (RecyclerView) findViewById(R.id.student_list);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        ShapeDrawable shapeDrawableForDivider = new ShapeDrawable(new RectShape());
        int dividerThickness = 25;// (int) (SomeOtherView.getHeight() * desiredPercent);
        shapeDrawableForDivider.setIntrinsicHeight(dividerThickness);
        shapeDrawableForDivider.setAlpha(0);
        dividerItemDecoration.setDrawable(shapeDrawableForDivider);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new MyAdapter(this, get_students);
        recyclerView.setAdapter(mAdapter);


        /*********************Short Attendance SMS Send BTN***************************************/

        shortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Student> shortAttendees = getCourseObject.shortAttendance();
                //send sms to all these short attendees
                /*Intent sendSms=new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+shortAttendees));
                sendSms.putExtra("sms_body","Dear Student Your Attendance is Running Short In "+getCourseObject.getCourseName());
                startActivity(sendSms);*/
                ArrayList<String> get_contact_numbers = getCourseObject.get_shortAttendance();
                String short_att = new String();
                String add_another = ";";
                int count = 0;
                for (int i = 0; i < get_contact_numbers.size(); i++) {
                    if (count == 0) {
                        String arr = get_contact_numbers.get(i);
                        short_att = arr;
                        count = 1;
                    } else {
                        short_att = short_att.concat(";");
                        short_att = short_att.concat(get_contact_numbers.get(i));
                        // short_att=get_contact_numbers.get(i);
                    }
                }

                //  String mblNumVar = "+923328472236";
                Intent smsMsgAppVar = new Intent(Intent.ACTION_VIEW);
                smsMsgAppVar.setData(Uri.parse("sms:" + short_att));
                smsMsgAppVar.putExtra("sms_body", "This is a warning message for subject " + getCourseObject.getCourseName() + " As your attendence is falling below the requirement");
                startActivity(smsMsgAppVar);
            }
        });
        /*********************Submit btn Click****************************************************/
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //not reached last
                //do whatever with that student obj
                for (int j = 0; j < get_students.size(); j++) {
                    boolean marked = false;
                    for (int k = 0; k < mAdapter.getMarkedStudents().size(); k++) {
                        Student current = mAdapter.getMarkedStudents().get(k);
                        if (current == get_students.get(j))//mark this student present as his checkbox is checked
                        {
                            Date date=null;
                            String d=displayDate.getText().toString();
                            try{
                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // here set the pattern as you date in string was containing like date/month/year
                                date= sdf.parse(d);
                            }catch(ParseException ex){
                                // handle parsing exception if date string was different from the pattern applying into the SimpleDateFormat contructor
                            }
                            boolean mark = current.markAttendance(new Attendance("P", date, true));
                            marked = true;
                            Toast.makeText(getApplicationContext(), current.getStudentName() + "Marked Present!", Toast.LENGTH_LONG).show();

                        }
                    }
                    if (!marked)//if student checkbox wasnt checked mark him/her absent
                    {
                        Date date = new Date();
                        get_students.get(j).markAttendance(new Attendance("A", date, true));
                        Toast.makeText(getApplicationContext(), get_students.get(j).getStudentName() + "Marked Absent!", Toast.LENGTH_LONG).show();

                    }
                }
                Intent intent=new Intent(MarkAttendance.this,selectCourseToMarkAttendance.class);
                /*add updated student values in original teacher object*/
                teacher.updateCourseObjectAfterAttendanceMark(getCourseObject);
                intent.putExtra("teacher_arr", teacher);
                intent.putExtra("courseSelected",getCourseObject);
                setResult(Activity.RESULT_OK,intent);
                finish();

            }
        });
        /**********************SEND SMS ON BTN CLICK***************************/
        /*
        String mblNumVar = "+923328472236";
        Intent smsMsgAppVar = new Intent(Intent.ACTION_VIEW);
        smsMsgAppVar.setData(Uri.parse("sms:" +  mblNumVar));
        smsMsgAppVar.putExtra("sms_body", "This is a warning message");
        startActivity(smsMsgAppVar);
         */

        /*****************************GET COURSE SELECTED HERE****************/
        Teacher teacher=(Teacher)getIntent().getSerializableExtra("teacher_arr");
        Course selectedCourse=(Course)getIntent().getSerializableExtra("courseSelected");


        /****************DATE PICKER LOGIC***************************************/
        displayDate=(TextView)findViewById(R.id.datePicker);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy / MM / dd ");
        String date = mdformat.format(calendar.getTime());
        displayDate.setText(date);
        //put listener on textView so Whenever user click textbox,date picker activated
        displayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create a Calendar object
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog=new DatePickerDialog(MarkAttendance.this,
                        android.R.style.Theme_Material_Dialog_MinWidth,dateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });
        dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String  finalDateShown=dayOfMonth+"/"+month+"/"+year;
                displayDate.setText(finalDateShown);

            }
        };
    }

}
