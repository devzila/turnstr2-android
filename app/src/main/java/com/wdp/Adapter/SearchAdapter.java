package com.wdp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.wdp.ActivityScreen.OtherProfileActivity;
import com.wdp.CubeScreen.CubeGLSurfaceView;
import com.wdp.CubeScreen.DragControl;
import com.wdp.Modal.LoginResDataModal;
import com.wdp.Modal.SearchDataModal;
import com.wdp.Utility.CommonSharedPreference;
import com.wdp.turnstr.R;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> implements Filterable {
    ArrayList<SearchDataModal.DataBean.UsersBean> dataList;
    ArrayList<SearchDataModal.DataBean.UsersBean> filterdataList;
    Context context;
    LoginResDataModal loginResDataModal;
    CommonSharedPreference commonSharedPreference = new CommonSharedPreference();

    public SearchAdapter(Context context, ArrayList<SearchDataModal.DataBean.UsersBean> notificationListBeans) {
        this.context = context;
        this.dataList = notificationListBeans;
        this.filterdataList = notificationListBeans;
        loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_iteam_search, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        SearchDataModal.DataBean.UsersBean dataBean = filterdataList.get(position);
        holder.txt_name.setText(dataBean.getName());
        String avatar1="",avatar2="",avatar3="",avatar4="",avatar5="",avatar6="";
        if (dataBean.getAvatar()!= null){
            avatar1 = dataBean.getAvatar().getThumb();
        }
        if (dataBean.getAvatar2()!= null){
            avatar2 = dataBean.getAvatar2().getThumb();
        }
        if (dataBean.getAvatar3()!= null){
            avatar3 = dataBean.getAvatar3().getThumb();
        }
        if (dataBean.getAvatar4()!= null){
            avatar4 = dataBean.getAvatar4().getThumb();
        }
        if (dataBean.getAvatar5()!= null){
            avatar5 = dataBean.getAvatar5().getThumb();
        }
        if (dataBean.getAvatar6()!= null){
            avatar6 = dataBean.getAvatar6().getThumb();
        }

        holder.glView = new CubeGLSurfaceView(context,avatar1,avatar2,avatar3,avatar4,avatar5,avatar6);
        holder.ll_cube.setBackgroundColor(context.getResources().getColor(R.color.white));
        holder.ll_cube.addView(holder.glView );
        holder.dragControl = new DragControl(context,context,avatar1,avatar2,avatar3,avatar4,avatar5,avatar6);
        //holder.glView.setOnTouchListener(holder.dragControl);
        holder.glView.setDragControl(holder.dragControl);
        holder.glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        holder.glView.setZOrderOnTop(true);
        holder.glView.setZOrderMediaOverlay(true);

        holder.ll_cube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, OtherProfileActivity.class);
                i.putExtra("user_id",dataBean.getId());
                context.startActivity(i);
            }
        });





    }

    @Override
    public int getItemCount() {
        return filterdataList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ll_cube;
        TextView txt_name;
        CubeGLSurfaceView glView;
        DragControl dragControl;
        public MyViewHolder(View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.txt_name);
            ll_cube = itemView.findViewById(R.id.ll_cube);
        }
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                Log.d("charString", "----->" + charString);
                if (charString.isEmpty()) {
                    filterdataList = dataList;
                } else {
                    ArrayList<SearchDataModal.DataBean.UsersBean> filteredList = new ArrayList<>();
                    for (SearchDataModal.DataBean.UsersBean row : dataList) {
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    filterdataList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filterdataList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filterdataList = (ArrayList<SearchDataModal.DataBean.UsersBean>) filterResults.values;
                Log.d("charString", "----->" + filterdataList);
                notifyDataSetChanged();
            }
        };
    }
}
