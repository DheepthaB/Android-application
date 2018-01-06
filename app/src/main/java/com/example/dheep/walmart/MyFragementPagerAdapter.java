package com.example.dheep.walmart;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by dheep on 12/21/2017.
 */

public class MyFragementPagerAdapter extends FragmentPagerAdapter {

    public MyFragementPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                Fragment fragment1=new BeveragesFragment();
                return fragment1;
            case 1:
                Fragment fragment2=new ElectronicsFragment();
                return fragment2;
            case 2:
                Fragment fragment3=new KetchupFragment();
                return fragment3;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
