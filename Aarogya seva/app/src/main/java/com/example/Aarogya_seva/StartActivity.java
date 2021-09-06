package com.example.Aarogya_seva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class StartActivity extends AppCompatActivity {
    LinearLayout mButtonAdmin;
    LinearLayout mButtonPatient;
    LinearLayout mButtonDoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mButtonAdmin=(LinearLayout) findViewById(R.id.button_admin);
        mButtonPatient=(LinearLayout) findViewById(R.id.button_patient);
        mButtonDoctor=(LinearLayout) findViewById(R.id.button_doctor);

        mButtonPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent patient=new Intent(StartActivity.this,MainActivity.class);
                startActivity(patient);


            }
        });

        mButtonAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent admin=new Intent(StartActivity.this,AdminLogin.class);
                startActivity(admin);


            }
        });

        mButtonDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent doctor=new Intent(StartActivity.this,DoctorLogin.class);
                startActivity(doctor);
            }
        });
    }
}
