package com.wdp.ActivityScreen;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sendbird.android.BaseChannel;
import com.sendbird.android.BaseMessage;
import com.sendbird.android.GroupChannel;
import com.sendbird.android.GroupChannelListQuery;
import com.sendbird.android.SendBird;
import com.sendbird.android.SendBirdException;
import com.wdp.Adapter.GroupChannelListAdapter;
import com.wdp.Agora.VideoCallActivity;
import com.wdp.Agora.live.BaseActivity;
import com.wdp.CubeScreen.CubeGLSurfaceView;
import com.wdp.CubeScreen.CubeModal;
import com.wdp.CubeScreen.DragControl;
import com.wdp.Modal.LoginResDataModal;
import com.wdp.SendBird.ConnectionManager;
import com.wdp.Utility.CommonSharedPreference;
import com.wdp.turnstr.R;

import java.util.List;
import java.util.Locale;

public class ChatDashboardActivity extends BaseActivity implements View.OnClickListener {
    ImageView imgBack, imgVideoCall;
    RelativeLayout rl_cube, relaySearch;
    RecyclerView mRecyclerView;
    EditText edtSearch;
    private SwipeRefreshLayout mSwipeRefresh;
    TextView txtNoUser, txtCancel;
    LinearLayout llChatSearch;
    CubeGLSurfaceView glView;
    private DragControl dragControl;
    private static final int CHANNEL_LIST_LIMIT = 15;
    String channel_name ="";
    private RelativeLayout relayChat;
    private GroupChannelListAdapter mChannelListAdapter;
    LoginResDataModal loginResDataModal;
    private GroupChannelListQuery mChannelListQuery;
    private LinearLayoutManager mLayoutManager;
    private static final String CONNECTION_HANDLER_ID = "CONNECTION_HANDLER_GROUP_CHANNEL_LIST";
    private static final String CHANNEL_HANDLER_ID = "CHANNEL_HANDLER_GROUP_CHANNEL_LIST";
    CommonSharedPreference commonSharedPreference = new CommonSharedPreference();
    private static final int INTENT_REQUEST_NEW_GROUP_CHANNEL = 302;
    ImageView img_cube;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_dashboard);
        imgBack = findViewById(R.id.imgBack);
        imgVideoCall = findViewById(R.id.imgVideoCall);
        txtCancel = findViewById(R.id.txtCancel);
        edtSearch = findViewById(R.id.edtSearch);
        rl_cube = findViewById(R.id.rl_cube);
        relaySearch = findViewById(R.id.relaySearch);
        relayChat = findViewById(R.id.relayChat);
        txtNoUser = findViewById(R.id.txtNoUser);
        llChatSearch = findViewById(R.id.llChatSearch);
        img_cube = findViewById(R.id.img_cube);

        mRecyclerView = (RecyclerView) findViewById(R.id.rvChatList);
        mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_layout_group_channel_list);

        loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(this);
        channel_name = loginResDataModal.getData().getUser().getId()+"_"+loginResDataModal.getData().getUser().getName().replace(" ","");

        String avatar1="",avatar2="",avatar3="",avatar4="",avatar5="",avatar6="";
        if (loginResDataModal.getData().getUser().getAvatar()!= null) {
            avatar1 = loginResDataModal.getData().getUser().getAvatar().getThumb();
        }
        if (loginResDataModal.getData().getUser().getAvatar2()!= null) {
            avatar2 = loginResDataModal.getData().getUser().getAvatar2().getThumb();
        }
        if (loginResDataModal.getData().getUser().getAvatar3()!= null) {
            avatar3 = loginResDataModal.getData().getUser().getAvatar3().getThumb();
        }
        if (loginResDataModal.getData().getUser().getAvatar4()!= null) {
            avatar4 = loginResDataModal.getData().getUser().getAvatar4().getThumb();
        }
        if (loginResDataModal.getData().getUser().getAvatar5()!= null) {
            avatar5 = loginResDataModal.getData().getUser().getAvatar5().getThumb();
        }
        if (loginResDataModal.getData().getUser().getAvatar6()!= null) {
            avatar6 = loginResDataModal.getData().getUser().getAvatar6().getThumb();
        }
        glView = new CubeGLSurfaceView(this,avatar1,avatar2,avatar3,avatar4,avatar5,avatar6);
        rl_cube.setBackgroundColor(getResources().getColor(R.color.colorOrangelite));
        rl_cube.addView(glView);
        dragControl = new DragControl(this,this,avatar1,avatar2,avatar3,avatar4,avatar5,avatar6);
        glView.setOnTouchListener(dragControl);
        glView.setDragControl(dragControl);
        glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        glView.setZOrderOnTop(false);
        glView.setZOrderMediaOverlay(true);
        imgBack.setOnClickListener(this);
        imgVideoCall.setOnClickListener(this);
        relayChat.setOnClickListener(this);
        llChatSearch.setOnClickListener(this);
        txtCancel.setOnClickListener(this);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefresh.setRefreshing(true);
                refresh();
            }
        });

        mChannelListAdapter = new GroupChannelListAdapter(ChatDashboardActivity.this);
        /*if (*/mChannelListAdapter.load();/*){
            txtNoUser.setVisibility(View.GONE);
            mSwipeRefresh.setVisibility(View.VISIBLE);
        }*/

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String value = edtSearch.getText().toString().toLowerCase(Locale.getDefault());
                mChannelListAdapter.filter(value);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        img_cube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ChatDashboardActivity.this, MyStoriesActivity.class);
                startActivity(i);
            }
        });


        setUpRecyclerView();
        setUpChannelListAdapter();
    }

    // Sets up recycler view
    private void setUpRecyclerView() {
        mLayoutManager = new LinearLayoutManager(ChatDashboardActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mChannelListAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(ChatDashboardActivity.this, DividerItemDecoration.VERTICAL));

        // If user scrolls to bottom of the list, loads more channels.
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (mLayoutManager.findLastVisibleItemPosition() == mChannelListAdapter.getItemCount() - 1) {
                    loadNextChannelList();
                }
            }
        });
    }

    // Sets up channel list adapter
    private void setUpChannelListAdapter() {
        mChannelListAdapter.setOnItemClickListener(new GroupChannelListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(GroupChannel channel) {
                enterGroupChannel(channel);
            }
        });

        mChannelListAdapter.setOnItemLongClickListener(new GroupChannelListAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(final GroupChannel channel) {
                showChannelOptionsDialog(channel);
            }
        });
    }

    void enterGroupChannel(GroupChannel channel) {
        final String channelUrl = channel.getUrl();
        String channelName;
        if ((channel.getMembers().get(0).getNickname()).equalsIgnoreCase(loginResDataModal.getData().getUser().getName())){
            channelName = channel.getMembers().get(1).getNickname();
        }else {
            channelName = channel.getMembers().get(0).getNickname();
        }
        enterGroupChannel(channelUrl, channelName);
    }

    /**
     * Enters a Group Channel with a URL.
     *
     * @param channelUrl The URL of the channel to enter.
     */
    void enterGroupChannel(String channelUrl, String channel_name) {
        Intent intent = new Intent(ChatDashboardActivity.this, ChatActivity.class);
        intent.putExtra("channelUrl", channelUrl);
        intent.putExtra("channelName", channel_name);
        startActivity(intent);
    }

    /**
     * Loads the next channels from the current query instance.
     */
    private void loadNextChannelList() {
        mChannelListQuery.next(new GroupChannelListQuery.GroupChannelListQueryResultHandler() {
            @Override
            public void onResult(List<GroupChannel> list, SendBirdException e) {
                if (e != null) {
                    // Error!
                    e.printStackTrace();
                    return;
                }

                for (GroupChannel channel : list) {
                    mChannelListAdapter.addLast(channel);
                }
            }
        });
    }

    /**
     * Displays a dialog listing channel-specific options.
     */
    private void showChannelOptionsDialog(final GroupChannel channel) {
        String[] options;
        final boolean pushCurrentlyEnabled = channel.isPushEnabled();

        options = pushCurrentlyEnabled
                ? new String[]{"Leave channel", "Turn push notifications OFF"}
                : new String[]{"Leave channel", "Turn push notifications ON"};

        AlertDialog.Builder builder = new AlertDialog.Builder(ChatDashboardActivity.this);
        builder.setTitle("Channel options")
                .setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            // Show a dialog to confirm that the user wants to leave the channel.
                            new AlertDialog.Builder(ChatDashboardActivity.this)
                                    .setTitle("Leave channel " + channel.getName() + "?")
                                    .setPositiveButton("Leave", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            leaveChannel(channel);
                                        }
                                    })
                                    .setNegativeButton("Cancel", null)
                                    .create().show();
                        } else if (which == 1) {
                            setChannelPushPreferences(channel, !pushCurrentlyEnabled);
                        }
                    }
                });
        builder.create().show();
    }

    private void setChannelPushPreferences(final GroupChannel channel, final boolean on) {
        // Change push preferences.
        channel.setPushPreference(on, new GroupChannel.GroupChannelSetPushPreferenceHandler() {
            @Override
            public void onResult(SendBirdException e) {
                if (e != null) {
                    e.printStackTrace();
                    Toast.makeText(ChatDashboardActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT)
                            .show();
                    return;
                }

                String toast = on
                        ? "Push notifications have been turned ON"
                        : "Push notifications have been turned OFF";

                Toast.makeText(ChatDashboardActivity.this, toast, Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    private void leaveChannel(final GroupChannel channel) {
        channel.leave(new GroupChannel.GroupChannelLeaveHandler() {
            @Override
            public void onResult(SendBirdException e) {
                if (e != null) {
                    // Error!
                    return;
                }

                // Re-query message list
                refresh();
            }
        });
    }

    private void refresh() {
        refreshChannelList(CHANNEL_LIST_LIMIT);
    }

    private void refreshChannelList(int numChannels) {
        mChannelListQuery = GroupChannel.createMyGroupChannelListQuery();
        mChannelListQuery.setLimit(numChannels);

        mChannelListQuery.next(new GroupChannelListQuery.GroupChannelListQueryResultHandler() {
            @Override
            public void onResult(List<GroupChannel> list, SendBirdException e) {
                if (e != null) {
                    // Error!
                    e.printStackTrace();
                    return;
                }

                mChannelListAdapter.clearMap();
                mChannelListAdapter.setGroupChannelList(list);
            }
        });

        if (mSwipeRefresh.isRefreshing()) {
            mSwipeRefresh.setRefreshing(false);
        }
    }


    @Override
    public void onClick(View view) {
        int id= view.getId();
        if (id==R.id.imgBack){
            finish();
        }else if (id==R.id.imgVideoCall){
            Intent intent = new Intent(this, VideoCallActivity.class);
            config().setChannelName(channel_name);
            startActivity(intent);
        }else if (id==R.id.relayChat){
            Intent intent = new Intent(ChatDashboardActivity.this, FollowingListActivity.class);
            intent.putExtra("from", "ChatDashboardActivity");
            startActivityForResult(intent, INTENT_REQUEST_NEW_GROUP_CHANNEL);
        }else if (id==R.id.llChatSearch){
            relaySearch.setVisibility(View.VISIBLE);
        }else if (id==R.id.txtCancel){
            relaySearch.setVisibility(View.GONE);
            edtSearch.setText("");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INTENT_REQUEST_NEW_GROUP_CHANNEL) {
            if (resultCode == RESULT_OK) {
                // Channel successfully created
                // Enter the newly created channel.
                String newChannelUrl = data.getStringExtra(FollowingListActivity.EXTRA_NEW_CHANNEL_URL);
                if (newChannelUrl != null) {
                    Intent intent = new Intent(ChatDashboardActivity.this, ChatActivity.class);
                    intent.putExtra("channelUrl", newChannelUrl);
                    startActivity(intent);
                }
            } else {
                Log.d("GrChLIST", "resultCode not STATUS_OK");
            }
        }
    }

    @Override
    public void onPause() {
        mChannelListAdapter.save();

        Log.d("LIFECYCLE", "GroupChannelListFragment onPause()");

        ConnectionManager.removeConnectionManagementHandler(CONNECTION_HANDLER_ID);
        SendBird.removeChannelHandler(CHANNEL_HANDLER_ID);
        super.onPause();
    }

    @Override
    public void onResume() {
        Log.d("LIFECYCLE", "GroupChannelListFragment onResume()");

        ConnectionManager.addConnectionManagementHandler(loginResDataModal.getData().getUser().getId(), CONNECTION_HANDLER_ID, new ConnectionManager.ConnectionManagementHandler() {
            @Override
            public void onConnected(boolean reconnect) {
                refresh();
            }
        });

        SendBird.addChannelHandler(CHANNEL_HANDLER_ID, new SendBird.ChannelHandler() {
            @Override
            public void onMessageReceived(BaseChannel baseChannel, BaseMessage baseMessage) {
            }

            @Override
            public void onChannelChanged(BaseChannel channel) {
                mChannelListAdapter.clearMap();
                mChannelListAdapter.updateOrInsert(channel);
            }

            @Override
            public void onTypingStatusUpdated(GroupChannel channel) {
                mChannelListAdapter.notifyDataSetChanged();
            }
        });

        super.onResume();
    }
}
