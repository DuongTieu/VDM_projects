package duong.tieu.vdmproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubException;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.util.ArrayList;

import duong.tieu.vdmproject.R;
import duong.tieu.vdmproject.adapter.ViewPagerAdapter;
import duong.tieu.vdmproject.fragment.InterestFragment;
import duong.tieu.vdmproject.fragment.MyProjectFragment;
import duong.tieu.vdmproject.fragment.OppFragment;
import duong.tieu.vdmproject.models.DGetProject;
import duong.tieu.vdmproject.models.DMessages;
import duong.tieu.vdmproject.models.Models;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private BottomBar mBottomBar;

    private ArrayList<DGetProject> mListProjects = new ArrayList<>();
    private int verify;

    private Pubnub mPubnub = new Pubnub(
            Models.PUBLIC_KEY,  // PUBLISH_KEY   (Optional, supply "" to disable)
            Models.SUBSCRIBE_KEY,  // SUBSCRIBE_KEY (Required)
            Models.SECRET_KEY,      // SECRET_KEY    (Optional, supply "" to disable)
            "",      // CIPHER_KEY    (Optional, supply "" to disable)
            false    // SSL_ON?
    );
    private Callback mCallback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // bottom bar
        initData();
        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.setItemsFromMenu(R.menu.menu, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                getMessage(menuItemId, false);
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                getMessage(menuItemId, false);
            }
        });


        // set màu cho layout bottom
        // set đưuọc nhiêu màu khac nhau cho mỗi item
        mBottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.colorPrimary));
        mBottomBar.mapColorForTab(1, ContextCompat.getColor(this, R.color.colorPrimary));
        mBottomBar.mapColorForTab(2, ContextCompat.getColor(this, R.color.colorPrimary));
        mBottomBar.mapColorForTab(3, ContextCompat.getColor(this, R.color.colorPrimary));
        mBottomBar.mapColorForTab(4, ContextCompat.getColor(this, R.color.colorPrimary));

        // Thông báo tin nhắn .
        mBottomBar.makeBadgeForTabAt(1, 0xFFFF0000, 10).setAutoShowAfterUnSelection(true);
        mBottomBar.makeBadgeForTabAt(3, 0xFFFF0000, 13).setAutoShowAfterUnSelection(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OppFragment(), "Cơ hội mới");
        adapter.addFragment(new MyProjectFragment(), "Đang quan tâm");
        adapter.addFragment(new InterestFragment(), "Dự án của tôi");
        viewPager.setAdapter(adapter);
    }


    private void initDataNofication() {
        mCallback = new Callback() {

            @Override
            public void successCallback(String channel, final Object message) {
                Log.i("successCallbackSend", "SUBSCRIBE : " + channel + " : "
                        + message.getClass() + " : " + message.toString());
                Log.i("Tag", message.toString());
                String string = createMessages(message.toString());
                setBroadCastNotification(string);

            }
        };
        try {
            mPubnub.subscribe(Models.CHANNEL_NOTIFICATION, mCallback);
        } catch (PubnubException e) {
            e.printStackTrace();
        }

    }

    private void setBroadCastNotification(String string) {
        Intent intent = new Intent();
        intent.setAction("com.notification.chat");
        Bundle bundle = new Bundle();
        bundle.putString(Models.DATA, string);
        intent.putExtra(Models.PACKAGE, bundle);
        sendBroadcast(intent);
    }

    private String createMessages(String messagess) {

        DMessages messages = new DMessages();
        messages.setId("1");
        messages.setFrom("admin");
        messages.setTo("nhahv0902");
        messages.setCdate("12/22/1995");
        messages.setContent(messagess);
        messages.setIsrun("1");

        String json = new Gson().toJson(messages);
        return json;
    }


    private void getMessage(int menuItemId, boolean isReselection) {

        switch (menuItemId) {
            case R.id.bb_menu_recents:
                //Todo
                break;
            case R.id.bb_menu_favorites:
                Intent goNews = new Intent(this, LayoutNewsActivity.class);
                startActivity(goNews);
                break;
            case R.id.bb_menu_nearby:
                //Todo
                //go to MainActivity
                Intent mess = new Intent(this, LayoutMessageActivity.class);
                startActivity(mess);

                Log.d("test main", "start activity");
                break;
            case R.id.bb_menu_friends:
                //Todo
                Intent testNotifi = new Intent(this, TestNotifiActivity.class);
                startActivity(testNotifi);

                break;
            case R.id.bb_menu_food:
                //Todo
                break;
        }

        if (isReselection) {
            //Todo
        }

    }

    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(Models.PACKAGE);
        if (bundle != null) {
            verify = bundle.getInt(Models.VERIFY);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        mBottomBar.onSaveInstanceState(outState);
    }

    public int getVerify() {
        return verify;
    }

    public void setVerify(int verify) {
        this.verify = verify;
    }
}

