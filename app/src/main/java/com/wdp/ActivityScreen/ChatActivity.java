package com.wdp.ActivityScreen;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.sendbird.android.AdminMessage;
import com.sendbird.android.BaseChannel;
import com.sendbird.android.BaseMessage;
import com.sendbird.android.FileMessage;
import com.sendbird.android.GroupChannel;
import com.sendbird.android.Member;
import com.sendbird.android.SendBird;
import com.sendbird.android.SendBirdException;
import com.sendbird.android.UserMessage;
import com.wdp.Adapter.GroupChatAdapter;
import com.wdp.Modal.LoginResDataModal;
import com.wdp.SendBird.ConnectionManager;
import com.wdp.SendBird.FileUtils;
import com.wdp.SendBird.MediaPlayerActivity;
import com.wdp.SendBird.PhotoViewerActivity;
import com.wdp.SendBird.TextUtils;
import com.wdp.SendBird.UrlPreviewInfo;
import com.wdp.SendBird.WebUtils;
import com.wdp.Utility.CommonSharedPreference;
import com.wdp.turnstr.R;

import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    String mChannelUrl = "", mChannelName="";
    RecyclerView mRecyclerView;
    private RelativeLayout mRootLayout;
    ImageView imgBack;
    TextView txtGroupName;
    private LinearLayoutManager mLayoutManager;
    private GroupChatAdapter mChatAdapter;
    private static final int CHANNEL_LIST_LIMIT = 30;
    LoginResDataModal loginResDataModal;
    CommonSharedPreference commonSharedPreference = new CommonSharedPreference();
    private static final String CONNECTION_HANDLER_ID = "CONNECTION_HANDLER_GROUP_CHAT";
    private static final String CHANNEL_HANDLER_ID = "CHANNEL_HANDLER_GROUP_CHANNEL_CHAT";
    private static final int INTENT_REQUEST_CHOOSE_MEDIA = 301;

    private static final int STATE_NORMAL = 0;
    private static final int STATE_EDIT = 1;
    private int mCurrentState = STATE_NORMAL;
    private GroupChannel mChannel;
    private ImageButton mUploadFileButton;
    private View mCurrentEventLayout;
    private TextView mCurrentEventText;
    private EditText mMessageEditText;
    private static final int PERMISSION_WRITE_EXTERNAL_STORAGE = 13;
    private Button mMessageSendButton;
    private InputMethodManager mIMM;
    private BaseMessage mEditingMessage = null;
    private boolean mIsTyping;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_chat);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mChannelUrl = getIntent().getStringExtra("channelUrl");
        mChannelName = getIntent().getStringExtra("channelName");
        loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(this);
        mIMM = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_group_chat);
        mRootLayout = (RelativeLayout) findViewById(R.id.layout_group_chat_root);

        mCurrentEventLayout = findViewById(R.id.layout_group_chat_current_event);
        mCurrentEventText = (TextView) findViewById(R.id.text_group_chat_current_event);
        imgBack = findViewById(R.id.imgBack);
        txtGroupName = findViewById(R.id.txtGroupName);

        mMessageEditText = (EditText) findViewById(R.id.edittext_group_chat_message);
        mMessageSendButton = (Button) findViewById(R.id.button_group_chat_send);
        mUploadFileButton = (ImageButton) findViewById(R.id.button_group_chat_upload);

        txtGroupName.setText(mChannelName);
        mChatAdapter = new GroupChatAdapter(ChatActivity.this);
        mIsTyping = false;
        mMessageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    mMessageSendButton.setEnabled(true);
                } else {
                    mMessageSendButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    mMessageSendButton.setEnabled(true);
                } else {
                    mMessageSendButton.setEnabled(false);
                }
            }
        });

        mMessageSendButton.setEnabled(false);
        mMessageSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentState == STATE_EDIT) {
                    String userInput = mMessageEditText.getText().toString();
                    if (userInput.length() > 0) {
                        if (mEditingMessage != null) {
                            editMessage(mEditingMessage, userInput);
                        }
                    }
                    setState(STATE_NORMAL, null, -1);
                } else {
                    String userInput = mMessageEditText.getText().toString();
                    if (userInput.length() > 0) {
                        sendUserMessage(userInput);
                        mMessageEditText.setText("");
                    }
                }
            }
        });

        mUploadFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestMedia();
            }
        });


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        setUpChatListAdapter();
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        mLayoutManager = new LinearLayoutManager(ChatActivity.this);
        mLayoutManager.setReverseLayout(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mChatAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (mLayoutManager.findLastVisibleItemPosition() == mChatAdapter.getItemCount() - 1) {
                    mChatAdapter.loadPreviousMessages(CHANNEL_LIST_LIMIT, null);
                }
            }
        });
    }

    private void requestMedia() {
        if (ContextCompat.checkSelfPermission(ChatActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // If storage permissions are not granted, request permissions at run-time,
            // as per < API 23 guidelines.
            requestStoragePermissions();
        } else {
            Intent intent = new Intent();

            intent.setType("*/*");

            intent.setAction(Intent.ACTION_GET_CONTENT);

            // Always show the chooser (if there are multiple options available)
            startActivityForResult(Intent.createChooser(intent, "Select Media"), INTENT_REQUEST_CHOOSE_MEDIA);

            // Set this as false to maintain connection
            // even when an external Activity is started.
            SendBird.setAutoBackgroundDetection(false);
        }
    }

    private void editMessage(final BaseMessage message, String editedMessage) {
        if (mChannel == null) {
            return;
        }

        mChannel.updateUserMessage(message.getMessageId(), editedMessage, null, null, new BaseChannel.UpdateUserMessageHandler() {
            @Override
            public void onUpdated(UserMessage userMessage, SendBirdException e) {
                if (e != null) {
                    // Error!
                    Toast.makeText(ChatActivity.this, "Error " + e.getCode() + ": " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                mChatAdapter.loadLatestMessages(CHANNEL_LIST_LIMIT, new BaseChannel.GetMessagesHandler() {
                    @Override
                    public void onResult(List<BaseMessage> list, SendBirdException e) {
                        mChatAdapter.markAllMessagesAsRead();
                    }
                });
            }
        });
    }

    private void setUpChatListAdapter() {
        mChatAdapter.setItemClickListener(new GroupChatAdapter.OnItemClickListener() {
            @Override
            public void onUserMessageItemClick(UserMessage message) {
                // Restore failed message and remove the failed message from list.
                if (mChatAdapter.isFailedMessage(message)) {
                    retryFailedMessage(message);
                    return;
                }

                // Message is sending. Do nothing on click event.
                if (mChatAdapter.isTempMessage(message)) {
                    return;
                }


                if (message.getCustomType().equals(GroupChatAdapter.URL_PREVIEW_CUSTOM_TYPE)) {
                    try {
                        UrlPreviewInfo info = new UrlPreviewInfo(message.getData());
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(info.getUrl()));
                        startActivity(browserIntent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFileMessageItemClick(FileMessage message) {
                // Load media chooser and remove the failed message from list.
                if (mChatAdapter.isFailedMessage(message)) {
                    retryFailedMessage(message);
                    return;
                }

                // Message is sending. Do nothing on click event.
                if (mChatAdapter.isTempMessage(message)) {
                    return;
                }


                onFileMessageClicked(message);
            }
        });

        mChatAdapter.setItemLongClickListener(new GroupChatAdapter.OnItemLongClickListener() {
            @Override
            public void onUserMessageItemLongClick(UserMessage message, int position) {
                if (message.getSender().getUserId().equals(loginResDataModal.getData().getUser().getId())) {
                    showMessageOptionsDialog(message, position);
                }
            }

            @Override
            public void onFileMessageItemLongClick(FileMessage message) {
            }

            @Override
            public void onAdminMessageItemLongClick(AdminMessage message) {
            }
        });
    }

    private void showMessageOptionsDialog(final BaseMessage message, final int position) {
        String[] options = new String[] { "Edit message", "Delete message" };

        AlertDialog.Builder builder = new AlertDialog.Builder(ChatActivity.this);
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    setState(STATE_EDIT, message, position);
                } else if (which == 1) {
                    deleteMessage(message);
                }
            }
        });
        builder.create().show();
    }

    private void deleteMessage(final BaseMessage message) {
        if (mChannel == null) {
            return;
        }

        mChannel.deleteMessage(message, new BaseChannel.DeleteMessageHandler() {
            @Override
            public void onResult(SendBirdException e) {
                if (e != null) {
                    // Error!
                    Toast.makeText(ChatActivity.this, "Error " + e.getCode() + ": " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                mChatAdapter.loadLatestMessages(CHANNEL_LIST_LIMIT, new BaseChannel.GetMessagesHandler() {
                    @Override
                    public void onResult(List<BaseMessage> list, SendBirdException e) {
                        mChatAdapter.markAllMessagesAsRead();
                    }
                });
            }
        });
    }

    private void setState(int state, BaseMessage editingMessage, final int position) {
        switch (state) {
            case STATE_NORMAL:
                mCurrentState = STATE_NORMAL;
                mEditingMessage = null;

                mUploadFileButton.setVisibility(View.VISIBLE);
                mMessageSendButton.setText("SEND");
                mMessageEditText.setText("");
                break;

            case STATE_EDIT:
                mCurrentState = STATE_EDIT;
                mEditingMessage = editingMessage;

                mUploadFileButton.setVisibility(View.GONE);
                mMessageSendButton.setText("SAVE");
                String messageString = ((UserMessage)editingMessage).getMessage();
                if (messageString == null) {
                    messageString = "";
                }
                mMessageEditText.setText(messageString);
                if (messageString.length() > 0) {
                    mMessageEditText.setSelection(0, messageString.length());
                }

                mMessageEditText.requestFocus();
                mMessageEditText.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIMM.showSoftInput(mMessageEditText, 0);

                        mRecyclerView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mRecyclerView.scrollToPosition(position);
                            }
                        }, 500);
                    }
                }, 100);
                break;
        }
    }

    private void retryFailedMessage(final BaseMessage message) {
        new AlertDialog.Builder(ChatActivity.this)
                .setMessage("Retry?")
                .setPositiveButton(R.string.resend_message, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == DialogInterface.BUTTON_POSITIVE) {
                            if (message instanceof UserMessage) {
                                String userInput = ((UserMessage) message).getMessage();
                                sendUserMessage(userInput);
                            } else if (message instanceof FileMessage) {
                                Uri uri = mChatAdapter.getTempFileMessageUri(message);
                                sendFileWithThumbnail(uri);
                            }
                            mChatAdapter.removeFailedMessage(message);
                        }
                    }
                })
                .setNegativeButton(R.string.delete_message, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == DialogInterface.BUTTON_NEGATIVE) {
                            mChatAdapter.removeFailedMessage(message);
                        }
                    }
                }).show();
    }

    private void sendUserMessage(String text) {
        if (mChannel == null) {
            return;
        }

        List<String> urls = WebUtils.extractUrls(text);
        if (urls.size() > 0) {
            sendUserMessageWithUrl(text, urls.get(0));
            return;
        }

        UserMessage tempUserMessage = mChannel.sendUserMessage(text, new BaseChannel.SendUserMessageHandler() {
            @Override
            public void onSent(UserMessage userMessage, SendBirdException e) {
                if (e != null) {
                    // Error!
                    Log.e("ChatActivity", e.toString());
                    Toast.makeText(ChatActivity.this, "Send failed with error " + e.getCode() + ": " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    mChatAdapter.markMessageFailed(userMessage);
                    return;
                }

                // Update a sent message to RecyclerView
                mChatAdapter.markMessageSent(userMessage);
            }
        });

        // Display a user message to RecyclerView
        mChatAdapter.addFirst(tempUserMessage);
    }

    private void sendUserMessageWithUrl(final String text, String url) {
        if (mChannel == null) {
            return;
        }

        new WebUtils.UrlPreviewAsyncTask() {
            @Override
            protected void onPostExecute(UrlPreviewInfo info) {
                if (mChannel == null) {
                    return;
                }

                UserMessage tempUserMessage = null;
                BaseChannel.SendUserMessageHandler handler = new BaseChannel.SendUserMessageHandler() {
                    @Override
                    public void onSent(UserMessage userMessage, SendBirdException e) {
                        if (e != null) {
                            // Error!
                            Log.e("ChatActivity", e.toString());
                            Toast.makeText(ChatActivity.this, "Send failed with error " + e.getCode() + ": " + e.getMessage(), Toast.LENGTH_SHORT).show();

                            mChatAdapter.markMessageFailed(userMessage);
                            return;
                        }

                        // Update a sent message to RecyclerView
                        mChatAdapter.markMessageSent(userMessage);
                    }
                };

                try {
                    // Sending a message with URL preview information and custom type.
                    String jsonString = info.toJsonString();
                    tempUserMessage = mChannel.sendUserMessage(text, jsonString, GroupChatAdapter.URL_PREVIEW_CUSTOM_TYPE, handler);
                } catch (Exception e) {
                    // Sending a message without URL preview information.
                    tempUserMessage = mChannel.sendUserMessage(text, handler);
                }


                // Display a user message to RecyclerView
                mChatAdapter.addFirst(tempUserMessage);
            }
        }.execute(url);
    }

    private void onFileMessageClicked(FileMessage message) {
        String type = message.getType().toLowerCase();
        if (type.startsWith("image")) {
            Intent i = new Intent(ChatActivity.this, PhotoViewerActivity.class);
            i.putExtra("url", message.getUrl());
            i.putExtra("type", message.getType());
            startActivity(i);
        } else if (type.startsWith("video")) {
            Intent intent = new Intent(ChatActivity.this, MediaPlayerActivity.class);
            intent.putExtra("url", message.getUrl());
            startActivity(intent);
        } else {
            showDownloadConfirmDialog(message);
        }
    }

    private void showDownloadConfirmDialog(final FileMessage message) {
        if (ContextCompat.checkSelfPermission(ChatActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // If storage permissions are not granted, request permissions at run-time,
            // as per < API 23 guidelines.
            requestStoragePermissions();
        } else {
            new AlertDialog.Builder(ChatActivity.this)
                    .setMessage("Download file?")
                    .setPositiveButton(R.string.download, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == DialogInterface.BUTTON_POSITIVE) {
                                FileUtils.downloadFile(ChatActivity.this, message.getUrl(), message.getName());
                            }
                        }
                    })
                    .setNegativeButton(R.string.cancel, null).show();
        }

    }

    private void sendFileWithThumbnail(Uri uri) {
        if (mChannel == null) {
            return;
        }

        // Specify two dimensions of thumbnails to generate
        List<FileMessage.ThumbnailSize> thumbnailSizes = new ArrayList<>();
        thumbnailSizes.add(new FileMessage.ThumbnailSize(240, 240));
        thumbnailSizes.add(new FileMessage.ThumbnailSize(320, 320));

        Hashtable<String, Object> info = FileUtils.getFileInfo(ChatActivity.this, uri);

        if (info == null || info.isEmpty()) {
            Toast.makeText(ChatActivity.this, "Extracting file information failed.", Toast.LENGTH_LONG).show();
            return;
        }

        final String name;
        if (info.containsKey("name")) {
            name = (String) info.get("name");
        } else {
            name = "Sendbird File";
        }
        final String path = (String) info.get("path");
        final File file = new File(path);
        final String mime = (String) info.get("mime");
        final int size = (Integer) info.get("size");

        if (path == null || path.equals("")) {
            Toast.makeText(ChatActivity.this, "File must be located in local storage.", Toast.LENGTH_LONG).show();
        } else {
            BaseChannel.SendFileMessageHandler fileMessageHandler = new BaseChannel.SendFileMessageHandler() {
                @Override
                public void onSent(FileMessage fileMessage, SendBirdException e) {
                    if (e != null) {
                        Toast.makeText(ChatActivity.this, "" + e.getCode() + ":" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        mChatAdapter.markMessageFailed(fileMessage);
                        return;
                    }

                    mChatAdapter.markMessageSent(fileMessage);
                }
            };

            // Send image with thumbnails in the specified dimensions
            FileMessage tempFileMessage = mChannel.sendFileMessage(file, name, mime, size, "", null, thumbnailSizes, fileMessageHandler);

            mChatAdapter.addTempFileMessageInfo(tempFileMessage, uri);
            mChatAdapter.addFirst(tempFileMessage);
        }
    }

    private void requestStoragePermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // For example if the user has previously denied the permission.
            Snackbar.make(mRootLayout, "Storage access permissions are required to upload/download files.", Snackbar.LENGTH_LONG).setAction("Okay", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ActivityCompat.requestPermissions(ChatActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_WRITE_EXTERNAL_STORAGE);
                }
            }).show();
        } else {
            // Permission has not been granted yet. Request it directly.
            ActivityCompat.requestPermissions(ChatActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSION_WRITE_EXTERNAL_STORAGE);
        }
    }

    private void refresh() {
        if (mChannel == null) {
            GroupChannel.getChannel(mChannelUrl, new GroupChannel.GroupChannelGetHandler() {
                @Override
                public void onResult(GroupChannel groupChannel, SendBirdException e) {
                    if (e != null) {
                        // Error!
                        e.printStackTrace();
                        return;
                    }

                    mChannel = groupChannel;
                    mChatAdapter.setChannel(mChannel);
                    mChatAdapter.loadLatestMessages(CHANNEL_LIST_LIMIT, new BaseChannel.GetMessagesHandler() {
                        @Override
                        public void onResult(List<BaseMessage> list, SendBirdException e) {
                            mChatAdapter.markAllMessagesAsRead();
                        }
                    });
                }
            });
        } else {
            mChannel.refresh(new GroupChannel.GroupChannelRefreshHandler() {
                @Override
                public void onResult(SendBirdException e) {
                    if (e != null) {
                        // Error!
                        e.printStackTrace();
                        return;
                    }

                    mChatAdapter.loadLatestMessages(CHANNEL_LIST_LIMIT, new BaseChannel.GetMessagesHandler() {
                        @Override
                        public void onResult(List<BaseMessage> list, SendBirdException e) {
                            mChatAdapter.markAllMessagesAsRead();
                        }
                    });
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        ConnectionManager.addConnectionManagementHandler(loginResDataModal.getData().getUser().getId(),CONNECTION_HANDLER_ID, new ConnectionManager.ConnectionManagementHandler() {
            @Override
            public void onConnected(boolean reconnect) {
                refresh();
            }
        });

        mChatAdapter.setContext(ChatActivity.this); // Glide bug fix (java.lang.IllegalArgumentException: You cannot start a load for a destroyed activity)

        // Gets channel from URL user requested
        SendBird.addChannelHandler(CHANNEL_HANDLER_ID, new SendBird.ChannelHandler() {
            @Override
            public void onMessageReceived(BaseChannel baseChannel, BaseMessage baseMessage) {
                if (baseChannel.getUrl().equals(mChannelUrl)) {
                    mChatAdapter.markAllMessagesAsRead();
                    // Add new message to view
                    mChatAdapter.addFirst(baseMessage);
                }
            }

            @Override
            public void onMessageDeleted(BaseChannel baseChannel, long msgId) {
                super.onMessageDeleted(baseChannel, msgId);
                if (baseChannel.getUrl().equals(mChannelUrl)) {
                    mChatAdapter.delete(msgId);
                }
            }

            @Override
            public void onMessageUpdated(BaseChannel channel, BaseMessage message) {
                super.onMessageUpdated(channel, message);
                if (channel.getUrl().equals(mChannelUrl)) {
                    mChatAdapter.update(message);
                }
            }

            @Override
            public void onReadReceiptUpdated(GroupChannel channel) {
                if (channel.getUrl().equals(mChannelUrl)) {
                    mChatAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTypingStatusUpdated(GroupChannel channel) {
                if (channel.getUrl().equals(mChannelUrl)) {
                    List<Member> typingUsers = channel.getTypingMembers();
                    displayTyping(typingUsers);
                }
            }

            @Override
            public void onDeliveryReceiptUpdated(GroupChannel channel) {
                if (channel.getUrl().equals(mChannelUrl)) {
                    mChatAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onPause() {
        setTypingStatus(false);

        ConnectionManager.removeConnectionManagementHandler(CONNECTION_HANDLER_ID);
        SendBird.removeChannelHandler(CHANNEL_HANDLER_ID);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        // Save messages to cache.
        mChatAdapter.save();
        super.onDestroy();
    }

    private void setTypingStatus(boolean typing) {
        if (mChannel == null) {
            return;
        }

        if (typing) {
            mIsTyping = true;
            mChannel.startTyping();
        } else {
            mIsTyping = false;
            mChannel.endTyping();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Set this as true to restore background connection management.
        SendBird.setAutoBackgroundDetection(true);

        if (requestCode == INTENT_REQUEST_CHOOSE_MEDIA && resultCode == Activity.RESULT_OK) {
            // If user has successfully chosen the image, show a dialog to confirm upload.
            if (data == null) {
                return;
            }

            sendFileWithThumbnail(data.getData());
        }
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

    private void displayTyping(List<Member> typingUsers) {

        if (typingUsers.size() > 0) {
            mCurrentEventLayout.setVisibility(View.VISIBLE);
            String string;

            if (typingUsers.size() == 1) {
                string = String.format(getString(R.string.user_typing), typingUsers.get(0).getNickname());
            } else if (typingUsers.size() == 2) {
                string = String.format(getString(R.string.two_users_typing), typingUsers.get(0).getNickname(), typingUsers.get(1).getNickname());
            } else {
                string = getString(R.string.users_typing);
            }
            mCurrentEventText.setText(string);
        } else {
            mCurrentEventLayout.setVisibility(View.GONE);
        }
    }
}
