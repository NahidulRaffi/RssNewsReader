package com.androhungers.rssnewsreader.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androhungers.rssnewsreader.R;
import com.androhungers.rssnewsreader.activities.RssFeedActivity;
import com.androhungers.rssnewsreader.viewModel.RssFeedViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyRssFragment extends Fragment {
    public static String tag = MyRssFragment.class.toString();
    private RssFeedActivity rssFeedActivity = new RssFeedActivity();
    ViewModel viewModel;

    @BindView(R.id.img_add)
    ImageView imgAdd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_rss, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rssFeedActivity = (RssFeedActivity) getActivity();
        viewModel = ViewModelProviders.of(getActivity()).get(RssFeedViewModel.class);

        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RssAddBottomSheet bottomSheetLayout = RssAddBottomSheet.getInstance();
                bottomSheetLayout.show(getFragmentManager(), "OK");
            }
        });

    }
}
