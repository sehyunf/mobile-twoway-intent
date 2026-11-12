package com.example.twowayintent;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    public static int calculate(String operator, int num1, int num2) {
        if (operator == null) {
            return num1 + num2;
        }
        switch (operator) {
            case "+":
            case "ADD":
            case "더하기":
                return num1 + num2;
            case "-":
            case "SUB":
            case "빼기":
                return num1 - num2;
            case "*":
            case "MUL":
            case "곱하기":
                return num1 * num2;
            case "/":
            case "DIV":
            case "나누기":
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    return 0; // 0으로 나누기 방지
                }
            default:
                return num1 + num2;
        }
    }

    public static int calculateHap(int num1, int num2) {
        return calculate("+", num1, num2);
    }

    public static String formatResultButtonText(int result) {
        return "결과 : " + result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        setTitle("Second 액티비티");

        Intent inIntent = getIntent();
        int num1 = inIntent != null ? inIntent.getIntExtra(MainActivity.EXTRA_NUM1, 0) : 0;
        int num2 = inIntent != null ? inIntent.getIntExtra(MainActivity.EXTRA_NUM2, 0) : 0;
        String operator = inIntent != null ? inIntent.getStringExtra(MainActivity.EXTRA_OPERATOR) : "+";
        if (operator == null && inIntent != null) {
            operator = inIntent.getStringExtra("Op");
        }

        final int resultValue = calculate(operator, num1, num2);

        Button btnReturn = (Button) findViewById(R.id.btnReturn);
        // 결과값을 두 번째 인텐트(액티비티)의 버튼 텍스트로 표시
        btnReturn.setText(formatResultButtonText(resultValue));

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outIntent = new Intent(getApplicationContext(),
                        MainActivity.class);
                outIntent.putExtra(MainActivity.EXTRA_RESULT, resultValue);
                outIntent.putExtra(MainActivity.EXTRA_HAP, resultValue);
                setResult(RESULT_OK, outIntent);
                finish();
            }
        });
    }
}
