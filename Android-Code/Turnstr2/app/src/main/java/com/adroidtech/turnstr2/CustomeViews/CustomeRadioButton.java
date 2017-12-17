package com.adroidtech.turnstr2.CustomeViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.RadioButton;


public class CustomeRadioButton extends RadioButton {

    public CustomeRadioButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomeRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomeRadioButton(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");
            setTypeface(tf);
        }
    }

}