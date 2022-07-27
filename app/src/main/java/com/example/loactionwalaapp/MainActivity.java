package com.example.loactionwalaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button openmaps =(Button) findViewById(R.id.locationbtn);



        openmaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmaps();
            }
        });

    }

    void openmaps(){
        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);
    }
}