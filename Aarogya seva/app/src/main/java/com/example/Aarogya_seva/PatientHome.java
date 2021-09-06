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

public class PatientHome extends AppCompatActivity {
    DatabaseHelper db;
    LinearLayout viewapp,maddApp;
    EditText musern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        musern=(EditText) findViewById(R.id.edittext_patName);
        viewapp=(LinearLayout)findViewById(R.id.button_pat_app);
        maddApp=(LinearLayout) findViewById(R.id.button_add_appointment);
        db = new DatabaseHelper(this);
        viewAppoint();

        maddApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add_app=new Intent(PatientHome.this,AddAppointment.class);
                startActivity(add_app);
            }
        });
    }

    private void viewAppoint() {


        viewapp.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                String usernameP=musern.getText().toString().trim();

                if (usernameP.equals("")){
                    Toast.makeText(PatientHome.this, "Please enter your username", Toast.LENGTH_SHORT).show();

                }
                else {
                    Cursor add = db.getAppDetail(usernameP);
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
        Toast.makeText(PatientHome.this, "Logged out", Toast.LENGTH_SHORT).show();
        Intent add_logout = new Intent(PatientHome.this, StartActivity.class);
        startActivity(add_logout);
        return super.onOptionsItemSelected(item);
    }
}
