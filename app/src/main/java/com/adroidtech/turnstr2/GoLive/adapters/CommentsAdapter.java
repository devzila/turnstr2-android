package com.adroidtech.turnstr2.GoLive.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adroidtech.turnstr2.R;

import java.util.List;

/**
 * Created by narinder on 20/02/18.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {


    private final Context context;
    private final List<String> messageList;


    public CommentsAdapter(Context context, List<String> messageList) {
        this.context=context;
        this.messageList=messageList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CommentsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comments_adapter, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        holder.textMessage.setText(""+messageList.get(position));
    }


    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textMessage;

        public ViewHolder(View v) {
            super(v);
            textMessage = (TextView) v.findViewById(R.id.textMessage);
        }
    }

}
