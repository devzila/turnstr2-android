package com.adroidtech.turnstr2.GoLive;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.adroidtech.turnstr2.GoLive.adapters.CommentsAdapter;
import com.adroidtech.turnstr2.R;
import com.adroidtech.turnstr2.Utils.GeneralValues;
import com.adroidtech.turnstr2.Utils.PreferenceKeys;
import com.adroidtech.turnstr2.Utils.SharedPreference;
import com.adroidtech.turnstr2.WebServices.AsyncCallback;
import com.adroidtech.turnstr2.WebServices.CommonAsync;
import com.adroidtech.turnstr2.chat.activitys.AllFriendListVideo;
import com.adroidtech.turnstr2.chat.groupchannel.GroupChatFragment;
import com.opentok.android.BaseVideoRenderer;
import com.opentok.android.Connection;
import com.opentok.android.OpentokError;
import com.opentok.android.Publisher;
import com.opentok.android.PublisherKit;
import com.opentok.android.Session;
import com.opentok.android.Stream;
import com.opentok.android.Subscriber;
import com.opentok.android.SubscriberKit;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;


public class MainVideoActivity extends AppCompatActivity
                            implements AsyncCallback, EasyPermissions.PermissionCallbacks,
                                        WebServiceCoordinator.Listener,
                                        Session.SessionListener,
                                        PublisherKit.PublisherListener,
                                        SubscriberKit.SubscriberListener, View.OnClickListener, Session.SignalListener {

    private static final String LOG_TAG = MainVideoActivity.class.getSimpleName();
    private static final int RC_SETTINGS_SCREEN_PERM = 123;
    private static final int RC_VIDEO_APP_PERM = 124;
    private static final String TAG = "MainVieoActivity.class";

    // Suppressing this warning. mWebServiceCoordinator will get GarbageCollected if it is local.
    @SuppressWarnings("FieldCanBeLocal")
    private WebServiceCoordinator mWebServiceCoordinator;

    private Session mSession;
    private Publisher mPublisher;
    private Subscriber mSubscriber;
    private Subscriber mSubscriber1;
    private Subscriber mSubscriber2;

    private RelativeLayout mPublisherViewContainer;
  //  private RelativeLayout mSubscriberViewContainer;
    private SharedPreference sharedPreference;
    private ImageView addMoreUser;
  //  private String memberID;
    List<Stream> streamList=new ArrayList<>();
  //  private RelativeLayout mSubscriberViewContainer1;
  //  private RelativeLayout mSubscriberViewContainer2;
   // private LinearLayout topview;
    private ImageView imgSpeaker;
    private ImageView imgSwitchCamera;
    private ImageView imgEndCall;
    private ImageView imgMic;
    private int call_typeFlag=-1;
    private ImageView btnSend;
    private EditText edtSend;
    List<String> message=new ArrayList<>();
    private RecyclerView recyclerView;
    private CommentsAdapter commentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.e(LOG_TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_chat_golive);


        Bundle bundle = getIntent().getExtras();
        if(null!=bundle)
        {

            String sessionId = bundle.getString("sessionId");
            String token = bundle.getString("token");
            String call_type = bundle.getString("call_type");

            Log.e("TAG", "......bundle......"+sessionId+"........."+token);


            if(null!=call_type && !call_type.isEmpty()){
                call_typeFlag=1;

            }

            if(null!=sessionId && !sessionId.isEmpty() && null!=token && !token.isEmpty() )
            {
                OpenTokConfig.SESSION_ID=sessionId;
                OpenTokConfig.TOKEN=token;

                requestPermissions();

            }



        }



//        memberID = getIntent().getStringExtra(GroupChatFragment.MEMBER);
//        String memberID_IN = getIntent().getStringExtra(AllFriendListVideo.INVITE_MEMBER);

      //  Log.e(TAG, "....memberid..."+memberID);


        sharedPreference = new SharedPreference(getApplicationContext());
        // initialize view objects from your layout
        mPublisherViewContainer = (RelativeLayout)findViewById(R.id.publisher_container);


         commentsAdapter=new CommentsAdapter(getApplicationContext(), message);
         recyclerView = (RecyclerView) findViewById(R.id.activity_comments_recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setStackFromEnd(true);
        manager.setReverseLayout(true);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(commentsAdapter);

//        mSubscriberViewContainer = (RelativeLayout)findViewById(R.id.subscriber_container);
//        mSubscriberViewContainer1 = (RelativeLayout)findViewById(R.id.subscriber_container1);
//        mSubscriberViewContainer2 = (RelativeLayout)findViewById(R.id.subscriber_container2);
//        topview = (LinearLayout)findViewById(R.id.topview);


        btnSend=(ImageView)findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this);
        edtSend=(EditText)findViewById(R.id.edtSend);
        addMoreUser=(ImageView)findViewById(R.id.addMoreUser);
        addMoreUser.setOnClickListener(this);

        imgMic=(ImageView)findViewById(R.id.imgMic);
        imgEndCall=(ImageView)findViewById(R.id.imgEndCall);
        imgSwitchCamera=(ImageView)findViewById(R.id.imgSwitchCamera);
        imgSpeaker=(ImageView)findViewById(R.id.imgSpeaker);
        imgMic.setOnClickListener(this);
        imgEndCall.setOnClickListener(this);
        imgSwitchCamera.setOnClickListener(this);
        imgSpeaker.setOnClickListener(this);


        try {

            if(call_typeFlag!=1)
            {
                getlive_sessionRequest("");
            }


//            if(null!=memberID)
//            {
//                getlive_sessionRequest(memberID);
//            }
//            else if(null!=memberID_IN)
//            {
//                getlive_sessionRequest(memberID_IN);
//                finish();
//            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //requestPermissions();



    }

    private void getlive_sessionRequest(String memberID) throws JSONException {

        Log.e(TAG, ".member id......."+memberID);
        JSONObject mJson = new JSONObject();

        mJson.put("call_type", "go_live");//"video_call");
        mJson.put("invitees", "");//memberID);

        Log.e("Tag", "test................");
        HashMap<String, String> extraHeaders = new HashMap<>();
        extraHeaders.put("auth_token", sharedPreference.getString(PreferenceKeys.APP_AUTH_TOKEN));

        new CommonAsync(this, "POST", this, GeneralValues.GOLIVE_SESSION, mJson, extraHeaders).execute();
        Log.e("Tag", "test................11");


    }

    private void live_notify_Request() throws JSONException {


        JSONObject mJson = new JSONObject();

       // mJson.put("call_type", "go_live");//"video_call");
       // mJson.put("invitees", "");//memberID);

        Log.e("Tag", "test................");
        HashMap<String, String> extraHeaders = new HashMap<>();
        extraHeaders.put("auth_token", sharedPreference.getString(PreferenceKeys.APP_AUTH_TOKEN));

        new CommonAsync(this, "POST", this, GeneralValues.LIVE_NOTIFY, mJson, extraHeaders).execute();
        Log.e("Tag", "test................11");


    }

     /* Activity lifecycle methods */

    @Override
    protected void onPause() {

        Log.e(LOG_TAG, "onPause");

        super.onPause();

        if (mSession != null) {
            mSession.onPause();
        }

    }

    @Override
    protected void onResume() {

        Log.e(LOG_TAG, "onResume");

        super.onResume();

        if (mSession != null) {
            mSession.onResume();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

        Log.e(LOG_TAG, "onPermissionsGranted:" + requestCode + ":" + perms.size());
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

        Log.d(LOG_TAG, "onPermissionsDenied:" + requestCode + ":" + perms.size());

        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this)
                    .setTitle(getString(R.string.title_settings_dialog))
                    .setRationale(getString(R.string.rationale_ask_again))
                    .setPositiveButton(getString(R.string.setting))
                    .setNegativeButton(getString(R.string.cancel))
                    .setRequestCode(RC_SETTINGS_SCREEN_PERM)
                    .build()
                    .show();
        }
    }

    @AfterPermissionGranted(RC_VIDEO_APP_PERM)
    private void requestPermissions() {

        String[] perms = { Manifest.permission.INTERNET, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO };
        if (EasyPermissions.hasPermissions(this, perms)) {
            // if there is no server URL set
            if (OpenTokConfig.CHAT_SERVER_URL == null) {
                // use hard coded session values
                if (OpenTokConfig.areHardCodedConfigsValid()) {
                    initializeSession(OpenTokConfig.API_KEY, OpenTokConfig.SESSION_ID, OpenTokConfig.TOKEN);
                } else {
                    showConfigError("Configuration Error", OpenTokConfig.hardCodedConfigErrorMessage);
                }
            } else {
                // otherwise initialize WebServiceCoordinator and kick off request for session data
                // session initialization occurs once data is returned, in onSessionConnectionDataReady
                if (OpenTokConfig.isWebServerConfigUrlValid()) {
                    mWebServiceCoordinator = new WebServiceCoordinator(this, this);
                    mWebServiceCoordinator.fetchSessionConnectionData(OpenTokConfig.SESSION_INFO_ENDPOINT);
                } else {
                    showConfigError("Configuration Error", OpenTokConfig.webServerConfigErrorMessage);
                }
            }
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_video_app), RC_VIDEO_APP_PERM, perms);
        }
    }

    private void initializeSession(String apiKey, String sessionId, String token) {

        mSession = new Session.Builder(this, apiKey, sessionId).build();
        mSession.setSessionListener(this);
        mSession.connect(token);
    }

    /* Web Service Coordinator delegate methods */

    @Override
    public void onSessionConnectionDataReady(String apiKey, String sessionId, String token) {

        Log.d(LOG_TAG, "ApiKey: "+apiKey + " SessionId: "+ sessionId + " Token: "+token);
        initializeSession(apiKey, sessionId, token);
    }

    @Override
    public void onWebServiceCoordinatorError(Exception error) {

        Log.e(LOG_TAG, "Web Service error: " + error.getMessage());
        Toast.makeText(this, "Web Service error: " + error.getMessage(), Toast.LENGTH_LONG).show();
        finish();

    }




    /* Session Listener methods */

    @Override
    public void onConnected(Session session) {

        Log.e(LOG_TAG, "onConnected: Connected to session: "+session.getSessionId());



        try {
            if(call_typeFlag!=1)
            {
                // initialize Publisher and set this object to listen to Publisher events
                mPublisher = new Publisher.Builder(this).build();
                mPublisher.setPublisherListener(this);

                // set publisher video style to fill view
                mPublisher.getRenderer().setStyle(BaseVideoRenderer.STYLE_VIDEO_SCALE,
                        BaseVideoRenderer.STYLE_VIDEO_FILL);
                mPublisherViewContainer.addView(mPublisher.getView());
                if (mPublisher.getView() instanceof GLSurfaceView) {
                    ((GLSurfaceView) mPublisher.getView()).setZOrderOnTop(true);
                }

                mSession.publish(mPublisher);
                mSession.setSignalListener(this);
                live_notify_Request();

            }
            else {
                //mPublisherViewContainer.setVisibility(View.GONE);
                imgMic.setVisibility(View.GONE);
                imgSwitchCamera.setVisibility(View.GONE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisconnected(Session session) {

        Log.e(LOG_TAG, "onDisconnected: Disconnected from session: "+session.getSessionId());
    }

    @Override
    public void onStreamReceived(Session session, Stream stream) {

        Log.e(LOG_TAG, "onStreamReceived: New Stream Received "+stream.getStreamId() + " in session: "+session.getSessionId());


        mSubscriber = new Subscriber.Builder(this, stream).build();
            mSubscriber.getRenderer().setStyle(BaseVideoRenderer.STYLE_VIDEO_SCALE, BaseVideoRenderer.STYLE_VIDEO_FILL);
            mSubscriber.setSubscriberListener(this);
            mSession.subscribe(mSubscriber);
        mSession.setSignalListener(this);
//            if(call_typeFlag==1) {
//                mPublisherViewContainer.addView(mSubscriber.getView());
//            }
        mPublisherViewContainer.addView(mSubscriber.getView());
        if (mSubscriber.getView() instanceof GLSurfaceView) {
            ((GLSurfaceView) mSubscriber.getView()).setZOrderOnTop(true);
        }


      //  }


    }

    @Override
    public void onStreamDropped(Session session, Stream stream) {

        Log.e(LOG_TAG, "onStreamDropped: Stream Dropped: "+stream.getStreamId() +" in session: "+session.getSessionId());

//        if (mSubscriber != null) {
//            mSubscriber = null;
//            mSubscriberViewContainer.removeAllViews();
//        }
//        if (mSubscriber1 != null) {
//            mSubscriber1 = null;
//            mSubscriberViewContainer1.removeAllViews();
//        }if (mSubscriber2 != null) {
//            mSubscriber2 = null;
//            mSubscriberViewContainer2.removeAllViews();
//        }
    }

    @Override
    public void onError(Session session, OpentokError opentokError) {
        Log.e(LOG_TAG, "onError: "+ opentokError.getErrorDomain() + " : " +
                opentokError.getErrorCode() + " - "+opentokError.getMessage() + " in session: "+ session.getSessionId());

        showOpenTokError(opentokError);
    }

    /* Publisher Listener methods */

    @Override
    public void onStreamCreated(PublisherKit publisherKit, Stream stream) {

        Log.d(LOG_TAG, "onStreamCreated: Publisher Stream Created. Own stream "+stream.getStreamId());

    }

    @Override
    public void onStreamDestroyed(PublisherKit publisherKit, Stream stream) {

        Log.d(LOG_TAG, "onStreamDestroyed: Publisher Stream Destroyed. Own stream "+stream.getStreamId());
    }

    @Override
    public void onError(PublisherKit publisherKit, OpentokError opentokError) {

        Log.e(LOG_TAG, "onError: "+opentokError.getErrorDomain() + " : " +
                opentokError.getErrorCode() +  " - "+opentokError.getMessage());

        showOpenTokError(opentokError);
    }

    @Override
    public void onConnected(SubscriberKit subscriberKit) {

        Log.e(LOG_TAG, "onConnected: Subscriber connected. Stream: "+subscriberKit.getStream().getStreamId());
    }

    @Override
    public void onDisconnected(SubscriberKit subscriberKit) {

        Log.e(LOG_TAG, "onDisconnected: Subscriber disconnected. Stream: "+subscriberKit.getStream().getStreamId());
    }

    @Override
    public void onError(SubscriberKit subscriberKit, OpentokError opentokError) {

        Log.e(LOG_TAG, "onError: "+opentokError.getErrorDomain() + " : " +
                opentokError.getErrorCode() +  " - "+opentokError.getMessage());

        showOpenTokError(opentokError);
    }

    private void showOpenTokError(OpentokError opentokError) {

        Toast.makeText(this, opentokError.getErrorDomain().name() +": " +opentokError.getMessage() + " Please, see the logcat.", Toast.LENGTH_LONG).show();
        finish();
    }

    private void showConfigError(String alertTitle, final String errorMessage) {
        Log.e(LOG_TAG, "Error " + alertTitle + ": " + errorMessage);
        new AlertDialog.Builder(this)
                .setTitle(alertTitle)
                .setMessage(errorMessage)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        MainVideoActivity.this.finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void getAsyncResult(String json, String txt) {


        Log.e("TAG", "jsonObject........"+json);
        Log.e("TAG", "txt........"+txt);

        if(txt.toString().trim().equals("/v1/user/golive_session".trim()))
        {
            if (null != json) {

                JSONObject jsonobject = null;
                try {
                    jsonobject = new JSONObject(json);

                    if (null != jsonobject) {
                        String name;
                        Iterator<String> keys = jsonobject.keys();

                        while (keys.hasNext()) {

                            name = keys.next();
                            System.out.println("key namn:::::::" + name);
                            if (name.equals("status")) {
                            }
                            if (name.equals("data")) {

                                JSONObject dataJson = jsonobject.getJSONObject(name);
                                if (null != dataJson) {
                                    String dataName;
                                    Iterator<String> dataKey = dataJson.keys();
                                    while (dataKey.hasNext()) {
                                        dataName = dataKey.next();
                                        if (dataName.equals("session")) {

                                            JSONObject sessionObject=dataJson.getJSONObject(dataName);

                                            if(null!=sessionObject)
                                            {
                                                String sessionName;
                                                Iterator<String> sessionKey=sessionObject.keys();
                                                while (sessionKey.hasNext())
                                                {
                                                    sessionName=sessionKey.next();

                                                    if(sessionName.equals("id")){
                                                        Log.e(TAG, "...id.."+sessionObject.getString(sessionName));
                                                    }if(sessionName.equals("completed")){
                                                    Log.e(TAG, "...completed.."+sessionObject.getString(sessionName));
                                                }if(sessionName.equals("created_at")){
                                                    Log.e(TAG, "...created_at.."+sessionObject.getString(sessionName));
                                                }if(sessionName.equals("session_id")){
                                                    Log.e(TAG, "...session_id.."+sessionObject.getString(sessionName));
                                                    OpenTokConfig.SESSION_ID=sessionObject.getString(sessionName);
                                                }if(sessionName.equals("session_type")){
                                                    Log.e(TAG, "...session_type.."+sessionObject.getString(sessionName));
                                                }if(sessionName.equals("token")){
                                                    Log.e(TAG, "...token.."+sessionObject.getString(sessionName));
                                                    OpenTokConfig.TOKEN=sessionObject.getString(sessionName);
                                                }if(sessionName.equals("updated_at")){
                                                    Log.e(TAG, "...updated_at.."+sessionObject.getString(sessionName));
                                                }if(sessionName.equals("user_id")){
                                                    Log.e(TAG, "...user_id.."+sessionObject.getString(sessionName));
                                                }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception ex){
                    ex.printStackTrace();
                }

                if(null!= OpenTokConfig.TOKEN && !OpenTokConfig.TOKEN.isEmpty() && null!= OpenTokConfig.SESSION_ID && !OpenTokConfig.SESSION_ID.isEmpty())
                {
                    Log.e(TAG, "................ requestPermissions .....");


                    requestPermissions();

                }

            }
        }


    }

    @Override
    public void onClick(View v) {

        if(v==addMoreUser)
        {
            startActivity(new Intent(getApplicationContext(), AllFriendListVideo.class));
        }
        if(v==imgMic)
        {
            if(mPublisher.getPublishAudio())
            {
                imgMic.setImageDrawable(getResources().getDrawable(R.drawable.ic_mic_off));
                mPublisher.setPublishAudio(false);
            }
            else
            {
                imgMic.setImageDrawable(getResources().getDrawable(R.drawable.ic_mic_on));
                mPublisher.setPublishAudio(true);
            }

        }
        if(v==imgEndCall)
        {
            if(null!=mSession)
            {
                mSession.disconnect();
                finish();
            }

        }
        if(v==imgSwitchCamera)
        {
            mPublisher.cycleCamera();
        }
        if(v==imgSpeaker)
        {

        }
        if(v==btnSend)
        {
            if(edtSend.getText().toString().trim().isEmpty())
            {
                Toast.makeText(getApplicationContext(), "Enter message ", Toast.LENGTH_SHORT).show();
                return;
            }

            mSession.sendSignal("chat", edtSend.getText().toString().trim());

            edtSend.setText("");
        }

    }

    @Override
    public void onSignalReceived(Session session, String s, String s1, Connection connection) {
        String myConnectionId = session.getConnection().getConnectionId();
        if (connection != null && connection.getConnectionId().equals(myConnectionId)) {
            // Signal received from another client
            Log.e("TAG", "............"+s +" ... "+s1 +"......."+session);
        }
        else {
            Log.e("TAG", "............"+s +" ... "+s1 +"......."+session);
        }

        message.add(s);

        commentsAdapter.notifyItemRangeInserted(message.size()-1, message.size());


    }
}
