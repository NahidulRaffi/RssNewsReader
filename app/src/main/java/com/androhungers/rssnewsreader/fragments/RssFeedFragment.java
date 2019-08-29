package com.androhungers.rssnewsreader.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androhungers.rssnewsreader.R;
import com.androhungers.rssnewsreader.activities.RssFeedActivity;
import com.androhungers.rssnewsreader.adapters.RssPagerAdapter;
import com.androhungers.rssnewsreader.viewModel.RssFeedViewModel;
import com.gigamole.navigationtabstrip.NavigationTabStrip;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RssFeedFragment extends Fragment {

    @BindView(R.id.nav)
    NavigationTabStrip navigationTabStrip;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    public static String tag = RssFeedFragment.class.toString();
    private RssFeedActivity rssFeedActivity = new RssFeedActivity();
    ViewModel viewModel;
    RssPagerAdapter viewPagerAdater;


    public RssFeedFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rss_feed, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rssFeedActivity = (RssFeedActivity) getActivity();
        viewModel = ViewModelProviders.of(getActivity()).get(RssFeedViewModel.class);

        setUpUI();

        Log.i("::>>","Frag Activity Created");
    }

    private void setUpUI(){
        viewPagerAdater = new RssPagerAdapter(getChildFragmentManager());
        viewPager.removeAllViews();
        viewPager.setAdapter(viewPagerAdater);

        navigationTabStrip.setTitles("Home","My Rss");
        navigationTabStrip.setViewPager(viewPager);

//        navigationTabStrip.setTabIndex(0, true);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {

                    //getActivity().getSupportFragmentManager().popBackStack();
                    getActivity().finish();
                    return true;
                } else
                    return false;
            }
        });

    }
}
