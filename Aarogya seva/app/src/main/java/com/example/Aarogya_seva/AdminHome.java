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
import android.widget.LinearLayout;
import android.widget.Toast;

public class AdminHome extends AppCompatActivity {
    DatabaseHelper db;
    LinearLayout madd_doc;
    LinearLayout mdoctor;
    LinearLayout mpatient;
    LinearLayout mappointment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        db = new DatabaseHelper(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        madd_doc = (LinearLayout) findViewById(R.id.button_add_doc);
        mdoctor = (LinearLayout) findViewById(R.id.button_doc_detail);
        mpatient = (LinearLayout) findViewById(R.id.button_pat_detail);
        mappointment = (LinearLayout) findViewById(R.id.button_view_appointment);

        viewDoctor();
        viewPatient();
        viewAppointment();


        madd_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add_doc = new Intent(AdminHome.this, add_doc.class);
                startActivity(add_doc);
            }
        });

    }

    private void viewPatient() {
        mpatient.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Cursor add = db.getPatientDetail();
                if (add.getCount() == 0) {
                    showmessage("error", "no data found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (add.moveToNext()) {

                    buffer.append("Name     : " + add.getString(0) + "\n");
                    buffer.append("Email    : " + add.getString(1) + "\n");
                    buffer.append("Mobile No: " + add.getString(2) + "\n");
                    buffer.append("Username : " + add.getString(3) + "\n");
                    buffer.append("Password : " + add.getString(4) + "\n\n");

                }
                showmessage("Patient Details", buffer.toString());

            }



        });


    }

    private void viewDoctor() {


        mdoctor.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Cursor add = db.getDoctorDetail();
                if (add.getCount() == 0) {
                    showmessage("error", "no data found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (add.moveToNext()) {
                    buffer.append("email        : " + add.getString(0) + "\n");
                    buffer.append("password     : " + add.getString(1) + "\n");
                    buffer.append("name         : " + add.getString(2) + "\n");
                    buffer.append("mobile_no    : " + add.getString(3) + "\n");
                    buffer.append("clinic_address: " + add.getString(4) + "\n");
                    buffer.append("category     : " + add.getString(5) + "\n\n");

                }
                showmessage("Doctor detials", buffer.toString());



            }


        });


    }

    private void viewAppointment() {


        mappointment.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Cursor add = db.getAppointmentDetail();
                if (add.getCount() == 0) {
                    showmessage("error", "no data found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (add.moveToNext()) {

                    buffer.append("username : " + add.getString(0) + "\n");
                    buffer.append("category : " + add.getString(1) + "\n");
                    buffer.append("doctor   : " + add.getString(2) + "\n");
                    buffer.append("date     : " + add.getString(3) + "\n");
                    buffer.append("time     : " + add.getString(4) + "\n\n");

                }
                showmessage("Appointment details", buffer.toString());


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
        Toast.makeText(AdminHome.this, "Logged out", Toast.LENGTH_SHORT).show();
        Intent add_logout = new Intent(AdminHome.this, StartActivity.class);
        startActivity(add_logout);
        return super.onOptionsItemSelected(item);
    }
}
