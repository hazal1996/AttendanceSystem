package com.example.mehreenathar.attendancesystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class loginteacher extends AppCompatActivity {
    public Teacher loggedInTeacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginteacher);
        //get data from interface
        final Button loginButton=(Button)findViewById(R.id.loginBtn);
        final EditText email=(EditText)findViewById(R.id.name);
        final EditText password=(EditText)findViewById(R.id.password) ;
        TextView signUpClick=(TextView)findViewById(R.id.signup);//access TextView signup

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //confirm login credentials
                Teacher teacher=(Teacher)getIntent().getSerializableExtra("teacher_arr");
               String tEmail=email.getText().toString();
               String tPassword=password.getText().toString();

                if(teacher!=null)
                {
                        if(tEmail.equals(teacher.getEmail()) && tPassword.equals(teacher.getPassword()))
                        {
                            loggedInTeacher=new Teacher();
                            loggedInTeacher=teacher;
                            //Teacher found amongst registered teachers
                            Toast.makeText(getApplicationContext(), "Logged In Successfully!", Toast.LENGTH_SHORT ).show();
                            Intent intent=new Intent(loginteacher.this,options.class);
                            intent.putExtra("teacher_arr", teacher);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Login Failed!", Toast.LENGTH_SHORT ).show();
                            Intent redirectToItself=new Intent(loginteacher.this,loginteacher.class);
                        }

                }

            }
        });

        //when user clicks "Signup" on LoginTeacher page,redirect him/her to SignUp Teacher page
        signUpClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent redirectToSignUpTeacher=new Intent(loginteacher.this,signupteacher.class);
            startActivity(redirectToSignUpTeacher);
            }
        });
        //Bundle b = getIntent().getExtras();


    }
}
