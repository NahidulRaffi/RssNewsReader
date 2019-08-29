package com.androhungers.rssnewsreader.services;

import androidx.fragment.app.Fragment;

public interface NavigationManager {
    void push(Fragment fragment, String tag);
    void pop( String tag);
    boolean onBackPressed();
    boolean isTop(String tag);
}
