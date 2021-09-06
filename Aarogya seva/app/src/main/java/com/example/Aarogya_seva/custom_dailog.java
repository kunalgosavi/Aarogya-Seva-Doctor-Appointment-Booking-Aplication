package com.example.Aarogya_seva;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class custom_dailog  extends AppCompatActivity {
   EditText mTexttitle;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dailog);
        View txt = findViewById(R.id.txt);
        
        
    }
}
