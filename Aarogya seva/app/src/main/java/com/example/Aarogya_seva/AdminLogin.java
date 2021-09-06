package com.example.Aarogya_seva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {
    Button mButtonLogin;
    EditText mTextUsername;
    EditText mTextPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //getSupportActionBar().setBackgroundDrawable();
        mTextUsername=(EditText)findViewById(R.id.edittext_username);
        mTextPassword=(EditText)findViewById(R.id.edittext_password);
        mButtonLogin=(Button)findViewById(R.id.button_adminlogin);

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=mTextUsername.getText().toString().trim();
                String password=mTextPassword.getText().toString().trim();
                if (username.equals("admin")&&password.equals("admin@123"))
                {
                    Toast.makeText(AdminLogin.this, "successfully Logged In", Toast.LENGTH_SHORT).show();
                    Intent admin_home=new Intent(AdminLogin.this,AdminHome.class);
                    startActivity(admin_home);
                }
                else
                {
                        Toast.makeText(AdminLogin.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }


}
