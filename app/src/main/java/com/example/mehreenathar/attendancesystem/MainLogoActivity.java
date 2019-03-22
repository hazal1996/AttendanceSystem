package com.example.mehreenathar.attendancesystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainLogoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_logo);
        Button logoName=(Button) findViewById(R.id.button);

        //put an onClick listener on TextView(Frequenza)
        logoName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create an Intent for redirecting to Login Teacher Screen Upon Logo Name Click
                Intent redirectToTeacherLogin=new Intent(MainLogoActivity.this,loginteacher.class);
            startActivity(redirectToTeacherLogin);
            }
        });
    }
}
