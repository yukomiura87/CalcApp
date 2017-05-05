package jp.techacademy.miura3.yuuko.calcapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        double value1 = Double.parseDouble(intent.getStringExtra("VAL1"));
        double value2 = Double.parseDouble(intent.getStringExtra("VAL2"));
        String operator = intent.getStringExtra("VAL3");

        // 計算式を出力
        TextView tvFormula = (TextView) findViewById(R.id.textViewFormula);
        tvFormula.setText(String.valueOf(value1) + " " + operator + " " + String.valueOf(value2) + " =");

        // 計算結果を出力
        TextView tvResult = (TextView) findViewById(R.id.textViewResult);
        if(operator.equals("+")) {
            tvResult.setText(String.valueOf(value1 + value2));
        } else if(operator.equals("-")) {
            tvResult.setText(String.valueOf(value1 - value2));
        } else if(operator.equals("*")) {
            tvResult.setText(String.valueOf(value1 * value2));
        } else if(operator.equals("/")) {
            tvResult.setText(String.valueOf(value1 / value2));
        }
    }
}
