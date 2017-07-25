package com.example.apple.demomusic.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.apple.demomusic.fragments.FavoriteFragment;
import com.example.apple.demomusic.fragments.MusicFragment;
import com.example.apple.demomusic.fragments.DownloadFragment;

/**
 * Created by apple on 7/16/17.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    MusicFragment musicFragment;
    FavoriteFragment favoriteFragment;
    DownloadFragment downloadFragment;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        musicFragment = new MusicFragment();
        favoriteFragment = new FavoriteFragment();
        downloadFragment = new DownloadFragment();
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = musicFragment;
                break;
            case 1:
                fragment = favoriteFragment;
                break;
            case 2:
                fragment = downloadFragment;
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
