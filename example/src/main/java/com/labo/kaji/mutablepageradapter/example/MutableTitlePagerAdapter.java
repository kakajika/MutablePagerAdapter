package com.labo.kaji.mutablepageradapter.example;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.astuetz.PagerSlidingTabStrip;
import com.labo.kaji.mutablepageradapter.MutablePagerAdapter;
import com.labo.kaji.mutablepageradapter.MutableStatePagerAdapter;

/**
 * An example for using MutablePagerAdapter with PagerSlidingTabStrip.
 */
public class MutableTitlePagerAdapter extends MutableStatePagerAdapter {

    private PagerSlidingTabStrip mTabs;

    public MutableTitlePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setTabStrip(PagerSlidingTabStrip tabs) {
        mTabs = tabs;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (mTabs != null && getCount() > 0) {
            mTabs.notifyDataSetChanged();
        }
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = super.getItem(position);
        ((ExamplePageFragment) f).setPageIndex(position);
        return f;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return getPageFragment(position).getArguments().getString("title");
    }

}
