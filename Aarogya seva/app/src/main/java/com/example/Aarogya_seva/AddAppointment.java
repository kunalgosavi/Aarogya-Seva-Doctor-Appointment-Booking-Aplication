package com.example.Aarogya_seva;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AddAppointment extends AppCompatActivity {
   EditText mappID;
   EditText musername;
   TextView mtime;
   Spinner mspinner_appcategory;
   Spinner mspinner_doctor;
   CalendarView mcalender;
   TextView mdate;
   Button mbt1;
   Button mbt2;
   Button mbt3;
   Button mbt4;
   Button mbt5;
   Button mbt6;
   Button madd;
   DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);
        mspinner_appcategory=(Spinner)findViewById(R.id.spinner_appCategory);
        mspinner_doctor=(Spinner)findViewById(R.id.spinner_appDoctor);
        mcalender=(CalendarView) findViewById(R.id.calendarView);
        mdate=(TextView) findViewById(R.id.textview_appDate);
        mtime=(TextView) findViewById(R.id.textview_apptime);
        musername=(EditText) findViewById(R.id.edittext_appUsername);
        mbt1=(Button) findViewById(R.id.button_t1);
        mbt2=(Button) findViewById(R.id.button_t2);
        mbt3=(Button) findViewById(R.id.button_t3);
        mbt4=(Button) findViewById(R.id.button_t4);
        mbt5=(Button) findViewById(R.id.button_t5);
        mbt6=(Button) findViewById(R.id.button_t6);
        madd=(Button) findViewById(R.id.button_add);
        db=new DatabaseHelper(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(AddAppointment.this,android.R.layout.simple_spinner_dropdown_item,getResources().getStringArray(R.array.doc_category));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mspinner_appcategory.setAdapter(myAdapter);

        mspinner_appcategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loadSpinnerData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });
        mcalender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                String date=i2+"/"+(i1+1)+"/"+i;
                String bt1="10:00-10:30am";
                String bt2="10:30-11:00am";
                String bt3="11:00-11:30am";
                String bt4="05:00-05:30pm";
                String bt5="05:30-06:00pm";
                String bt6="06:00-06:30pm";

                mdate.setText(date);
                if (db.checkTime(date,bt1))
                {
                    mbt1.setEnabled(false);
                }
                else
                {
                    mbt1.setEnabled(true);
                }

                if (db.checkTime(date,bt2))
                {
                    mbt2.setEnabled(false);
                }
                else
                {
                    mbt2.setEnabled(true);
                }
                if (db.checkTime(date,bt3))
                {
                    mbt3.setEnabled(false);
                }
                else
                {
                    mbt3.setEnabled(true);
                }
                if (db.checkTime(date,bt4))
                {
                    mbt4.setEnabled(false);
                }
                else
                {
                    mbt4.setEnabled(true);
                }
                if (db.checkTime(date,bt5))
                {
                    mbt5.setEnabled(false);
                }
                else
                {
                    mbt5.setEnabled(true);
                }
                if (db.checkTime(date,bt6))
                {
                    mbt6.setEnabled(false);
                }
                else
                {
                    mbt6.setEnabled(true);
                }
            }
        });



        mbt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String time="10:00-10:30am";
                mtime.setText(time);

            }
        });

        mbt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String time="10:30-11:00am";
                mtime.setText(time);

            }
        });

        mbt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String time="11:00-11:30am";
                mtime.setText(time);

            }
        });

        mbt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String time="05:00-05:30pm";
                mtime.setText(time);

            }
        });

        mbt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String time="05:30-06:00pm";
                mtime.setText(time);

            }
        });

        mbt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String time="06:00-06:30pm";
                mtime.setText(time);

            }
        });


        madd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=musername.getText().toString().trim();
                String category=mspinner_appcategory.getSelectedItem().toString().trim();
                String doctor=mspinner_doctor.getSelectedItem().toString().trim();
                String date=mdate.getText().toString().trim();
                String time=mtime.getText().toString().trim();

                if (username.equals("")||category.equals("")||doctor.equals(null)||username.equals("")||date.equals("")||time.equals(""))
                {
                    Toast.makeText(AddAppointment.this, "Enter All The Fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    long insertapp = db.add_appointment(username, category, doctor, date, time);
                    if (insertapp > 0) {
                    /*Toast.makeText(AddAppointment.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                    Intent goIntent = new Intent(AddAppointment.this, PatientHome.class);
                    startActivity(goIntent);*/

                        AlertDialog.Builder builder = new AlertDialog.Builder(AddAppointment.this);
                        View view1 = LayoutInflater.from(AddAppointment.this).inflate(R.layout.custom_dailog, null);

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent goIntent = new Intent(AddAppointment.this, PatientHome.class);
                                startActivity(goIntent);
                            }
                        });

                        builder.setView(view1);
                        builder.show();
                    } else {

                        Toast.makeText(AddAppointment.this, "Registeration Error", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

    public void loadSpinnerData(){
        DatabaseHelper db=new DatabaseHelper(getApplicationContext());
        String category=mspinner_appcategory.getSelectedItem().toString().trim();
        List<String> labels=db.getAllLabels(category);

        ArrayAdapter<String> dataAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,labels);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mspinner_doctor.setAdapter(dataAdapter);
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
                Toast.makeText(AddAppointment.this, "Logged out", Toast.LENGTH_SHORT).show();
                Intent add_logout = new Intent(AddAppointment.this, StartActivity.class);
                startActivity(add_logout);
        }
        return super.onOptionsItemSelected(item);
    }

}
