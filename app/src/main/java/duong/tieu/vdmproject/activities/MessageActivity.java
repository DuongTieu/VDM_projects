package duong.tieu.vdmproject.activities;

import android.accounts.Account;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import duong.tieu.vdmproject.adapter.ViewPagerAdapter;
import duong.tieu.vdmproject.fragment.ContactsFragment;
import duong.tieu.vdmproject.fragment.MessFragment;
import duong.tieu.vdmproject.models.Models;

/**
 * Created by duong on 07/04/2016.
 */
public class MessageActivity extends AppCompatActivity {


    private Button mBtSendMessages;
    private EditText mEdSendMessages;
    private String mEmailReceive;
    private String emailSend;
    private String emailReceive;
    private Account mAccount;
    private ArrayList<String> mListString = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;

    private String mUser;
    private String mSubChannel;
    private String mPubChannel;

    private static final String PUBLIC_KEY = "pub-c-470b0c62-1d29-4905-ad3d-10e41ecae909";
    private static final String SUBSCRIBE_KEY = "sub-c-047ca2dc-fbc7-11e5-861b-02ee2ddab7fe";
    private static final String SECRET_KEY = "sec-c-NmIyOTA3NTMtYTY1Yi00Nzc2LWI1MmItOGQ2MjA0OGNkZjEy";


    private Pubnub mPubnub = new Pubnub(
            PUBLIC_KEY,  // PUBLISH_KEY   (Optional, supply "" to disable)
            SUBSCRIBE_KEY,  // SUBSCRIBE_KEY (Required)
            SECRET_KEY,      // SECRET_KEY    (Optional, supply "" to disable)
            "",      // CIPHER_KEY    (Optional, supply "" to disable)
            false    // SSL_ON?
    );

    private Callback mCallback;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess);

//        viewPager = (ViewPager) findViewById(R.id.viewpager);
//        setupViewPager(viewPager);
//
//        tabLayout = (TabLayout) findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(viewPager);

        mSubChannel = Models.CHANNEL_ + LoginActivity.mUsername;
        mPubChannel = Models.CHANNEL_ + mUser;
        Log.i("Tag", mSubChannel + "");
        Log.i("Tag", mPubChannel + "");

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

        initWidgets();


    }

    private void initWidgets() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.tbMessages);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(mUser);

        ListView mLvListMessages = (ListView) findViewById(R.id.lvListMessages);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mListString);

        mLvListMessages.setAdapter(mAdapter);
        mBtSendMessages = (Button) findViewById(R.id.btSendMessage);
        mEdSendMessages = (EditText) findViewById(R.id.edSendMessage);
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MessFragment(), "Tin nhắn");
        adapter.addFragment(new ContactsFragment(), "Danh bạ");
        viewPager.setAdapter(adapter);
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
}