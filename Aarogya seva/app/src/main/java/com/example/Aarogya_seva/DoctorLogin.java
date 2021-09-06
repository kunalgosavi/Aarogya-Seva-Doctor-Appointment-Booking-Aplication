package com.example.Aarogya_seva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DoctorLogin extends AppCompatActivity {
    Button mButtonLogin;
    EditText mTextUsername;
    EditText mTextPassword;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);
        db=new DatabaseHelper(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mTextUsername=(EditText)findViewById(R.id.edittext_user);
        mTextPassword=(EditText)findViewById(R.id.edittext_pass);
        mButtonLogin=(Button)findViewById(R.id.button_doctorlogin);

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=mTextUsername.getText().toString().trim();
                String password=mTextPassword.getText().toString().trim();
                Boolean res=db.checkDoctor(email,password);
                if(res==true)
                {
                    Toast.makeText(DoctorLogin.this,"Successfully Logged In",Toast.LENGTH_SHORT).show();
                    Intent Doc_Home=new Intent(DoctorLogin.this,DoctorHome.class);
                    startActivity(Doc_Home);
                }
                else
                {
                    Toast.makeText(DoctorLogin.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
