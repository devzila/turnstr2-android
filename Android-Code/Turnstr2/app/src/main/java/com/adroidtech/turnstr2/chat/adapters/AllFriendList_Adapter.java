package com.adroidtech.turnstr2.chat.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adroidtech.turnstr2.R;
import com.adroidtech.turnstr2.chat.models.Member;

import java.util.List;

/**
 * Created by narinder on 18/12/17.
 */

public class AllFriendList_Adapter extends RecyclerView.Adapter<AllFriendList_Adapter.MyViewHolder> {

    private final Context context;
    private final List<Member> member;
    private List<Member> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView email;


        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            email = (TextView) view.findViewById(R.id.email);
        }
    }


    public AllFriendList_Adapter(Context context, List<Member> member) {
        this.context = context;
        this.member=member;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.allfriend_list_adapter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.name.setText(member.get(position).getFirst_name());




    }

    @Override
    public int getItemCount() {
        return member.size();
    }
}
