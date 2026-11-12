package com.example.twowayintent;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_NUM1 = "Num1";
    public static final String EXTRA_NUM2 = "Num2";
    public static final String EXTRA_OPERATOR = "Operator";
    public static final String EXTRA_HAP = "Hap";
    public static final String EXTRA_RESULT = "Result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("메인 액티비티");

        Button btnNewActivity = (Button) findViewById(R.id.btnNewActivity);
        final RadioGroup rgOperator = (RadioGroup) findViewById(R.id.rgOperator);

        btnNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtNum1 = (EditText) findViewById(R.id.edtNum1);
                EditText edtNum2 = (EditText) findViewById(R.id.edtNum2);

                String strNum1 = edtNum1.getText() != null ? edtNum1.getText().toString() : "";
                String strNum2 = edtNum2.getText() != null ? edtNum2.getText().toString() : "";

                Integer num1 = parseNumber(strNum1);
                Integer num2 = parseNumber(strNum2);

                if (num1 == null || num2 == null) {
                    Toast.makeText(getApplicationContext(), "숫자를 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String operator = "+";
                int selectedId = rgOperator.getCheckedRadioButtonId();
                if (selectedId == R.id.rbSub) {
                    operator = "-";
                } else if (selectedId == R.id.rbMul) {
                    operator = "*";
                } else if (selectedId == R.id.rbDiv) {
                    operator = "/";
                } else {
                    operator = "+";
                }

                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra(EXTRA_NUM1, num1.intValue());
                intent.putExtra(EXTRA_NUM2, num2.intValue());
                intent.putExtra(EXTRA_OPERATOR, operator);
                intent.putExtra("Op", operator);
                startActivityForResult(intent, 0);
            }
        });
    }

    public static Integer parseNumber(String text) {
        if (text == null) {
            return null;
        }
        String trimmed = text.trim();
        if (trimmed.isEmpty()) {
            return null;
        }
        try {
            return Integer.parseInt(trimmed);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 결과는 두 번째 액티비티의 버튼 텍스트로 표시되므로 메인 액티비티에서는 토스트 메시지를 띄우지 않음
    }
}
