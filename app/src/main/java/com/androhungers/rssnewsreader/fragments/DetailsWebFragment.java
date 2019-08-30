package com.androhungers.rssnewsreader.fragments;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.androhungers.rssnewsreader.R;
import com.androhungers.rssnewsreader.activities.RssFeedActivity;
import com.androhungers.rssnewsreader.viewModel.RssFeedViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailsWebFragment extends Fragment {

    public static String tag = DetailsWebFragment.class.toString();
    private RssFeedActivity rssFeedActivity = new RssFeedActivity();
    RssFeedViewModel viewModel;

    @BindView(R.id.img_back)
    ImageView imgBack;

    @BindView(R.id.web)
    WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rss_details, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelProviders.of(getActivity()).get(RssFeedViewModel.class);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

                    getActivity().getSupportFragmentManager().popBackStack();
                    return true;
                } else
                    return false;
            }
        });

    }
}
