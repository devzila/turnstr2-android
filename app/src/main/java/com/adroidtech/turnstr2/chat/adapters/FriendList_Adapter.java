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

public class FriendList_Adapter extends RecyclerView.Adapter<FriendList_Adapter.MyViewHolder> {

    private final Context context;
    private List<Member> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public MyViewHolder(View view) {
            super(view);
//            title = (TextView) view.findViewById(R.id.title);
//            genre = (TextView) view.findViewById(R.id.genre);
//            year = (TextView) view.findViewById(R.id.year);
        }
    }


    public FriendList_Adapter(Context context) {
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.friend_list_adapter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        Member movie = moviesList.get(position);
//        holder.title.setText(movie.getTitle());
//        holder.genre.setText(movie.getGenre());
//        holder.year.setText(movie.getYear());
    }

    @Override
    public int getItemCount() {
        return 10;//moviesList.size();
    }
}
