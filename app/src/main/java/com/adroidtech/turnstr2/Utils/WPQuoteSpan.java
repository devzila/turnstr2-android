package com.adroidtech.turnstr2.Utils;

/**
 * Created by sarbjot.singh on 9/5/2017.
 */

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.style.LineBackgroundSpan;
import android.text.style.QuoteSpan;

/**
 * Customzed QuoteSpan for use in SpannableString's
 */
public class WPQuoteSpan extends QuoteSpan implements LineBackgroundSpan {
    public static final int STRIPE_COLOR = 0xFFC1C3C2;
    private static final int STRIPE_WIDTH = 5;
    private static final int GAP_WIDTH = 10;
    private int mHeight = 0;
    private int mRight;

    public WPQuoteSpan() {
        super(STRIPE_COLOR);
    }

    @Override
    public int getLeadingMargin(boolean first) {
        int margin = GAP_WIDTH * 2;
        return margin;
    }


    /**
     * Draw a nice thick gray bar if Ice Cream Sandwhich or newer. There's a
     * bug on older devices that does not respect the increased margin.
     */
    @Override
    public void drawLeadingMargin(Canvas c, Paint p, int x, int dir, int top, int baseline, int bottom,
                                  CharSequence text, int start, int end, boolean first, Layout layout) {
        Paint.Style style = p.getStyle();
        int color = p.getColor();
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(1);
        p.setAlpha(50);
        p.setColor(STRIPE_COLOR);
        c.drawRect(0, 0, mRight, mHeight, p);
        layout.getEllipsizedWidth();
        p.setStyle(style);
        p.setColor(color);

    }

    @Override
    public void drawBackground(Canvas c, Paint p, int left, int right, int top, int baseline, int bottom, CharSequence text, int start, int end, int lnum) {
        int paintColor = p.getColor();
        p.setColor(0xFFC1C3C2);
        mHeight = bottom;
        mRight = right;
//        c.drawRect(left, top, right, mHeight + GAP_WIDTH * 2, p);
//        p.setColor(paintColor);
        Paint paint = new Paint();
        p.setColor(0xff000000);
        p.setStyle(Paint.Style.FILL);
        p.setAlpha(30);
        if (top == 0)
            top = 2;
        c.drawRect(left, top, right, bottom, p);
        p.setColor(paintColor);
    }
}