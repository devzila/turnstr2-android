package com.wdp.Adapter;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;


import com.wdp.turnstr.R;

import java.util.List;


/**
 * Created by Choudhary on 7/1/2017.
 */

public class MediaAlbumAdapter extends BaseAdapter implements SpinnerAdapter {
    private Context activity;
    private List<String> bucketNames,bitmapList;
    private TextView txt;
    private String nId;

    public MediaAlbumAdapter(Context context, List<String> bucketNames, List<String> bitmapList) {
        this.activity = context;
        this.bucketNames=bucketNames;
        this.bitmapList = bitmapList;
    }

    public int getCount() {
        return bucketNames.size();
    }

    public Object getItem(int i) {
        return bucketNames.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView txt = new TextView(activity);
        txt.setPadding(16, 16, 16, 16);
        txt.setGravity(Gravity.CENTER);
        txt.setTextColor(activity.getResources().getColor(R.color.ColorGray));
        txt.setTextSize(14);
        txt.setText(bucketNames.get(position));
        txt.setBackgroundColor(activity.getResources().getColor(R.color.white));
        return txt;
    }

    public View getView(int i, View view, ViewGroup viewgroup) {
            txt = new TextView(activity);
            txt.setTextSize(14);
            txt.setGravity(Gravity.CENTER);
            txt.setTextColor(activity.getResources().getColor(R.color.black));
            txt.setPadding(10, 0, 10, 0);
            txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            txt.setText(bucketNames.get(i));
            txt.setBackgroundColor(activity.getResources().getColor(R.color.white));
            return txt;
    }


}
