package com.example.apple.demomusic.managers;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;

import com.example.apple.demomusic.R;

/**
 * Created by apple on 7/19/17.
 */

public class ScreenManager {
    private static final String TAG = ScreenManager.class.toString();

    public static void openFragment(FragmentManager fragmentManager, Fragment fragment,int layoutID, boolean addToBackStack, String tag) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(layoutID, fragment, tag);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }
}
