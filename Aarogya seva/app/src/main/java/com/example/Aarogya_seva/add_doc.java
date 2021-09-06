package com.example.Aarogya_seva;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class add_doc extends AppCompatActivity {
    DatabaseHelper db;
    EditText mtext_doc_password;
    EditText mtext_doc_name;
    EditText mtext_doc_email;
    EditText mtext_doc_mobile_no;
    EditText mtext_doc_clinic_address;
    Spinner mspinner_category;
    Button msubmit;
    Button mback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doc);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db=new DatabaseHelper(this);

        mtext_doc_password=(EditText)findViewById(R.id.edittext_doc_password);
        mtext_doc_name=(EditText)findViewById(R.id.edittext_doc_name);
        mtext_doc_email=(EditText)findViewById(R.id.edittext_doc_email);
        mtext_doc_mobile_no=(EditText)findViewById(R.id.edittext_doc_mobile_no);
        mtext_doc_clinic_address=(EditText)findViewById(R.id.edittext_clinic_address);
        mspinner_category=(Spinner)findViewById(R.id.spinner_category);
        msubmit=(Button)findViewById(R.id.button_submit);
        mback=(Button)findViewById(R.id.button_back);

        ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(add_doc.this,android.R.layout.simple_spinner_dropdown_item,getResources().getStringArray(R.array.doc_category));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mspinner_category.setAdapter(myAdapter);




        mback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(add_doc.this, "Back", Toast.LENGTH_SHORT).show();
                Intent intent_back=new Intent(add_doc.this,AdminHome.class);
                startActivity(intent_back);
            }
        });

        msubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String doc_email=mtext_doc_email.getText().toString().trim();
                String doc_password=mtext_doc_password.getText().toString().trim();
                String doc_name=mtext_doc_name.getText().toString().trim();
                String doc_mobile_no=mtext_doc_mobile_no.getText().toString().trim();
                String doc_clinic_address=mtext_doc_clinic_address.getText().toString().trim();
                String doc_category=mspinner_category.getSelectedItem().toString().trim();
                String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String passwordPattern="[a-zA-Z0-9_@]+";

            if(doc_password.equals("")||doc_name.equals("")||doc_mobile_no.equals("")||doc_clinic_address.equals("")||doc_email.equals(""))
            {
                Toast.makeText(add_doc.this,"ENTER ALL FIELDS",Toast.LENGTH_SHORT).show();
            }
            else
                if (!doc_email.matches(emailPattern))
                {
                    Toast.makeText(add_doc.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                }
                else
                if (doc_password.length()<8)
                {
                    Toast.makeText(add_doc.this, "Please enter 8 digit password", Toast.LENGTH_SHORT).show();
                }
                else
                if (!doc_password.matches(passwordPattern))
                {
                    Toast.makeText(add_doc.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                }
                else
                if(mtext_doc_mobile_no.length()!=10)
                {
                    Toast.makeText(add_doc.this, "Invalid mobile number", Toast.LENGTH_SHORT).show();
                }
                else {
                    long  add=db.add_doc( doc_password, doc_name, doc_mobile_no,  doc_clinic_address, doc_email, doc_category);
                    if(add > 0) {
                        Toast.makeText(add_doc.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        Intent addIntent = new Intent(add_doc.this, AdminHome.class);
                        startActivity(addIntent);
                    }
                    else {
                            Toast.makeText(add_doc.this, "Registeration Error", Toast.LENGTH_SHORT).show();
                    }


                }




            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Logout:
            Toast.makeText(add_doc.this, "Logged out", Toast.LENGTH_SHORT).show();
            Intent add_logout = new Intent(add_doc.this, StartActivity.class);
            startActivity(add_logout);
        }
        return super.onOptionsItemSelected(item);
    }


}
