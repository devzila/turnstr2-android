package com.adroidtech.turnstr2.chat.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.adroidtech.turnstr2.R;
import com.adroidtech.turnstr2.chat.models.Member;

import java.util.List;

/**
 * Created by narinder on 18/12/17.
 */

public class AllFriendList_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final List<Member> member;
    private List<Member> moviesList;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private boolean isLoading;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView email;


        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            email = (TextView) view.findViewById(R.id.email);
        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
        }
    }


    public AllFriendList_Adapter(Context context, List<Member> member) {
        this.context = context;
        this.member=member;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.allfriend_list_adapter, parent, false);
           // View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view_row, parent, false);
            return new MyViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof MyViewHolder) {
            MyViewHolder userViewHolder = (MyViewHolder) holder;
            userViewHolder.name.setText(member.get(position).getUsername());
            userViewHolder.email.setText(member.get(position).getFirst_name()+" "+member.get(position).getLast_name());
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }


    }
    @Override
    public int getItemViewType(int position) {
        return member.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

//    @Override
//    public int getItemCount() {
//        return member.size();
//    }
@Override
public int getItemCount() {
    return member == null ? 0 : member.size();
}

    public void setLoaded() {
        isLoading = false;
    }
}
