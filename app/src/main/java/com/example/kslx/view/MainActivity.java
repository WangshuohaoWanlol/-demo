package com.example.kslx.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.kslx.R;
import com.example.kslx.view.fragment.MainoneFragment;
import com.example.kslx.view.fragment.MaintwoFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager mVp;
    private TabLayout mTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.mVp);
        mTab = (TabLayout) findViewById(R.id.mTab);

        final ArrayList<Fragment> fragments = new ArrayList<>();

        mTab.setupWithViewPager(mVp);
        fragments.add(new MainoneFragment());
        fragments.add(new MaintwoFragment());

        mVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            String src [] = {"首页","收藏"};
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {

                return src[position];
            }

        });
    }

}
