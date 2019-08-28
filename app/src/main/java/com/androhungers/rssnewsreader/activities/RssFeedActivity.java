package com.androhungers.rssnewsreader.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.androhungers.rssnewsreader.R;
import com.gigamole.navigationtabstrip.NavigationTabStrip;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RssFeedActivity extends AppCompatActivity {

    @BindView(R.id.nav)
    NavigationTabStrip navigationTabStrip;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss_feed);

        ButterKnife.bind(this);
        setUpUI();
    }

    private void setUpUI(){
        navigationTabStrip.setTitles("Home","My RSS");
        navigationTabStrip.setTabIndex(0);
    }
}
