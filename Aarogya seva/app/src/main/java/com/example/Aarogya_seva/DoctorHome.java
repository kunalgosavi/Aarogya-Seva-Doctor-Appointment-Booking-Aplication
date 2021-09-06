package com.example.Aarogya_seva;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class DoctorHome extends AppCompatActivity {

    LinearLayout mapp;
    EditText mdName;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);
        db = new DatabaseHelper(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mapp=(LinearLayout) findViewById(R.id.button_view_app);
        mdName=(EditText) findViewById(R.id.edittext_dName);
        viewDoctorAppointment();
    }

    private void viewDoctorAppointment() {


        mapp.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                String doctor=mdName.getText().toString().trim();
                if (doctor.equals("")){
                    Toast.makeText(DoctorHome.this, "Please enter your name", Toast.LENGTH_SHORT).show();

                }
                else {
                    Cursor add = db.getDoctorAppointmentDetail(doctor);
                    if (add.getCount() == 0) {
                        showmessage("error", "no data found");
                        return;
                    }
                    StringBuffer buffer = new StringBuffer();
                    while (add.moveToNext()) {

                        buffer.append("Patient Name: " + add.getString(0) + "\n");
                        buffer.append("Category    : " + add.getString(1) + "\n");
                        buffer.append("Doctor      : " + add.getString(2) + "\n");
                        buffer.append("Date        : " + add.getString(3) + "\n");
                        buffer.append("Time        : " + add.getString(4) + "\n\n");

                    }
                    showmessage("Appointments", buffer.toString());

                }
            }


        });
    }

    private void showmessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(DoctorHome.this, "Logged out", Toast.LENGTH_SHORT).show();
        Intent add_logout = new Intent(DoctorHome.this, StartActivity.class);
        startActivity(add_logout);
        return super.onOptionsItemSelected(item);
    }
}
