package com.wdp.ActivityScreen;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.wdp.Agora.VideoCallActivity;
import com.wdp.Agora.live.BaseActivity;
import com.wdp.Deepar.FragmentMask;
import com.wdp.Fragment.CubeFragment;
import com.wdp.Fragment.FilterLiveFragment;
import com.wdp.Fragment.ProfileFragment;
import com.wdp.Fragment.SearchFragment;
import com.wdp.Utility.CommanUtility;
import com.wdp.Fragment.HomeFragment;
import com.wdp.turnstr.R;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    Fragment fragment;
    RelativeLayout rl_search,rl_video,rl_profile,rl_cube,rl_home;
    ImageView img_home,img_profile,img_cube,img_search,img_video,imgChat;
    View toolbar;
    String channel_name="";
    public static Handler h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        initView();
        clickLister();

        h = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.d("Clicker's Message--->>>" , ""+msg.what);
                switch(msg.what) {
                    case 0:
                        finish();
                        startActivity(getIntent().putExtra("from", "EditProfile"));
                        break;
                    case 1:
                        finish();
                        startActivity(getIntent().putExtra("from", "CubeFragment"));
                }
            }
        };
        if (getIntent().getStringExtra("from")!=null){
            if (getIntent().getStringExtra("from").equalsIgnoreCase("EditProfile")){
                fragment = new ProfileFragment();
                CommanUtility.replaceFragment(fragment,this);
                defaultselectedColor();
                selectedColor(img_profile);
                toolbar.setVisibility(View.GONE);
            }else if (getIntent().getStringExtra("from").equalsIgnoreCase("CubeFragment")){
                fragment = new HomeFragment();
                CommanUtility.replaceFragment(fragment, this);
                defaultselectedColor();
                selectedColor(img_home);
            }
        }else {
            fragment = new HomeFragment();
            CommanUtility.replaceFragment(fragment, this);
            defaultselectedColor();
            selectedColor(img_home);
        }

        if (getIntent().getStringExtra("chanel_id")!=null){
            Log.d("ReceivedChannel--->>", getIntent().getStringExtra("chanel_id"));
            channel_name = getIntent().getStringExtra("chanel_id");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Join Video Call");
            builder.setMessage(getResources().getString(R.string.suretoopenvideoactivity));
            builder.setCancelable(true);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    Intent intent = new Intent(MainActivity.this, VideoCallActivity.class);
                    config().setChannelName(channel_name);
                    startActivity(intent);
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }else {
            Log.d("ReceivedChannel--->>", "empty");
        }
    }

    private void initView() {
        rl_search = findViewById(R.id.rl_search);
        rl_video = findViewById(R.id.rl_video);
        rl_profile = findViewById(R.id.rl_profile);
        rl_cube = findViewById(R.id.rl_cube);
        rl_home = findViewById(R.id.rl_home);
        img_home = findViewById(R.id.img_home);
        img_profile = findViewById(R.id.img_profile);
        img_cube = findViewById(R.id.img_cube);
        img_search = findViewById(R.id.img_search);
        img_video = findViewById(R.id.img_video);
        toolbar = findViewById(R.id.toolbar);
        imgChat = findViewById(R.id.imgChat);
    }

    private void clickLister(){
        rl_search.setOnClickListener(this);
        rl_video.setOnClickListener(this);
        rl_profile.setOnClickListener(this);
        rl_cube.setOnClickListener(this);
        rl_home.setOnClickListener(this);

        img_home.setOnClickListener(this);
        img_profile.setOnClickListener(this);
        img_cube.setOnClickListener(this);
        img_search.setOnClickListener(this);
        img_video.setOnClickListener(this);
        imgChat.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == img_search){
            fragment = new SearchFragment();
            CommanUtility.replaceFragment(fragment,this);
            defaultselectedColor();
            selectedColor(img_search);
            toolbar.setVisibility(View.GONE);
        }
        else if (view ==img_video){
            fragment = new FilterLiveFragment();
            CommanUtility.replaceFragment(fragment,this);
            defaultselectedColor();
            selectedColor(img_video);
            toolbar.setVisibility(View.GONE);
        }
        else if (view == img_profile){
            fragment = new ProfileFragment();
            CommanUtility.replaceFragment(fragment,this);
            defaultselectedColor();
            selectedColor(img_profile);
            toolbar.setVisibility(View.GONE);
        }
        else if (view == img_home){
            fragment = new HomeFragment();
            CommanUtility.replaceFragment(fragment,this);
            defaultselectedColor();
            selectedColor(img_home);
            toolbar.setVisibility(View.VISIBLE);
        }
        else if (view== img_cube){
            fragment = new CubeFragment();
            CommanUtility.replaceFragment(fragment,this);
            defaultselectedColor();
            selectedColor(img_cube);
            toolbar.setVisibility(View.GONE);
        }
        else if (view==imgChat){
            Intent intent = new Intent(this, ChatDashboardActivity.class);
            startActivity(intent);
        }
    }


    private void selectedColor(ImageView imageView){
        imageView.setColorFilter(ContextCompat.getColor(this, R.color.colorOrangelite));
    }

    private void defaultselectedColor(){
        img_home.setColorFilter(ContextCompat.getColor(this, R.color.ColorGray808080), android.graphics.PorterDuff.Mode.MULTIPLY);
        img_profile.setColorFilter(ContextCompat.getColor(this, R.color.ColorGray808080), android.graphics.PorterDuff.Mode.MULTIPLY);
        img_cube.setColorFilter(ContextCompat.getColor(this, R.color.ColorGray808080), android.graphics.PorterDuff.Mode.MULTIPLY);
        img_search.setColorFilter(ContextCompat.getColor(this, R.color.ColorGray808080), android.graphics.PorterDuff.Mode.MULTIPLY);
        img_video.setColorFilter(ContextCompat.getColor(this, R.color.ColorGray808080), android.graphics.PorterDuff.Mode.MULTIPLY);
    }

}