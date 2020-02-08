package com.example.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView text;
    boolean isSecondNum;
    float carrier, num;
    Button add,sub,mul,div, pow;
    Button one, two, three, four, five, six, seven, eight, nine, zero;



    enum State{
        ADD,
        SUBS,
        MULT,
        DIV,
        DEF,
        POW
    }
    State state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.answer);
        state = State.DEF;
        text.setText("0");
        carrier = 0;
        num = 0;
        isSecondNum = false;
        add = findViewById(R.id.add);
        sub = findViewById(R.id.subs);
        mul = findViewById(R.id.multiply);
        div = findViewById(R.id.div);
        pow = findViewById(R.id.power);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        zero = findViewById(R.id.zero);
    }

    public void onNumClick(View view) {
        Button btn = (Button) view;
        String num = btn.getText().toString();
        String all = text.getText().toString();
        if (all=="0" && btn==zero){
            return;
        }
        if (all=="0" || all=="error"){
            text.setText(num);
            return;
        }
        if(state==State.DEF && !isSecondNum){
            text.setText(all + num);
            return;
        }
        if (isSecondNum){
            text.setText(num);
            isSecondNum = false;
        } else{
            text.setText(all + num);
        }
    }

    public void onClear(View view) {
        text.setText("0");
        state = State.DEF;
        isSecondNum = false;
    }

    public void onState(View view) {
        if (text.getText().toString()=="error")
            text.setText("0");
        Button btn = (Button) view;
        if (state==State.DEF)
        {
            if (btn == add) {
                state = State.ADD;
            } else if (btn == sub) {
                state = State.SUBS;
            } else if (btn == mul) {
                state = State.MULT;
            } else if (btn == div) {
                state = State.DIV;
            } else if (btn == pow){
                state = State.POW;
            }
            isSecondNum = true;
            carrier = Float.parseFloat(text.getText().toString());
        } else{
            num = Float.parseFloat(text.getText().toString());
            float ans = carrier;
            if (state==State.ADD){
                ans = (num + carrier);
                text.setText(String.valueOf(ans));
            }
            if (state==State.SUBS){
                ans = (carrier - num);
                text.setText(String.valueOf(ans));
            }
            if (state==State.MULT){
                ans = (num * carrier);
                text.setText(String.valueOf(ans));
            }
            if (state==State.DIV){
                if(num!=0){
                    ans = (carrier / num);
                    text.setText(String.valueOf(ans));
                }
                else
                    text.setText("error");
            }
            if (state==State.POW){
                if (num>0){
                    text.setText(String.valueOf(power(carrier, num)));
                } else if (num<0){
                    text.setText(String.valueOf(1/power(carrier, num*-1)));
                } else{
                    text.setText("1");
                }
            }
            if (btn == add) {
                state = State.ADD;
            } else if (btn == sub) {
                state = State.SUBS;
            } else if (btn == mul) {
                state = State.MULT;
            } else if (btn == div) {
                state = State.DIV;
            } else if (btn == pow){
                state = State.POW;
            }
            isSecondNum = true;
            carrier = ans;
        }
    }

    public void onSign(View view) {
        if (text.getText().toString().charAt(0)!='-')
        {
            text.setText("-" + text.getText().toString());
        } else{
            text.setText(text.getText().toString().substring(1));
        }
    }

    private float power(float carrier, float num) {
        if (num==0){
            return 1;
        }
        if (num%2==0){
            return power(carrier, num/2)*power(carrier, num/2);
        } else{
            return carrier*power(carrier, num-1);
        }
    }


    public void onResult(View view) {
        num = Float.parseFloat(text.getText().toString());
        float ans = carrier;
        if (state==State.ADD){
            ans = (num + carrier);
            text.setText(String.valueOf(ans));
        }
        if (state==State.SUBS){
            ans = (carrier - num);
            text.setText(String.valueOf(ans));
        }
        if (state==State.MULT){
            ans = (num * carrier);
            text.setText(String.valueOf(ans));
        }
        if (state==State.DIV){
            if(num!=0){
                ans = (carrier / num);
                text.setText(String.valueOf(ans));
            }
            else
                text.setText("error");
        }
        if (state==State.POW){
            if (num>0){
                text.setText(String.valueOf(power(carrier, num)));
            } else if (num<0){
                text.setText(String.valueOf(1/power(carrier, num*-1)));
            } else{
                text.setText("1");
            }
        }
        if (state==State.DEF){
            return;
        }
        isSecondNum = true;
        carrier = ans;
        state = State.DEF;
    }
}
