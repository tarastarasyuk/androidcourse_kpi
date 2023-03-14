package com.tarastarasiuk.androidcourse.util;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentUtil {
    public static void replaceFragmentFor(FragmentManager fragmentManager, Fragment fragment, int fragmentPlaceholder) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fragmentPlaceholder, fragment);
        fragmentTransaction.commit();
    }
}
