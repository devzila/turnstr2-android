package com.wdp.ActivityScreen;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wdp.Adapter.CommentListAdapter;
import com.wdp.ApiServices.PostReportApiService;
import com.wdp.ApiServices.PostsCommentApiService;
import com.wdp.ApiServices.PostsCommentsListApiService;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.CommetListDataModal;
import com.wdp.Modal.LoginResDataModal;
import com.wdp.Modal.PostCommentModal;
import com.wdp.Modal.PostReportDataModal;
import com.wdp.Utility.CommonSharedPreference;
import com.wdp.Utility.NetworkCheck;
import com.wdp.turnstr.R;

import java.util.ArrayList;

public class PostReportListActivity extends AppCompatActivity implements ApiResponseListner, View.OnClickListener {
    RadioGroup radioTabgroup;
    RadioButton radioHeader1,radioHeader2;
    TextView txt_title;
    ImageView img_back;
    Button btn_submit;
    String postid;
    EditText edt_message;
    CommonSharedPreference commonSharedPreference = new CommonSharedPreference();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_report);
        intiView();
        clickListner();
        prepair();


    }

    private void intiView() {
        txt_title = findViewById(R.id.txt_title);
        img_back = findViewById(R.id.img_back);
        radioTabgroup = findViewById(R.id.radioTabgroup);
        radioHeader1 = findViewById(R.id.radioHeader1);
        radioHeader2 = findViewById(R.id.radioHeader2);
        btn_submit = findViewById(R.id.btn_submit);
        edt_message = findViewById(R.id.edt_message);
    }

    private void clickListner(){
        img_back.setOnClickListener(this);
        btn_submit.setOnClickListener(this);


        radioTabgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioHeader1:
                        radioHeader1.setTextColor(getResources().getColor(R.color.white));
                        radioHeader2.setTextColor(getResources().getColor(R.color.blue));
                        break;
                    case R.id.radioHeader2:
                        radioHeader2.setTextColor(getResources().getColor(R.color.white));
                        radioHeader1.setTextColor(getResources().getColor(R.color.blue));
                        break;


                }

            }
        });
    }

    private void prepair() {
        postid = getIntent().getStringExtra("postid");
        txt_title.setText(getResources().getString(R.string.report_the_post));
        txt_title.setVisibility(View.VISIBLE);



        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }

    private void connectPostReport(String text,String reason) {
        LoginResDataModal loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(this);
        if (NetworkCheck.isConnected(this)) {
            PostReportApiService postReportApiService = new PostReportApiService(this);
            postReportApiService.Connect(loginResDataModal.getData().getToken(), postid, reason,text,this);

        } else {
            Toast.makeText(this, getResources().getString(R.string.server_data_not_found), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSuccess(String Tag, SuperCastClass superCastClass) {
        if (Tag.equalsIgnoreCase(ApiConstants.posts_reason_tag)){
            PostReportDataModal postReportDataModal = (PostReportDataModal) superCastClass;
            Toast.makeText(this,postReportDataModal.getMessage(),Toast.LENGTH_LONG).show();
            finish();

        }

    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onException(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        if (btn_submit ==view){
            if (TextUtils.isEmpty(edt_message.getText().toString())){
                Toast.makeText(this,getResources().getString(R.string.pls_enter_message),Toast.LENGTH_LONG).show();
            }
            else {
                String text =edt_message.getText().toString();
                String reason="";
                if (radioHeader1.isChecked()){
                    reason = radioHeader1.getText().toString().toLowerCase();
                }
                else if (radioHeader2.isChecked()){
                    reason = radioHeader2.getText().toString().toLowerCase();
                }

                connectPostReport(text,reason);
            }

        }


    }
}
