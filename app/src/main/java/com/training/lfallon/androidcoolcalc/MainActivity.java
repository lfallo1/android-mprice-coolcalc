package com.training.lfallon.androidcoolcalc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener, NumberPressHandler.AppendNumber {

    private String mRunningNumber = "";
    private Double mCurrentResult = 0.0;
    private char mCurrentOperation = '\0';
    private static final char ADD = '+';
    private static final char DIVIDE = '/';
    private static final char MULTIPLY = '*';
    private static final char SUBTRACT = '-';

    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NumberPressHandler numberPressHandler = new NumberPressHandler(this);

        resultText = (TextView)findViewById(R.id.resultText);
        clearResult();

        //equals button
        findViewById(R.id.equalsButton).setOnClickListener(this);

        //operation click handlers
        findViewById(R.id.addButton).setOnClickListener(this);
        findViewById(R.id.subtractButton).setOnClickListener(this);
        findViewById(R.id.divideButton).setOnClickListener(this);
        findViewById(R.id.multiplyButton).setOnClickListener(this);

        //clear click handler
        findViewById(R.id.clearButton).setOnClickListener(this);

        //number click handlers
        findViewById(R.id.oneButton).setOnClickListener(numberPressHandler);
        findViewById(R.id.twoButton).setOnClickListener(numberPressHandler);
        findViewById(R.id.threeButton).setOnClickListener(numberPressHandler);
        findViewById(R.id.fourButton).setOnClickListener(numberPressHandler);
        findViewById(R.id.fiveButton).setOnClickListener(numberPressHandler);
        findViewById(R.id.sixButton).setOnClickListener(numberPressHandler);
        findViewById(R.id.sevenButton).setOnClickListener(numberPressHandler);
        findViewById(R.id.eightButton).setOnClickListener(numberPressHandler);
        findViewById(R.id.nineButton).setOnClickListener(numberPressHandler);
        findViewById(R.id.zeroButton).setOnClickListener(numberPressHandler);
    }

    @Override
    public void onClick(View v) {

        //clear result
        if(v.getId() == R.id.clearButton){
            clearResult();
            return;
        }

        //perform operations
        if(v.getId() == R.id.addButton){
            calculate(ADD);
        } else if(v.getId() == R.id.subtractButton){
            calculate(SUBTRACT);
        } else if(v.getId() == R.id.divideButton){
            calculate(DIVIDE);
        } else if(v.getId() == R.id.multiplyButton){
            calculate(MULTIPLY);
        } else if(v.getId() == R.id.equalsButton){
            if(mRunningNumber.equals("")){
                return;
            }
            calculate('\0');
        }
    }

    private void clearResult(){
        mCurrentOperation = '\0';
        mRunningNumber = "";
        mCurrentResult = 0.0;
        resultText.setText("0");
    }

    private void calculate(char operation){
        if(mCurrentOperation != '\0' && !mRunningNumber.equals("")){
            if(mCurrentOperation == MULTIPLY){
                mCurrentResult *= Double.parseDouble(mRunningNumber);
            } else if(mCurrentOperation == DIVIDE){
                mCurrentResult /= Double.parseDouble(mRunningNumber);
            } else if(mCurrentOperation == SUBTRACT){
                mCurrentResult -= Double.parseDouble(mRunningNumber);
            } else if(mCurrentOperation == ADD){
                mCurrentResult += Double.parseDouble(mRunningNumber);
            }
        } else if(mRunningNumber != null && mRunningNumber.length() > 0){
            mCurrentResult = Double.parseDouble(mRunningNumber);
        }
        mCurrentOperation = operation;
        mRunningNumber = "";
        resultText.setText(String.format("%.2f", mCurrentResult));
    }

    @Override
    public void appendCharAsNumber(char c) {
        mRunningNumber += c;
        resultText.setText(mRunningNumber);
    }
}
