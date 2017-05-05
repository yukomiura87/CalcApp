package jp.techacademy.miura3.yuuko.calcapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText mEditText1;
    EditText mEditText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText1 = (EditText) findViewById(R.id.editText1);
        initEditText(mEditText1);

        mEditText2 = (EditText) findViewById(R.id.editText2);
        initEditText(mEditText2);

        Button btn1 = (Button) findViewById(R.id.btnPlus);
        btn1.setText("+");
        btn1.setOnClickListener(this);

        Button btn2 = (Button) findViewById(R.id.btnMinus);
        btn2.setText("-");
        btn2.setOnClickListener(this);

        Button btn3 = (Button) findViewById(R.id.btnMultiple);
        btn3.setText("*");
        btn3.setOnClickListener(this);

        Button btn4 = (Button) findViewById(R.id.btnDivide);
        btn4.setText("/");
        btn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // 入力チェック
        if(!isSuccessInput(v.getId())) {
            return ;
        }

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("VAL1", mEditText1.getText().toString().trim());
        intent.putExtra("VAL2", mEditText2.getText().toString().trim());
        intent.putExtra("VAL3", ((Button) findViewById(v.getId())).getText());

        startActivity(intent);
    }

    ///////////////////////////////////////////////////////////////////////////
    // 入力チェック
    ///////////////////////////////////////////////////////////////////////////
    private boolean isSuccessInput(int selectedButtonId) {
        this.setMessage("");

        if( mEditText1.getText().toString().trim().isEmpty()) {
            setMessage("入力は必須です。");
            setFocus(mEditText1);
            return false;
        }

        if( !isDouble(mEditText1.getText().toString().trim())) {
            setMessage("数値を入力してください。");
            setFocus(mEditText1);
            return false;
        }

        if( mEditText2.getText().toString().trim().isEmpty()) {
            setMessage("入力は必須です。");
            setFocus(mEditText2);
            return false;
        }

        if( !isDouble(mEditText2.getText().toString().trim())) {
            setMessage("数値を入力してください。");
            setFocus(mEditText2);
            return false;
        }

        if(selectedButtonId == R.id.btnDivide && Double.parseDouble(mEditText2.getText().toString().trim()) == (double)0 ) {
            setMessage("0で割ることはできません。");
            setFocus(mEditText2);
            return false;
        }

        return true;
    }

    ///////////////////////////////////////////////////////////////////////////
    // EditTextの初期化
    ///////////////////////////////////////////////////////////////////////////
    private void initEditText(EditText editText) {
        editText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editText.setGravity(Gravity.RIGHT);
        editText.setText("");

        //MAX入力文字数設定
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(21);
        editText.setFilters(filters);
    }

    ///////////////////////////////////////////////////////////////////////////
    // 文字列がdouble型のときtrueを返す。
    ///////////////////////////////////////////////////////////////////////////
    private boolean isDouble( String numString ) {
        try {
            double result = Double.parseDouble(numString);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // フォーカスをセット
    ///////////////////////////////////////////////////////////////////////////
    private void setFocus(EditText et) {
        et.requestFocus();
        et.setFocusableInTouchMode(true);
        et.setSelection(et.length());
    }

    ///////////////////////////////////////////////////////////////////////////
    // エラーメッセージをセット
    ///////////////////////////////////////////////////////////////////////////
    private void setMessage(String msg) {
        ((TextView) findViewById(R.id.messageView)).setText(msg);
    }
}
