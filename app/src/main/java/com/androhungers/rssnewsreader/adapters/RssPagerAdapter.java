package com.androhungers.rssnewsreader.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.androhungers.rssnewsreader.fragments.MyRssFragment;
import com.androhungers.rssnewsreader.fragments.RssHomeFragment;

public class RssPagerAdapter extends FragmentPagerAdapter {

    public RssPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public RssPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 : return new RssHomeFragment();
            case 1 : return new MyRssFragment();
            default: return new RssHomeFragment();

        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title;

        switch (position) {
            case 0 : title = "One-Way";
            case 1 : title = "Round-Trip";
            default: title = null;
        }

        return title;
    }
}
