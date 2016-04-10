package duong.tieu.vdmproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubError;
import com.pubnub.api.PubnubException;

import java.util.ArrayList;

import duong.tieu.vdmproject.R;
import duong.tieu.vdmproject.adapter.AdapterMessages;
import duong.tieu.vdmproject.models.DGetUser;
import duong.tieu.vdmproject.models.DMessages;
import duong.tieu.vdmproject.models.Models;

/**
 * Created by duong on 07/04/2016.
 */
public class MessageActivity extends AppCompatActivity {

    private Button mBtSendMessages;
    private EditText mEdSend;
    private String mUserReceive;
    private String mSubChannel;
    private String mPubChannel;

    private ListView mLvMessages;
    private AdapterMessages mAdapterMessages;
    private ArrayList<DMessages> mListMessage = new ArrayList<>();

    private Pubnub mPubnub = new Pubnub(
            Models.PUBLIC_KEY,  // PUBLISH_KEY   (Optional, supply "" to disable)
            Models.SUBSCRIBE_KEY,  // SUBSCRIBE_KEY (Required)
            Models.SECRET_KEY,      // SECRET_KEY    (Optional, supply "" to disable)
            "",      // CIPHER_KEY    (Optional, supply "" to disable)
            false    // SSL_ON?
    );

    private Callback mCallback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess);

        initWidgets();
        getInitData();
        initObject();
    }

    private void initObject() {

        mSubChannel = Models.CHANNEL_ + LoginActivity.mUsername;
        mPubChannel = Models.CHANNEL_ + mUserReceive;

        mCallback = new Callback() {
            @Override
            public void connectCallback(String channel, Object message) {
                Log.i("connectCallbackRe", "SUBSCRIBE : CONNECT on channel:" + channel
                        + " : " + message.getClass() + " : "
                        + message.toString());
            }

            @Override
            public void disconnectCallback(String channel, Object message) {
                Log.i("disconnectCallback", "SUBSCRIBE : DISCONNECT on channel:" + channel
                        + " : " + message.getClass() + " : "
                        + message.toString());
            }

            public void reconnectCallback(String channel, Object message) {
                Log.i("disconnectCallback", "SUBSCRIBE : RECONNECT on channel:" + channel
                        + " : " + message.getClass() + " : "
                        + message.toString());
            }

            @Override
            public void successCallback(String channel, final Object message) {
                Log.i("successCallbackSend", "SUBSCRIBE : " + channel + " : "
                        + message.getClass() + " : " + message.toString());
                MessageActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setList(message.toString());
                    }
                });

            }

            @Override
            public void errorCallback(String channel, PubnubError error) {
                Log.i("errorCallback", "SUBSCRIBE : ERROR on channel " + channel
                        + " : " + error.toString());
            }
        };
        try {
            mPubnub.subscribe(mSubChannel, mCallback);
        } catch (PubnubException e) {
            e.printStackTrace();
        }

        DMessages dMessages =
                new DMessages("0", mUserReceive, LoginActivity.mUsername, "kjsldflsaf", "1233", "0");
        mListMessage.add(dMessages);
        dMessages =
                new DMessages("1", LoginActivity.mUsername, mUserReceive, "sdfjklasfjasl", "565", "1");

        mListMessage.add(dMessages);
        mAdapterMessages.notifyDataSetChanged();
    }

    private void initWidgets() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.tbMessages);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(mUserReceive);

        mLvMessages = (ListView) findViewById(R.id.lvListMessages);
        mAdapterMessages = new AdapterMessages(this, mListMessage);
        mLvMessages.setAdapter(mAdapterMessages);
        mBtSendMessages = (Button) findViewById(R.id.btSendMessage);
        mEdSend = (EditText) findViewById(R.id.edSendMessage);
        mBtSendMessages.setOnClickListener(new Events());
    }

    // get data from contacts fragment
    private void getInitData() {

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(Models.PACKAGE);
        DGetUser dGetUser = (DGetUser) bundle.getSerializable(Models.DATA);
        if (dGetUser != null) {
            mUserReceive = dGetUser.getUsername();
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    public void setList(String list) {
        DMessages dMessages =
                new DMessages("0", mUserReceive, LoginActivity.mUsername, list, "1233", "0");
        mListMessage.add(dMessages);
        mAdapterMessages.notifyDataSetChanged();
    }

    private class Events implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btSendMessage) {
                onClickSendMessages();
            }
        }

        private void onClickSendMessages() {
//            mPubnub.publish(mPubChannel, mEdSend.getText().toString(), new Callback() {
//                @Override
//                public void successCallback(String channel, Object message) {
//                    super.successCallback(channel, message);
//                    Log.i("Tag", message.toString());
//                }
//
//                @Override
//                public void errorCallback(String channel, PubnubError error) {
//                    super.errorCallback(channel, error);
//                    Log.i("Tag", error.getErrorString());
//                }
//            });

            mPubnub.history(mPubChannel, 0, 34234, 100, true, new Callback() {
                @Override
                public void successCallback(String channel, Object message) {
                    super.successCallback(channel, message);
                    setList(message.toString());

                }

                @Override
                public void errorCallback(String channel, PubnubError error) {
                    super.errorCallback(channel, error);
                }
            });

            DMessages mMessageSend =
                    new DMessages("0", LoginActivity.mUsername, mUserReceive, mEdSend.getText().toString(), "1233", "0");

            mListMessage.add(mMessageSend);
            mAdapterMessages.notifyDataSetChanged();
            mEdSend.setText("");
        }
    }
}