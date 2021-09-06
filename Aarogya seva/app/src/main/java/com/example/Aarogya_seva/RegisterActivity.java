package com.example.Aarogya_seva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText mTextName;
    EditText mTextEmail;
    EditText mTextMobile;
    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextCnfPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db=new DatabaseHelper(this);
        mTextName=(EditText)findViewById(R.id.edittext_name);
        mTextEmail=(EditText)findViewById(R.id.edittext_email);
        mTextMobile=(EditText)findViewById(R.id.edittext_mobile);
        mTextUsername=(EditText)findViewById(R.id.edittext_username);
        mTextPassword=(EditText)findViewById(R.id.edittext_password);
        mTextCnfPassword=(EditText)findViewById(R.id.edittext_conPassword);
        mButtonRegister=(Button) findViewById(R.id.button_register);
        mTextViewLogin=(TextView)findViewById(R.id.textview_login);
        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent=new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(LoginIntent);
            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=mTextUsername.getText().toString().trim();
                String password=mTextPassword.getText().toString().trim();
                String name=mTextName.getText().toString().trim();
                String email=mTextEmail.getText().toString().trim();
                String mobile_no=mTextMobile.getText().toString().trim();
                String cnf_password=mTextCnfPassword.getText().toString().trim();
                String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String passwordPattern="[a-zA-Z0-9_@]+";

                if (name.equals("")||email.equals("")||mobile_no.equals("")||username.equals("")||password.equals("")||cnf_password.equals(""))
                {
                    Toast.makeText(RegisterActivity.this, "Enter All The Fields", Toast.LENGTH_SHORT).show();
                }
                else
                    if (password.length()<8)
                    {
                        Toast.makeText(RegisterActivity.this, "Please enter 8 digit password", Toast.LENGTH_SHORT).show();
                    }
                else
                    if (!email.matches(emailPattern))
                    {
                        Toast.makeText(RegisterActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                    }
                else
                    if (!password.matches(passwordPattern))
                    {
                        Toast.makeText(RegisterActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                    }
                    else
                    if(mTextMobile.length()!=10)
                    {
                        Toast.makeText(RegisterActivity.this, "Invalid mobile number", Toast.LENGTH_SHORT).show();
                    }

                else {
                    if (password.equals(cnf_password)) {
                        long val = db.add_pat(name, email, mobile_no, username, password);
                        if (val > 0) {
                            Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            Intent LoginIntent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(LoginIntent);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Registeration Error", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(RegisterActivity.this, "Password is not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }


}
