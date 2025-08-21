package com.example.app_fn_team;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button mybt1,mybt2,mybt3,mybt4,myhbt,myqbt,myibt,myrbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mybt1 = findViewById(R.id.bt1);
        mybt2 = findViewById(R.id.bt2);
        mybt3 = findViewById(R.id.bt3);
        mybt4 = findViewById(R.id.bt4);
        myhbt = findViewById(R.id.hbt);
        myqbt = findViewById(R.id.qbt);
        myibt = findViewById(R.id.ibt);
        myrbt = findViewById(R.id.rbt);

        mybt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(getApplicationContext(), Quiz1.class);
                startActivity(myintent);

            }
        });

        mybt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(getApplicationContext(), Quiz2.class);
                startActivity(myintent);

            }
        });

        mybt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(getApplicationContext(), Quiz3.class);
                startActivity(myintent);

            }
        });

        mybt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(getApplicationContext(), Quiz4.class);
                startActivity(myintent);

            }
        });

        myhbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myintent);

            }
        });

        myqbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(getApplicationContext(), moodchecker.class);
                startActivity(myintent);

            }
        });

        myibt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(getApplicationContext(), about.class);
                startActivity(myintent);

            }
        });

        myrbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://bangkokmentalhealthhospital.com/th/";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}