package com.androhungers.rssnewsreader.activities;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.androhungers.rssnewsreader.R;
import com.androhungers.rssnewsreader.common.FragmentNavigationManager;
import com.androhungers.rssnewsreader.fragments.RssFeedFragment;
import com.androhungers.rssnewsreader.services.NavigationManager;
import com.androhungers.rssnewsreader.services.NavigationProvider;
import com.androhungers.rssnewsreader.viewModel.RssFeedViewModel;


public class RssFeedActivity extends AppCompatActivity implements NavigationProvider {


    private NavigationManager navigationManager = null;

    ViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss_feed);

        viewModel = ViewModelProviders.of(this).get(RssFeedViewModel.class);
        provideManager().push(new RssFeedFragment(), RssFeedFragment.tag);

    }


    @Override
    public NavigationManager provideManager() {
        return getNavigationManager(this, R.id.container_view);
    }

    private NavigationManager getNavigationManager(AppCompatActivity activity, @IdRes int containerId) {
        if (navigationManager == null) {
            navigationManager = new FragmentNavigationManager(activity, containerId);
        }

        return navigationManager;
    }

    @Override
    public void onBackPressed() {
        if (provideManager().onBackPressed() == false) {
            super.onBackPressed();
        }
    }
}
