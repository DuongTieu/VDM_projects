package duong.tieu.vdmproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubError;
import com.pubnub.api.PubnubException;

import java.util.ArrayList;

import duong.tieu.vdmproject.R;
import duong.tieu.vdmproject.models.DGetUser;
import duong.tieu.vdmproject.models.Models;

/**
 * Created by duong on 07/04/2016.
 */
public class MessageActivity extends AppCompatActivity {


    private Button mBtSendMessages;
    private EditText mEdSendMessages;
    private ArrayAdapter<String> mAdapter;
    private String mUserReceive;
    private String mSubChannel;
    private String mPubChannel;

    private ArrayList<String> mListString = new ArrayList<>();
    private ListView mLvMessages;


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
    }

    private void initWidgets() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.tbMessages);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(mUserReceive);

        mLvMessages = (ListView) findViewById(R.id.lvListMessages);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mListString);
        mLvMessages.setAdapter(mAdapter);
        mBtSendMessages = (Button) findViewById(R.id.btSendMessage);
        mEdSendMessages = (EditText) findViewById(R.id.edSendMessage);
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
        mListString.add(list);
        mAdapter.notifyDataSetChanged();
    }

    private class Events implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btSendMessage){
                onClickSendMessages();
            }
        }

        private void onClickSendMessages() {

        }
    }
}