package com.wdp.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.wdp.ActivityScreen.FilterActivity;
import com.wdp.Utility.CommanUtility;
import com.wdp.turnstr.R;

public class FilterLiveFragment extends Fragment {
    RadioGroup radioTabgroup;
    Fragment fragment;
    String whichScreen="storypost";
    View root;



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.activity_filter_cube, container, false);

        initView();
        prepair();
        return root;
    }
    private void initView() {
        radioTabgroup = root.findViewById(R.id.radioTabgroup);
        fragment =  new FragmentFilter(whichScreen);
        CommanUtility.replaceFragmentContainer(fragment, getActivity());
    }

    private void prepair(){

        radioTabgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioHeader1:
                        fragment =  new GalleryFragment(whichScreen);
                        CommanUtility.replaceFragmentContainer(fragment, getActivity());
                        break;
                    case R.id.radioHeader2:
                        fragment =  new FragmentFilter(whichScreen);
                        CommanUtility.replaceFragmentContainer(fragment, getActivity());
                        break;

                    case R.id.radioHeader3:
                        fragment =  new FragmentCubeBeautyMask(whichScreen);
                        CommanUtility.replaceFragmentContainer(fragment, getActivity());
                        break;
                }

            }
        });
    }
}