package com.example.apple.demomusic.managers;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.apple.demomusic.R;

/**
 * Created by apple on 7/19/17.
 */

public class ScreenManager {

    public static void openFragment(FragmentManager fragmentManager, Fragment fragment, int layoutID, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.layout_container, fragment);
        if(addToBackStack){
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }
}
