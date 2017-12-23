package com.adroidtech.turnstr2.CustomeViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.CheckedTextView;



public class CustomeCheckedTextView extends CheckedTextView {

    public CustomeCheckedTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomeCheckedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomeCheckedTextView(Context context) {
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