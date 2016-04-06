package duong.tieu.vdmproject.Message;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import duong.tieu.vdmproject.Core.ViewPagerAdapter;
import duong.tieu.vdmproject.R;

/**
 * Created by duong on 05/04/2016.
 */
public class Message_Activity extends AppCompatActivity {

    private static final String TAG = "go to";
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mess_activity);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        Log.d(TAG, "have just gone MessActivity");
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Mess_Fragment(), "Tin nhắn");
        adapter.addFragment(new Contacts_Fragment(), "Danh bạ");
        viewPager.setAdapter(adapter);
    }

}
