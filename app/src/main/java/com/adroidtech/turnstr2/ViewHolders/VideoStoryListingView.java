package com.adroidtech.turnstr2.ViewHolders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adroidtech.turnstr2.Adapter.MyStoryAdapter;
import com.adroidtech.turnstr2.R;
import com.adroidtech.turnstr2.Utils.GeneralValues;

public class VideoStoryListingView extends RecyclerView.ViewHolder {

    public TextView date;
    public View mainView;
    public ImageView thumb_image;

    public VideoStoryListingView(View view, Context context) {
        super(view);
        this.mainView = view;
        thumb_image = (ImageView) view.findViewById(R.id.thumb_image);
        date = (TextView) view.findViewById(R.id.date);
        int screenWidth = GeneralValues.getScreenWidth(context);
        int itemWidth = ((screenWidth) / 3);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(itemWidth, itemWidth);
        thumb_image.setLayoutParams(layoutParams);
    }
}
