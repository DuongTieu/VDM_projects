package duong.tieu.vdmproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import duong.tieu.vdmproject.R;
import duong.tieu.vdmproject.adapter.ViewPagerAdapter;
import duong.tieu.vdmproject.fragment.Interest_Fragment;
import duong.tieu.vdmproject.fragment.My_Project_Fragment;
import duong.tieu.vdmproject.fragment.Opp_Fragment;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private BottomBar mBottomBar;
    private ViewFlipper vf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // bottom bar
        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.setItemsFromMenu(R.menu.menu, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                Toast.makeText(MainActivity.this, getMessage(menuItemId, false), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                Toast.makeText(getApplicationContext(), getMessage(menuItemId, true), Toast.LENGTH_SHORT).show();
            }
        });

        // set màu cho layout bottom
        // set đưuọc nhiêu màu khac nhau cho mỗi item
        mBottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.colorPrimary));
        mBottomBar.mapColorForTab(1, ContextCompat.getColor(this, R.color.colorPrimary));
        mBottomBar.mapColorForTab(2,  ContextCompat.getColor(this, R.color.colorPrimary));
        mBottomBar.mapColorForTab(3,  ContextCompat.getColor(this, R.color.colorPrimary));
        mBottomBar.mapColorForTab(4,  ContextCompat.getColor(this, R.color.colorPrimary));

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
        adapter.addFragment(new Opp_Fragment(), "Cơ hội mới");
        adapter.addFragment(new My_Project_Fragment(), "Đang quan tâm");
        adapter.addFragment(new Interest_Fragment(), "Dự án của tôi");
        viewPager.setAdapter(adapter);
    }



    private String getMessage(int menuItemId, boolean isReselection) {
        String message = "Nội dung của";

        switch (menuItemId) {
            case R.id.bb_menu_recents:
                message += "Hợp tác";

                break;
            case R.id.bb_menu_favorites:
                message += "Bản tin";
                break;
            case R.id.bb_menu_nearby:
                message += "Tin nhắn";
                //go to MainActivity
                Intent mess = new Intent(this, Message_Activity.class);
                startActivity(mess);

                Log.d("test main", "start activity");
                break;
            case R.id.bb_menu_friends:
                message += "add";
                break;
            case R.id.bb_menu_food:
                message += "more";
                break;
        }

        if (isReselection) {
            message += "đã click 2 lần vào đây";
        }

        return message;
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Necessary to restore the BottomBar's state, otherwise we would
        // lose the current tab on orientation change.
        mBottomBar.onSaveInstanceState(outState);
    }

}

