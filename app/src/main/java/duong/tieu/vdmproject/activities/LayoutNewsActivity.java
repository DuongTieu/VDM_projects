package duong.tieu.vdmproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import duong.tieu.vdmproject.R;
import duong.tieu.vdmproject.adapter.ViewPagerAdapter;
import duong.tieu.vdmproject.fragment.HomeNewsFragment;
import duong.tieu.vdmproject.fragment.SpecializedNewsFragment;
import duong.tieu.vdmproject.fragment.VSINewsFragment;
import duong.tieu.vdmproject.fragment.VipNewsFragment;

/**
 * Created by duong on 06/04/2016.
 */
public class LayoutNewsActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_news);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeNewsFragment(), "Bảng tin");
        adapter.addFragment(new VSINewsFragment(), "Tin vsi");
        adapter.addFragment(new SpecializedNewsFragment(), "Tin chuyên ngành");
        adapter.addFragment(new VipNewsFragment(), "Tin vip");
        viewPager.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent main = new Intent(getBaseContext(), MainActivity.class);
        startActivity(main);
    }

}
