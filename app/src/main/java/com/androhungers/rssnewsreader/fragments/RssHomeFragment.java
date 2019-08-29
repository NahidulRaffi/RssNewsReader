package com.androhungers.rssnewsreader.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androhungers.rssnewsreader.R;
import com.androhungers.rssnewsreader.activities.RssFeedActivity;

import butterknife.ButterKnife;


public class RssHomeFragment extends Fragment {

    public static String tag = RssHomeFragment.class.toString();
    private RssFeedActivity rssFeedActivity = new RssFeedActivity();
    ViewModel viewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rss_home, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
