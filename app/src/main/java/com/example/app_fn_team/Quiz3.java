package com.example.app_fn_team;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Quiz3 extends AppCompatActivity {

    TextView myq1;
    RadioGroup myrg1, myrg2, myrg3, myrg4, myrg5, myrg6, myrg7;
    Button myhbt,myqbt,myibt,myrbt,mysmbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz3);

        myq1 =findViewById(R.id.q1);

        myhbt = findViewById(R.id.hbt);
        myqbt = findViewById(R.id.qbt);
        myibt = findViewById(R.id.ibt);
        myrbt = findViewById(R.id.rbt);
        mysmbt = findViewById(R.id.rmbt);

        myrg1 = findViewById(R.id.rg1);
        myrg2 = findViewById(R.id.rg2);
        myrg3 = findViewById(R.id.rg3);
        myrg4 = findViewById(R.id.rg4);
        myrg5 = findViewById(R.id.rg5);
        myrg6 = findViewById(R.id.rg6);
        myrg7 = findViewById(R.id.rg7);

        mysmbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int totalScore = getSelectedValue(myrg1)+ getSelectedValue(myrg2)+
                        getSelectedValue(myrg3)+ getSelectedValue(myrg4)+
                        getSelectedValue(myrg5)+ getSelectedValue(myrg6)+
                        getSelectedValue(myrg7);

                // ถ้าไม่ได้เลือกค่าจากทั้งสองกลุ่ม ให้แจ้งเตือน
                if (getSelectedValue(myrg1) == -1 || getSelectedValue(myrg2) == -1
                        || getSelectedValue(myrg3) == -1 || getSelectedValue(myrg4) == -1
                        || getSelectedValue(myrg5) == -1 || getSelectedValue(myrg6) == -1
                        || getSelectedValue(myrg7) == -1) {
                    Toast.makeText(Quiz3.this, "กรุณาเลือกทุกข้อก่อนส่ง", Toast.LENGTH_SHORT).show();
                    return;
                }

                // ดึงค่าชื่อแบบทดสอบจาก TextView
                String quizName = myq1.getText().toString();

                // แสดงผล
                Toast.makeText(Quiz3.this, "ท่านได้ส่งแบบทดสอบแล้ว", Toast.LENGTH_SHORT).show();

                // ส่งค่าไปหน้า ResultActivity
                Intent intent = new Intent(Quiz3.this, Result.class);
                intent.putExtra("quizName", quizName); // ส่งชื่อแบบทดสอบ
                intent.putExtra("totalScore", totalScore); // ส่งคะแนนรวม
                startActivity(intent);

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
    private int getSelectedValue(RadioGroup radioGroup) {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId == -1) {
            return -1; // ถ้าไม่มีตัวเลือกที่ถูกเลือก
        }
        RadioButton selectedRadioButton = findViewById(selectedId);
        try {
            // ใช้ tag แทนการใช้ text เพราะ tag มีค่าตัวเลข
            return Integer.parseInt(selectedRadioButton.getTag().toString());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}