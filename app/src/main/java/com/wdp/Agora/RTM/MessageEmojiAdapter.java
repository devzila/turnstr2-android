package com.wdp.Agora.RTM;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;


import com.wdp.turnstr.R;

import java.util.List;


public class MessageEmojiAdapter extends RecyclerView.Adapter<MessageEmojiAdapter.MyViewHolder> {
    private List<String> messageBeanList;
    private LayoutInflater inflater;
    OnIteamclick onIteamclick;
    public interface OnIteamclick{
        public void onclickiteam(int pos);

    }

    public MessageEmojiAdapter(Context context, List<String> messageBeanList,OnIteamclick onIteamclick) {
        inflater = ((Activity) context).getLayoutInflater();
        this.messageBeanList = messageBeanList;
        this.onIteamclick = onIteamclick;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_iteam_emoji, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        setupView(holder, position);
    }

    @Override
    public int getItemCount() {
        return messageBeanList.size();
    }


    private void setupView(MyViewHolder holder, int position) {
        String emojiIteam = messageBeanList.get(position);
        holder.txt_message.setText(emojiIteam);
        holder.txt_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onIteamclick.onclickiteam(position);
            }
        });


    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_message;


        MyViewHolder(View itemView) {
            super(itemView);

            txt_message = itemView.findViewById(R.id.txt_message);

        }
    }
}
