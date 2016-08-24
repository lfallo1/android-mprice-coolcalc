package com.training.lfallon.androidcoolcalc;

import android.view.View;
import android.widget.Button;

public class NumberPressHandler implements View.OnClickListener{

    public interface AppendNumber{
        void appendCharAsNumber(char c);
    }

    private AppendNumber context;

    public NumberPressHandler(AppendNumber context){
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        Button button = (Button)v;
        this.context.appendCharAsNumber(button.getText().toString().charAt(0));
    }
}
