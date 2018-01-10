package com.adroidtech.turnstr2.ViewHolders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adroidtech.turnstr2.Adapter.MyStoryAdapter;
import com.adroidtech.turnstr2.R;
import com.adroidtech.turnstr2.Utils.GeneralValues;

public class MyStoryListingView extends RecyclerView.ViewHolder {

    public FrameLayout ll_main;
    public View mainView;

    public MyStoryListingView(View view, Context context) {
        super(view);
        this.mainView = view;
        ll_main = (FrameLayout) view.findViewById(R.id.layout_frame);
        int screenWidth = GeneralValues.getScreenWidth(context);
        int itemWidth = ((screenWidth) / 3);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(itemWidth, itemWidth);
        ll_main.setLayoutParams(layoutParams);
    }

    public void bind(final MyStoryAdapter.OnItemClickListener listener, final int position) {
        try {
            View.OnClickListener clickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.ClickAction(position);
                }
            };
            mainView.setOnClickListener(clickListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
