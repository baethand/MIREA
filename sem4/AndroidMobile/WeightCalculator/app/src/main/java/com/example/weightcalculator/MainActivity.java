package com.example.weightcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;
    private Button btnCalculate;
    private Spinner spinner;
    private EditText poleVvoda;
    Button goInfoActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        textView7 = findViewById(R.id.textView7);
        btnCalculate = findViewById(R.id.btnCalculate);
        spinner = findViewById(R.id.spinner);
        poleVvoda = findViewById(R.id.poleVvoda);
        goInfoActivity = findViewById(R.id.btn_info);

        goInfoActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });


        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = poleVvoda.getText().toString();
                Float vvod;
                try {
                    vvod = Float.parseFloat(str);
                }
                catch (Exception e){
                    vvod = (float) 0;
                }
                String selected = spinner.getSelectedItem().toString();
                float ideal;

                if(vvod >= 0) {
                    switch (selected) {
                        case  ("kt"):
                            ideal = (float) (vvod * 0.0002);
                            break;
                        case  ("g"):
                            ideal = (float) (vvod * 0.001);
                            break;
                        case  ("kg"):
                            ideal = vvod;
                            break;
                        case  ("centners"):
                            ideal = vvod * 100;
                            break;
                        case  ("tonns"):
                            ideal = vvod * 1000;
                            break;
                        case  ("pound"):
                            ideal = (float) (vvod * 0.453592);
                            break;
                        case  ("unci"):
                            ideal = (float) (vvod * 0.0283495);
                            break;
                        default:
                            ideal = 0;
                            break;
                    }
                    Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                    intent.putExtra("result", setRes(ideal));
                    startActivity(intent);
                }
            }
        });
    }

    public String setRes(float ideal){
        String result = String.valueOf(ideal / 0.0002) + " кт" + "\n" +
                String.valueOf(ideal / 0.001) + " г" + "\n" +
                String.valueOf(ideal) + " кг" + "\n" +
                String.valueOf(ideal / 100) + " ц" + "\n" +
                String.valueOf(ideal / 1000) + " тонн" + "\n" +
                String.valueOf(ideal / 0.45359200000017) + " фунтов" + "\n" +
                String.valueOf(ideal / 0.0283495) + " унции" + "\n";

        return result;
    }
}