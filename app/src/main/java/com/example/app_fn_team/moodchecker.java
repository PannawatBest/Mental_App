package com.example.app_fn_team;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class moodchecker extends AppCompatActivity {

    // ปุ่มล่าง
    Button myhbt, myqbt, myibt, myrbt;

    // เพิ่มส่วนของ Spinner และ TextView
    Spinner spinnerMood;
    TextView textInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_moodchecker);

        // เชื่อมปุ่มล่าง
        myhbt = findViewById(R.id.hbt);
        myqbt = findViewById(R.id.qbt);
        myibt = findViewById(R.id.ibt);
        myrbt = findViewById(R.id.rbt);

        // เชื่อม Spinner + TextView
        spinnerMood = findViewById(R.id.spinnerMood);
        textInfo = findViewById(R.id.textInfo);

        // ----- ส่วนของ Spinner -----
        // สร้าง Array เก็บหัวข้อ
        final String[] moodItems = {
                "โรคซึมเศร้า",
                "โรควิตกกังวล",
                "โรคแพนิค",
                "ความเครียด"
        };

        // สร้าง Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                moodItems
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMood.setAdapter(adapter);

        // ===== รับค่าจากหน้า Result (ถ้ามี) =====
        String diseaseType = getIntent().getStringExtra("diseaseType");
        if (diseaseType != null) {
            // หาตำแหน่ง index ใน moodItems
            int index = -1;
            for (int i = 0; i < moodItems.length; i++) {
                if (moodItems[i].equals(diseaseType)) {
                    index = i;
                    break;
                }
            }
            // ถ้าเจอ index ให้ spinnerMood เลือก item นั้น
            if (index != -1) {
                spinnerMood.setSelection(index);
            }
        }
        // ===== จบส่วนรับค่า =====

        // ตั้งค่า onItemSelectedListener
        spinnerMood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // เรียกฟังก์ชันแสดงข้อมูล
                showMoodInfo(moodItems[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // ถ้าไม่มีอะไรถูกเลือก
            }
        });
        // ----- จบส่วนของ Spinner -----

        // ปุ่ม Home
        myhbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myintent);
            }
        });

        // ปุ่ม MoodChecker (กดแล้วจะอยู่หน้าเดิม - ถ้าต้องการ behavior อย่างอื่น ปรับได้)
        myqbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ถ้าไม่อยากให้เปิดหน้านี้ซ้ำ ก็ comment โค้ดนี้ออก
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

        // ปุ่ม Recommand (ไปเว็บ)
        myrbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://bangkokmentalhealthhospital.com/th/";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        // ถ้า layout มี id="main" อยู่จริง
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // ฟังก์ชันแสดงข้อมูลตามหัวข้อ
    private void showMoodInfo(String mood) {
        switch (mood) {
            case "โรคซึมเศร้า":
                textInfo.setText("โรคซึมเศร้า (Major Depressive Disorder: MDD) คืออะไร?\n" +
                        "โรคซึมเศร้าเป็นภาวะผิดปกติทางอารมณ์ที่เกิดจากสารเคมีในสมอง เช่น เซโรโทนิน (Serotonin) ลดลง ทำให้เกิดอาการทางร่างกาย จิตใจ และความคิด อาจเกิดจาก พันธุกรรม, การเปลี่ยนแปลงของฮอร์โมน, หรือ ความผิดปกติของสารเคมีในสมอง\n" +
                        "อาการของโรคซึมเศร้า\n" +
                        "โรคซึมเศร้ามีอาการที่รุนแรงและต่อเนื่องมากกว่าความเศร้าทั่วไป โดยอาจมีอาการเช่น:\n" +
                        "รู้สึกซึมเศร้า ว่างเปล่า หรือหมดความสนใจในกิจกรรมที่เคยชอบ\n" +
                        "น้ำหนักเปลี่ยนแปลงผิดปกติ เบื่ออาหารหรือกินมากขึ้น\n" +
                        "นอนไม่หลับหรือนอนมากเกินไป\n" +
                        "ไม่มีสมาธิ ตัดสินใจลำบาก\n" +
                        "มีความคิดทำร้ายตัวเองหรืออยากฆ่าตัวตาย\n" +
                        "หากมีความคิดทำร้ายตัวเอง ควรรีบพบแพทย์โดยด่วน\n" +
                        "เมื่อไหร่ควรพบแพทย์?\n" +
                        "หากมีอาการซึมเศร้าต่อเนื่องเกิน 2 สัปดาห์ และส่งผลกระทบต่อชีวิตประจำวัน เช่น การทำงานหรือความสัมพันธ์กับคนรอบข้าง ควรพบแพทย์เพื่อรับการวินิจฉัยและรักษา\n" +
                        "วิธีรักษาโรคซึมเศร้า\n" +
                        "จิตบำบัด & พฤติกรรมบำบัด เพื่อปรับแนวคิดและพฤติกรรม\n" +
                        "การใช้ยา เช่น ยากลุ่ม SSRIs, SNRIs, TCA, และ MAOI ซึ่งช่วยปรับสมดุลสารเคมีในสมอง\n" +
                        "หากอาการรุนแรง อาจต้องรักษาในโรงพยาบาล\n" +
                        "ประเภทของยาต้านเศร้า\n" +
                        "SSRIs – เพิ่มระดับเซโรโทนินในสมอง (เช่น Fluoxetine, Sertraline)\n" +
                        "SNRIs – เพิ่มทั้งเซโรโทนินและนอร์อิพิเนพฟรีน (เช่น Venlafaxine)\n" +
                        "TCA – ออกฤทธิ์ช้า มักใช้กรณีซึมเศร้ารุนแรง (เช่น Amitriptyline)\n" +
                        "MAOI – ยับยั้งเอนไซม์ที่ทำลายสารสื่อประสาท (มีข้อจำกัดเรื่องอาหารที่ต้องระวัง)\n" +
                        "สำคัญ: การใช้ยาต้องอยู่ภายใต้การดูแลของแพทย์ ห้ามซื้อยารับประทานเองเพราะอาจเกิดอันตรายได้");
                break;
            case "โรควิตกกังวล":
                textInfo.setText("โรควิตกกังวลทั่วไป (Generalized Anxiety Disorder - GAD) คืออะไร?\n" +
                        "โรควิตกกังวลทั่วไปเป็นภาวะที่มีความกังวลมากเกินปกติในหลายเรื่องเป็นเวลานาน ส่งผลกระทบต่อชีวิตประจำวัน อาจมีอาการทางกายร่วมด้วย เช่น อ่อนล้า ปวดเมื่อย เสียสมาธิ และปัญหาการนอน\n" +
                        "\n" +
                        "อาการของโรควิตกกังวลทั่วไป\n" +
                        "✔ เครียดและวิตกกังวลเรื้อรัง\n" +
                        "✔ ตื่นตระหนกง่าย รู้สึกไม่สบายใจตลอดเวลา\n" +
                        "✔ ไม่สามารถควบคุมความวิตกกังวลได้\n" +
                        "✔ อ่อนเพลียง่าย\n" +
                        "✔ ไม่มีสมาธิ สูญเสียความสามารถในการจดจ่อ\n" +
                        "✔ มีอาการทางกาย เช่น ปวดเมื่อย ปวดศีรษะเรื้อรัง ปวดท้องโดยไม่ทราบสาเหตุ\n" +
                        "✔ มีปัญหาการนอน\n" +
                        "\n" +
                        "หากอาการเป็นนาน เกิน 6 เดือน และไม่ได้เกิดจากโรคทางกายอื่น ควรปรึกษาแพทย์\n" +
                        "\n" +
                        "เมื่อไหร่ควรพบแพทย์?\n" +
                        "หากความวิตกกังวลรบกวนชีวิตประจำวัน, การทำงาน หรือความสัมพันธ์\n" +
                        "มีอาการซึมเศร้าร่วม หรือมีผลกระทบต่อสุขภาพกาย\n" +
                        "ภาวะฉุกเฉิน\n" +
                        "หากมีภาวะซึมเศร้าร่วม หรือมีความคิดอยากทำร้ายตัวเอง ควรรีบพบแพทย์ทันที\n" +
                        "\n" +
                        "การวินิจฉัย\n" +
                        "แพทย์จะประเมินจากประวัติอาการ แบบทดสอบทางจิตวิทยา ตรวจร่างกาย และอาจมีการตรวจเพิ่มเติมเพื่อแยกโรคทางกายที่อาจคล้ายกัน\n" +
                        "\n" +
                        "การรักษา\n" +
                        "✅ การใช้ยา – ปรับสมดุลสารเคมีในสมอง\n" +
                        "✅ จิตบำบัด & พฤติกรรมบำบัด – ฝึกจัดการความวิตกกังวล\n" +
                        "✅ การรักษาต่อเนื่อง – อาการดีขึ้นได้เมื่อได้รับการดูแลอย่างเหมาะสม\n" +
                        "\n" +
                        "หากมีอาการวิตกกังวลที่รบกวนชีวิต อย่าลังเลที่จะขอคำปรึกษาจากแพทย์หรือผู้เชี่ยวชาญด้านสุขภาพจิต \uD83D\uDC99 \n");
                break;
            case "โรคแพนิค":
                textInfo.setText("โรคแพนิค (Panic Disorder) คืออะไร?\n" +
                        "โรคแพนิคเป็นภาวะที่ร่างกายเกิด ความตื่นตระหนก วิตกกังวล หรือกลัวอย่างรุนแรง กับสถานการณ์ที่อาจไม่เป็นอันตราย อาการเกิดขึ้นเฉียบพลันและเป็นพัก ๆ ส่งผลกระทบต่อการใช้ชีวิต เช่น ขับรถ ข้ามถนน หรือการทำงาน\n" +
                        "\n" +
                        "สาเหตุของโรคแพนิค\n" +
                        "✔ ความผิดปกติของสมอง – สมองส่วนควบคุมความกลัวทำงานผิดปกติ\n" +
                        "✔ ความไม่สมดุลของสารเคมีในสมอง\n" +
                        "✔ กรรมพันธุ์\n" +
                        "✔ การใช้สารเสพติด\n" +
                        "✔ ความเครียดสะสม\n" +
                        "✔ ประสบการณ์ร้ายแรงในอดีต ที่ทิ้งบาดแผลทางใจ\n" +
                        "\n" +
                        "อาการของโรคแพนิค\n" +
                        "✔ หัวใจเต้นเร็ว ใจสั่น แน่นหน้าอก\n" +
                        "✔ เวียนหัว เป็นลม\n" +
                        "✔ คลื่นไส้ ท้องไส้ปั่นป่วน\n" +
                        "✔ เหงื่อแตก มือเท้าชา ตัวร้อนวูบวาบ\n" +
                        "✔ หายใจติดขัด เหมือนมีอะไรจุกในลำคอ\n" +
                        "✔ กลัวตาย กลัวควบคุมตัวเองไม่ได้ กลัวเสียสติ\n" +
                        "\n" +
                        "การรักษาโรคแพนิค\n" +
                        "✅ การใช้ยา\n" +
                        "\n" +
                        "ยาคลายเครียด / ยานอนหลับ (ออกฤทธิ์เร็ว)\n" +
                        "ยาปรับสมดุลสารเคมีในสมอง (ออกฤทธิ์ช้า ต้องกินต่อเนื่อง)\n" +
                        "✅ การดูแลด้านจิตใจ\n" +
                        "\n" +
                        "ฝึกควบคุมความคิดและพฤติกรรมเมื่อเกิดอาการ\n" +
                        "ค่อย ๆ เผชิญหน้ากับสิ่งที่เคยหวาดกลัว\n" +
                        "ฝึกผ่อนคลาย ทำจิตใจให้สงบ\n" +
                        "หากอาการกระทบชีวิตประจำวัน ควรปรึกษาแพทย์เพื่อรับการรักษาที่เหมาะสม \uD83D\uDC99 \n"+
                        "คัดกรองความเสี่ยงเบื้องต้น – ช่วยให้ผู้ใช้สามารถทำแบบสอบถามเพื่อประเมินความเสี่ยงของโรคต่าง ๆ ได้อย่างสะดวก รวดเร็ว\n" +
                        "\n" +
                        "ให้ข้อมูลสุขภาพที่ถูกต้อง – นำเสนอข้อมูลเกี่ยวกับโรคที่มีความเสี่ยง พร้อมคำแนะนำเบื้องต้นในการดูแลสุขภาพ\n" +
                        "\n" +
                        "กระตุ้นให้ผู้ใช้ใส่ใจสุขภาพ – ช่วยให้ผู้ใช้ตระหนักถึงปัจจัยเสี่ยงของโรค และปรับเปลี่ยนพฤติกรรมเพื่อสุขภาพที่ดีขึ้น\n" +
                        "\n" +
                        "ลดภาระของบุคลากรทางการแพทย์ – ช่วยคัดกรองผู้ที่มีความเสี่ยง ก่อนเข้ารับการตรวจวินิจฉัยจากแพทย์\n" +
                        "\n" +
                        "ให้คำแนะนำเบื้องต้น – แจ้งแนวทางการดูแลตนเอง หรือแนะนำให้พบแพทย์เมื่อมีความเสี่ยงสูง");
                break;
            case "ความเครียด":
                textInfo.setText("PTSD และ ASD คืออะไร?\n" +
                        "Post-Traumatic Stress Disorder (PTSD) และ Acute Stress Disorder (ASD) เป็นโรคทางจิตเวชที่เกิดขึ้นหลังจากเผชิญเหตุการณ์รุนแรง เช่น อุบัติเหตุร้ายแรง ภัยพิบัติ ถูกทำร้าย หรือสงคราม โดย ASD จะเกิดขึ้นทันทีหลังเหตุการณ์ ส่วน PTSD อาจเกิดขึ้นภายหลังและคงอยู่เป็นเวลานาน\n" +
                        "\n" +
                        "อาการของ PTSD และ ASD\n" +
                        "✔ รู้สึกเหมือนตกอยู่ในเหตุการณ์เดิมซ้ำ ๆ ทั้งตอนหลับและตื่น\n" +
                        "✔ หลีกเลี่ยงสิ่งที่เกี่ยวข้องกับเหตุการณ์ และมีอาการตื่นตัวง่าย\n" +
                        "✔ หวาดกลัวรุนแรง รู้สึกไร้ทางออก ซึมเศร้า วิตกกังวล\n" +
                        "✔ มีปัญหาด้านความจำและสมาธิ\n" +
                        "✔ รับรู้สิ่งรอบตัวผิดปกติ เช่น รู้สึกเหมือนมองตัวเองจากมุมมองของคนอื่น หรือรู้สึกว่าเวลาช้าลง\n" +
                        "\n" +
                        "เมื่อไหร่ควรพบแพทย์?\n" +
                        "หากอาการกระทบต่อชีวิตประจำวัน เช่น การทำงานและความสัมพันธ์กับคนรอบข้าง\n" +
                        "\n" +
                        "ภาวะฉุกเฉิน\n" +
                        "หากมีความคิดอยากทำร้ายตัวเองหรืออยากตาย ควรรีบพบแพทย์ทันที\n" +
                        "\n" +
                        "การวินิจฉัย\n" +
                        "แพทย์จะซักประวัติ ตรวจร่างกาย และอาจส่งตรวจเพิ่มเติม เพื่อแยกโรคทางกายที่อาจมีอาการคล้าย PTSD เช่น โรคลมชัก หรือโรคจากการบาดเจ็บที่ศีรษะ\n" +
                        "\n" +
                        "การรักษา\n" +
                        "✅ ใช้ยา – เพื่อช่วยควบคุมอาการทางอารมณ์\n" +
                        "✅ จิตบำบัด – รูปแบบการบำบัดที่ใช้ ได้แก่\n" +
                        "\n" +
                        "Cognitive Behavior Therapy (CBT) – ปรับความคิดและพฤติกรรม\n" +
                        "Eye Movement Desensitization and Reprocessing (EMDR) – บำบัดบาดแผลทางใจ\n" +
                        "Exposure Therapy – ฝึกเผชิญหน้ากับสิ่งที่กลัว\n" +
                        "\uD83D\uDCCC หากมีอาการ PTSD หรือ ASD อย่าลังเลที่จะขอความช่วยเหลือ การรักษาที่ถูกต้องสามารถช่วยให้กลับมาใช้ชีวิตปกติได้ \uD83D\uDC99");
                break;
            default:
                textInfo.setText("ข้อมูลจะแสดงที่นี่");
        }
    }
}
