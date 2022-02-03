package com.cse.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity {
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10,
            btn11, btn12, btn13, btn14, btn15, btn16, btn17, btn18;
    ImageButton btn19;
    Button btn20;
 private EditText display;
  TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);
        btn0 = (Button) findViewById(R.id.zero);
        btn1 = (Button) findViewById(R.id.one);
        btn2 = (Button) findViewById(R.id.two);
        btn3 = (Button) findViewById(R.id.three);
        btn4 = (Button) findViewById(R.id.four);
        btn5 = (Button) findViewById(R.id.five);
        btn6 = (Button) findViewById(R.id.six);
        btn7 = (Button) findViewById(R.id.seven);
        btn8 = (Button) findViewById(R.id.eight);
        btn9 = (Button) findViewById(R.id.nine);
        btn10 = (Button) findViewById(R.id.point);
        btn11 = (Button) findViewById(R.id.equal);
        btn12 = (Button) findViewById(R.id.exponent);
        btn13 = (Button) findViewById(R.id.parenthesis);
        btn14 = (Button) findViewById(R.id.plus);
        btn15 = (Button) findViewById(R.id.minus);
        btn16 = (Button) findViewById(R.id.plusminus);
        btn17 = (Button) findViewById(R.id.division);
        btn18 = (Button) findViewById(R.id.multiplacation);
        btn19 = (ImageButton) findViewById(R.id.deletebtn);
        btn20 = (Button) findViewById(R.id.clear);
        tv = (TextView)findViewById(R.id.result);

//        display.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                display.setText("");
//
//            }
//        });



    }
    private void updateText(String strToAdd){
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0,cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if(getString(R.string.display).equals(display.getText().toString())){
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);
        }
        else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPos + 1);
        }
    }

    public void zero (View view) {
        updateText("0");

    }
    public void one (View view) {
        updateText("1");
    }
    public void two (View view) {
        updateText("2");
    }
    public void three (View view) {
        updateText("3");
    }
    public void four (View view) {
        updateText("4");
    }
    public void five (View view) {
        updateText("5");
    }
    public void six (View view) {
        updateText("6");
    }
    public void seven (View view) {
        updateText("7");
    }
    public void eight (View view) {
        updateText("8");
    }
    public void nine (View view) {
        updateText("9");
    }
    public void clear (View view) {
        display.setText("");
        tv.setText("");
    }
    public void parenthesis (View view) {
        int CursorPos = display.getSelectionStart();
        int openPar = 0;
        int closePar = 0;
        int textLen = display.getText().length();
        for (int i = 0; i < CursorPos; i++) {
            if (display.getText().toString().substring(i, i + 1).equals("(")) {
                openPar += 1;
            }
            if (display.getText().toString().substring(i, i + 1).equals(")")) {
                closePar += 1;
            }
        }
        if(openPar==closePar || display.getText().toString().substring(textLen-1,textLen).equals("(")){
            updateText("(");
            display.setSelection(CursorPos+1);
        }
      else if(closePar<openPar && ! display.getText().toString().substring(textLen-1,textLen).equals(")")){
            updateText(")");

        }
        display.setSelection(CursorPos+1);
    }
    public void exponent (View view) {
        updateText("%");
    }
    public void plus (View view) {
        updateText("+");
    }
    public void minus (View view) {
        updateText("-");
    }
    public void multiplacation (View view) {
        updateText("×");
    }
    public void subtraction (View view) {
        updateText("÷");
    }
    public void plusminus (View view) {
        updateText("+/-");
    }
    public void backspace (View view) {
        int CursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if(CursorPos != 0 && textLen !=0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(CursorPos -1,CursorPos,"");
            display.setText(selection);
            display.setSelection(CursorPos - 1);
        }

    }
    public void point (View view) {
        updateText(".");
    }
    public void equal (View view) {
        String data = display.getText().toString();

        data=data.replaceAll("×","*");
        data=data.replaceAll("%","/100");
        data=data.replaceAll("÷","/");
        Context rhino= Context.enter();
        rhino.setOptimizationLevel(-1);

        String finalResult="";

        Scriptable scriptable=rhino.initStandardObjects();
        finalResult=rhino.evaluateString(scriptable,data,"Javascript",1,null).toString();

        tv.setText(finalResult);

//        String userExp = display.getText().toString();
//        userExp = userExp.replaceAll("÷","/");
//        userExp = userExp.replaceAll("×","*");
//        Expression exp = new Expression(userExp);
//        String result = String.valueOf(exp.calculate());
//        display.setText(result);
//        display.setSelection(result.length());
       // tv.setText(result);

    }
    }