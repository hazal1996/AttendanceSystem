package com.example.mehreenathar.attendancesystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class signupteacher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupteacher);
        //declared as final so it can be accessed inside the listener
        final int teacherId=1;
        final ArrayList<Teacher> teacher = new ArrayList<Teacher>();
        final Button signUpBtn=(Button)findViewById(R.id.signUp);
        final EditText name=(EditText)findViewById(R.id.fullName);
        final EditText email=(EditText)findViewById(R.id.name);
        final EditText password=(EditText)findViewById(R.id.password);
        final EditText confirmPassword=(EditText)findViewById(R.id.confirmPassword);//ignore for now

        //upon signUp Button click add Teacher to ArrayList
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().toString().equals(confirmPassword.getText().toString()) )//display TOAST
                {
                    //TOAST displays popup message ...SYNTAX=Toast.makeText(context, text, duration);
                    Toast.makeText(getApplicationContext(), "Password Confirmed!", Toast.LENGTH_LONG ).show();
                    String tname=name.getText().toString();//gotta convert editText data to string type
                    String temail=email.getText().toString();
                    String tpassword=password.getText().toString();
                    // tname!=null && temail!=null && tpassword!=null &&
                    if( password.getText().toString().equals(confirmPassword.getText().toString()))
                    {
                        Teacher teacher=new Teacher(tname,tpassword,temail);
                       // teacher.add(new Teacher(tname,tpassword,temail));
                        Intent intent = new Intent(signupteacher.this, loginteacher.class);
                        intent.putExtra("teacher_arr", teacher);
                        Toast.makeText(getApplicationContext(), "Registered Successfully!", Toast.LENGTH_SHORT ).show();
                        startActivity(intent);
                    }
                    else
                    {
                        //all fields not entered error
                        Toast.makeText(getApplicationContext(), "Password Mismatch!", Toast.LENGTH_SHORT ).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Password Not Match!", Toast.LENGTH_LONG ).show();
                }


            }
        });

    }
}
