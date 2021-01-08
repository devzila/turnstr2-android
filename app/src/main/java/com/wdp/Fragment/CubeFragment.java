package com.wdp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.wdp.Utility.CommanUtility;
import com.wdp.turnstr.R;

public class CubeFragment extends Fragment {
    RadioGroup radioTabgroup;
    View root;
    Fragment fragment;
    String whichScreen="post";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_cube, container, false);
        initView();
        prepair();
        fragment =  new GalleryFragment(whichScreen);
        CommanUtility.replaceFragmentContainer(fragment,getActivity());

        return root;
    }

    private void initView() {
        radioTabgroup = root.findViewById(R.id.radioTabgroup);
    }

    private void prepair(){
        radioTabgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioHeader1:
                        fragment =  new GalleryFragment(whichScreen);
                        CommanUtility.replaceFragmentContainer(fragment,getActivity());
                        break;
                    case R.id.radioHeader2:
                        whichScreen = "image";
                        fragment =  new FragmentCubeMask(whichScreen);
                        CommanUtility.replaceFragmentContainer(fragment,getActivity());
                        break;

                    case R.id.radioHeader3:
                        whichScreen = "video";
                        fragment =  new FragmentCubeMask(whichScreen);
                        CommanUtility.replaceFragmentContainer(fragment,getActivity());
                        break;
                }

            }
        });
    }
}