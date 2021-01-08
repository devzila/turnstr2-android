package com.wdp.ActivityScreen;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.wdp.Fragment.FragmentCubeBeautyMask;
import com.wdp.Fragment.FragmentCubeMask;
import com.wdp.Fragment.FragmentFilter;
import com.wdp.Fragment.GalleryFragment;
import com.wdp.Utility.CommanUtility;
import com.wdp.turnstr.R;

public class FilterActivity extends AppCompatActivity {
    RadioGroup radioTabgroup;
    Fragment fragment;
    String whichScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_cube);
        initView();
        prepair();
    }
    private void initView() {
        radioTabgroup = findViewById(R.id.radioTabgroup);
    }

    private void prepair(){
        whichScreen = getIntent().getStringExtra("whichScreen");
        fragment =  new FragmentFilter();
        CommanUtility.replaceFragmentContainer(fragment,FilterActivity.this);
        radioTabgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioHeader1:
                        fragment =  new GalleryFragment(whichScreen);
                        CommanUtility.replaceFragmentContainer(fragment,FilterActivity.this);
                        break;
                    case R.id.radioHeader2:
                        fragment =  new FragmentFilter(whichScreen);
                        CommanUtility.replaceFragmentContainer(fragment,FilterActivity.this);
                        break;

                    case R.id.radioHeader3:
                        fragment =  new FragmentCubeBeautyMask(whichScreen);
                        CommanUtility.replaceFragmentContainer(fragment,FilterActivity.this);
                        break;
                }

            }
        });
    }
}