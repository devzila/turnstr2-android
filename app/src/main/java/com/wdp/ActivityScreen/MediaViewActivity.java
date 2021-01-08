package com.wdp.ActivityScreen;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wdp.Adapter.HomeSliderAdapter;
import com.wdp.ApiServices.ForgotPasswordApiService;
import com.wdp.CubeScreen.CubeGLSurfaceView;
import com.wdp.CubeScreen.DragControl;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.CubeDataModal;
import com.wdp.Modal.CubeFullViewDataModal;
import com.wdp.Modal.ForgotpasswordDataModal;
import com.wdp.Modal.PostDataModal;
import com.wdp.Utility.CommanUtility;
import com.wdp.Utility.CubeTransformer;
import com.wdp.Utility.NetworkCheck;
import com.wdp.Utility.WrapContentHeightViewPager;
import com.wdp.turnstr.R;

import java.util.ArrayList;
import java.util.List;

public class MediaViewActivity extends Activity  {
    LinearLayout ll_cube;
    private DragControl dragControl;
    CubeGLSurfaceView glView;
    HomeSliderAdapter homeSliderAdapter;
    WrapContentHeightViewPager wrapContentHeightViewPager;
    ArrayList<CubeFullViewDataModal> dataList = new ArrayList<>();
    ImageView img_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dummy_activity);
        initView();
        clickListner();
        prepair();

    }
    private void initView(){
        wrapContentHeightViewPager = findViewById(R.id.pager);
        img_back = findViewById(R.id.img_back);

    }

    private void clickListner(){
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void prepair(){
        Gson gson = new Gson();
        if (getIntent().getStringExtra("medialist") != null){
            String  imageUrl = getIntent().getStringExtra("medialist");
            TypeToken<List<CubeFullViewDataModal>> token = new TypeToken<List<CubeFullViewDataModal>>() {};
            dataList = gson.fromJson(imageUrl, token.getType());
            homeSliderAdapter = new HomeSliderAdapter(this, dataList);
            wrapContentHeightViewPager.setPageTransformer(true, new CubeTransformer());
            wrapContentHeightViewPager.setAdapter(homeSliderAdapter);
        }

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (homeSliderAdapter != null){
            homeSliderAdapter.releasePlayer();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (homeSliderAdapter != null){
            homeSliderAdapter.releasePlayer();
        }
    }
}
