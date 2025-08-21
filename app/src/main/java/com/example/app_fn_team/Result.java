package com.example.app_fn_team;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Result extends AppCompatActivity {

    TextView quizTitle, resultText, myak, myakm;
    Button myhbt, myqbt, myibt, myrbt, myrmbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);

        // กำหนดค่า View
        quizTitle = findViewById(R.id.quizTitle);
        resultText = findViewById(R.id.resultText);
        myak = findViewById(R.id.ak);
        myakm = findViewById(R.id.akm);
        myhbt = findViewById(R.id.hbt);
        myqbt = findViewById(R.id.qbt);
        myibt = findViewById(R.id.ibt);
        myrbt = findViewById(R.id.rbt);
        myrmbt = findViewById(R.id.rmbt);

        // 1) รับค่าจาก Intent ลงตัวแปร tempName (ธรรมดา)
        String tempName = getIntent().getStringExtra("quizName");
        int totalScore = getIntent().getIntExtra("totalScore", 0);

        // 2) ตรวจสอบถ้า tempName เป็น null → ปรับเป็น "ไม่พบข้อมูลแบบทดสอบ"
        if (tempName == null) {
            tempName = "ไม่พบข้อมูลแบบทดสอบ";
        }

        // 3) ประกาศตัวแปร final quizName จาก tempName
        final String quizName = tempName;

        // ------------------- เริ่มใช้งาน quizName (final) -------------------

        // กำหนดข้อความสำหรับระดับภาวะซึมเศร้า
        String resultMessage = "";
        String resulttt = "";

        // ตรวจสอบว่าเป็นแบบทดสอบไหน แล้วกำหนดช่วงคะแนนที่เหมาะสม
        if (quizName.equals("แบบทดสอบโรคซึมเศร้า")) {
            if (totalScore <= 9) {
                resultMessage = "ไม่มีอาการของโรคซึมเศร้า";
            } else if (totalScore >= 10 && totalScore <= 14) {
                resultMessage = "มีอาการของโรคซึมเศร้าเล็กน้อย";
            } else if (totalScore >= 15 && totalScore <= 20) {
                resultMessage = "มีอาการของโรคซึมเศร้าปานกลาง";
            } else {
                resultMessage = "มีอาการของโรคซึมเศร้าระดับรุนแรง";
            }
        }
        else if (quizName.equals("แบบทดสอบโรควิตกกังวล")) {
            if (totalScore <= 6) {
                resultMessage = "คุณไม่มีความวิตกกังวล";
            } else if (totalScore >= 7 && totalScore <= 13) {
                resultMessage = "คุณมีอาการของโรควิตกกังวลในระดับน้อย";
            } else if (totalScore >= 14 && totalScore <= 20) {
                resultMessage = "คุณมีอาการของโรควิตกกังวลในระดับกลาง";
                resulttt = "เราแนะนำให้ทำการประเมินเพิ่มเติมกับจิตแพทย์";
            } else {
                resultMessage = "คุณมีอาการของโรควิตกกังวลในระดับรุนแรง";
                resulttt = "เราแนะนำให้เข้าพบจิตแพทย์เพื่อปรึกษาและรับการรักษา";
            }
        }
        else if (quizName.equals("แบบทดสอบโรคแพนิค")) {
            if (totalScore < 7) {
                resultMessage = "คุณไม่มีอาการของโรคแพนิค";
                resulttt = "* เกณฑ์นี้สำหรับผู้ที่ไม่มีอาการกลัวที่โล่ง *";
            } else if (totalScore >= 7 && totalScore <= 14) {
                resultMessage = "คุณมีอาการของโรควิตกกังวลในระดับน้อย";
                resulttt = "* เกณฑ์นี้สำหรับผู้ที่ไม่มีอาการกลัวที่โล่ง *";
            } else {
                resultMessage = "คุณมีอาการของโรควิตกกังวลในระดับรุนแรง";
                resulttt = "* เกณฑ์นี้สำหรับผู้ที่ไม่มีอาการกลัวที่โล่ง *";
            }
        }
        else if (quizName.equals("แบบทดสอบความเครียด")) {
            if (totalScore <= 10) {
                resultMessage = "ไม่มีความเครียด";
            } else if (totalScore >= 11 && totalScore <= 19) {
                resultMessage = "คุณมีความเครียดในระดับต่ำ";
            } else if (totalScore >= 20 && totalScore <= 29) {
                resultMessage = "คุณมีความเครียดในปานกลาง";
            } else {
                resultMessage = "คุณมีความเครียดในระดับสูง";
                resulttt = "* เราแนะนำให้ปรึกษากับจิตแพทย์เนื่องจากมันสามารถช่วยให้คุณเข้าใจตัวเองมากขึ้นและหาทางจัดการที่เหมาะสม";
            }
        }
        else {
            resultMessage = "ไม่พบข้อมูลของแบบทดสอบนี้";
        }

        // แสดงผล
        quizTitle.setText(quizName);
        resultText.setText("คะแนนรวมของคุณคือ : " + totalScore);
        myak.setText(resultMessage);
        myakm.setText(resulttt);

        // ------------------- ตั้งค่า OnClickListener ให้ปุ่มต่าง ๆ -------------------

        // ปุ่ม Home
        myhbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myintent);
            }
        });

        // ปุ่ม Mood Checker
        myqbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(getApplicationContext(), moodchecker.class);
                startActivity(myintent);
            }
        });

        // ปุ่ม About
        myibt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(getApplicationContext(), about.class);
                startActivity(myintent);
            }
        });

        // ปุ่ม Recommend
        myrbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://bangkokmentalhealthhospital.com/th/";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        // ปุ่ม READ MORE
        myrmbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Map จาก quizName → diseaseType
                String diseaseType = "";
                if (quizName.equals("แบบทดสอบโรคซึมเศร้า")) {
                    diseaseType = "โรคซึมเศร้า";
                } else if (quizName.equals("แบบทดสอบโรควิตกกังวล")) {
                    diseaseType = "โรควิตกกังวล";
                } else if (quizName.equals("แบบทดสอบโรคแพนิค")) {
                    diseaseType = "โรคแพนิค";
                } else if (quizName.equals("แบบทดสอบความเครียด")) {
                    diseaseType = "ความเครียด";
                } else {
                    diseaseType = "";
                }

                // ส่งค่า diseaseType ไปหน้า moodchecker
                Intent intent = new Intent(Result.this, moodchecker.class);
                intent.putExtra("diseaseType", diseaseType);
                startActivity(intent);
            }
        });
    }
}
