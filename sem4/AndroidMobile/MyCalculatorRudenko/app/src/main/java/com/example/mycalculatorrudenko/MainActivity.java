package com.example.mycalculatorrudenko;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText userIpField;
    private EditText userMaskField;
    private Button button;
    private TextView textView;
    private String ipAddress;
    private String subnetMask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userIpField = findViewById(R.id.userIPField);
        userMaskField = findViewById(R.id.userMaskField);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.resultTextView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ipAddress = userIpField.getText().toString();
                subnetMask = userMaskField.getText().toString();



                //String res = "Длина маски подсети: " + maskLength + "\n\n" +
                //        "Адрес подсети: " +  networkAddress + "\n\n" +
                //       "Адрес широковещательной рассылки: " + broadcastAddress;

                //textView.setText(res);
            }
        });
    }
}

