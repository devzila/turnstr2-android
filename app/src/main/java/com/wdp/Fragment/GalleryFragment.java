package com.wdp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.wdp.ActivityScreen.ImageFilterActivity;
import com.wdp.ActivityScreen.MainActivity;
import com.wdp.ActivityScreen.StoryCubeViewActivity;
import com.wdp.ActivityScreen.StoryPostActivity;
import com.wdp.Adapter.MediaAdapter;
import com.wdp.Adapter.MediaAlbumAdapter;
import com.wdp.Utility.CommanUtility;
import com.wdp.turnstr.R;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class GalleryFragment extends Fragment {
    List<String> bitmapList=new ArrayList<>();
    List<String> bucketNames= new ArrayList<>();
    String[] projection = new String[]{MediaStore.Images.Media.BUCKET_DISPLAY_NAME, MediaStore.Images.Media.DATA };
    String[] projection2 = new String[]{MediaStore.Images.Media.DISPLAY_NAME,MediaStore.Images.Media.DATA };
    Spinner spinner;
    View view;
    MediaAlbumAdapter mediaAlbumAdapter;
    List<String> imagesList= new ArrayList<>();
    List<Boolean> selected=new ArrayList<>();
    private MediaAdapter mediaAdapter;
    private RecyclerView recyclerView;
    public static ArrayList<String> imagesSelected = new ArrayList<>();
    TextView txt_next, txt_cancel;
    String whichScreen;
    public GalleryFragment(){

    }

    public GalleryFragment(String whichscreen){
        whichScreen = whichscreen;
        Log.d("whichScreen","--->" + whichScreen);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gallery, container, false);
        initView();
        getPicBuckets();
        setData();
        return view;
    }
    private void initView(){
        spinner =  view.findViewById(R.id.spinner);
        recyclerView =  view.findViewById(R.id.recyclerView);
        txt_next =  view.findViewById(R.id.txt_next);
        txt_cancel = view.findViewById(R.id.txt_cancel);
    }

    private void setData(){
        mediaAlbumAdapter = new MediaAlbumAdapter(getActivity() ,bucketNames,bitmapList);
        spinner.setAdapter(mediaAlbumAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getPictures(bucketNames.get(i));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        txt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imagesSelected.size() > 0){
                    if (whichScreen.equalsIgnoreCase("post")){
                        Intent i = new Intent(getActivity(), ImageFilterActivity.class);
                        Gson gson = new Gson();
                        String arrayData = gson.toJson(imagesSelected);
                        i.putExtra("selectlist",arrayData);
                        startActivity(i);
                    }
                    else {
                        Intent i = new Intent(getActivity(), StoryPostActivity.class);
                        Gson gson = new Gson();
                        String arrayData = gson.toJson(imagesSelected);
                        i.putExtra("selectlist",arrayData);
                        startActivity(i);
                    }


                }
            }
        });

        txt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.h.sendEmptyMessage(1);
            }
        });

    }


    public void getPicBuckets(){
        bitmapList.clear();
        bucketNames.clear();
        Cursor cursor = getContext().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection,
                null, null, MediaStore.Images.Media.DATE_ADDED);
        ArrayList<String> bucketNamesTEMP = new ArrayList<>(cursor.getCount());
        ArrayList<String> bitmapListTEMP = new ArrayList<>(cursor.getCount());
        HashSet<String> albumSet = new HashSet<>();
        File file;
        if (cursor.moveToLast()) {
            do {
                if (Thread.interrupted()) {
                    return;
                }
                String album = cursor.getString(cursor.getColumnIndex(projection[0]));
                String image = cursor.getString(cursor.getColumnIndex(projection[1]));
                file = new File(image);
                if (file.exists() && !albumSet.contains(album)) {
                    bucketNamesTEMP.add(album);
                    bitmapListTEMP.add(image);
                    albumSet.add(album);
                }
            } while (cursor.moveToPrevious());
        }
        cursor.close();
        if (bucketNamesTEMP == null) {
            bucketNames = new ArrayList<>();
        }
        bucketNames.clear();
        bitmapList.clear();
        bucketNames.addAll(bucketNamesTEMP);
        bitmapList.addAll(bitmapListTEMP);
    }


    public void getPictures(String bucket){
        selected.clear();
        imagesSelected.clear();
        Cursor cursor = getContext().getContentResolver()
                .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection2,
                        MediaStore.Images.Media.BUCKET_DISPLAY_NAME+" =?",new String[]{bucket},MediaStore.Images.Media.DATE_ADDED);
        ArrayList<String> imagesTEMP = new ArrayList<>(cursor.getCount());
        HashSet<String> albumSet = new HashSet<>();
        File file;
        if (cursor.moveToLast()) {
            do {
                if (Thread.interrupted()) {
                    return;
                }
                String path = cursor.getString(cursor.getColumnIndex(projection2[1]));
                file = new File(path);
                if (file.exists() && !albumSet.contains(path)) {
                    imagesTEMP.add(path);
                    albumSet.add(path);
                    selected.add(false);
                }
            } while (cursor.moveToPrevious());
        }
        cursor.close();
        if (imagesTEMP == null) {
            imagesTEMP = new ArrayList<>();
        }
        imagesList.clear();
        imagesList.addAll(imagesTEMP);

        populateRecyclerView();
    }

    private void populateRecyclerView() {
        for (int i = 0; i < selected.size(); i++) {
            if (imagesSelected.contains(imagesList.get(i))) {
                selected.set(i, true);
            } else {
                selected.set(i, false);
            }
        }
        mediaAdapter = new MediaAdapter(imagesList, selected, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.getItemAnimator().setChangeDuration(0);
        recyclerView.setAdapter(mediaAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                if (!selected.get(position).equals(true) && imagesSelected.size() < 6) {
                    imagesSelected.add(imagesList.get(position));
                    Log.d("position","---->" + imagesList.get(position));
                    selected.set(position, !selected.get(position));
                    mediaAdapter.notifyItemChanged(position);
                } else if (selected.get(position).equals(true)) {
                    if (imagesSelected.indexOf(imagesList.get(position)) != -1) {
                        imagesSelected.remove(imagesSelected.indexOf(imagesList.get(position)));
                        selected.set(position, !selected.get(position));
                        mediaAdapter.notifyItemChanged(position);
                    }
                }

            }

            @Override
            public void onLongClick(View view, int position) {

            }

        }));
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {
        private GestureDetector gestureDetector;
        private GalleryFragment.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final GalleryFragment.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }
    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }
}