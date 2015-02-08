package com.labo.kaji.mutablepageradapter;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Extended FragmentStatePagerAdapter for supporting dynamic change of page elements.
 *
 * @author kakajika
 */
public class MutableStatePagerAdapter extends FragmentStatePagerAdapter {

    private final FragmentManager mFragmentManager;
    private final ArrayList<Page> mPageList = new ArrayList<Page>();

    public MutableStatePagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
    }

    /**
     * Get page fragment at specific index,
     *
     * @param index
     * @return Page fragment
     */
    public Fragment getPageFragment(int index) {
        if (index < 0 || index >= mPageList.size()) {
            return null;
        }
        return mPageList.get(index).fragment;
    }

    /**
     * Search position of specific page fragment.
     *
     * @param fragment
     * @return Position of fragment
     */
    public int indexOfPageFragment(Fragment fragment) {
        return mPageList.indexOf(fragment);
    }

    /**
     * Add page fragment to last.
     *
     * @param fragment Page fragment to add
     */
    public void addPageFragment(Fragment fragment) {
        if (fragment == null || mPageList.contains(fragment)) {
            return;
        }

        mPageList.add(new Page(fragment));
        notifyDataSetChanged();
    }

    /**
     * Insert page fragment into specific index.
     *
     * @param fragment Page fragment to insert
     * @param index    Insert index
     */
    public void insertPageFragment(Fragment fragment, int index) {
        if (fragment == null || mPageList.contains(fragment)) {
            return;
        }
        if (index < 0 || index >= mPageList.size()) {
            return;
        }

        mPageList.add(index, new Page(fragment));
        for (int i = index; i < mPageList.size(); ++i) {
            Page page = mPageList.get(i);
            if (page.fragment.isAdded()) {
                mPageList.get(i).position = -1;
            }
        }
        notifyDataSetChanged();
    }

    /**
     * Replace page fragment at specific index to new.
     *
     * @param fragment New page fragment
     * @param index    Replace index
     */
    public void replacePageFragment(Fragment fragment, int index) {
        if (fragment == null || index < 0 || index >= mPageList.size()) {
            return;
        }

        Fragment current = mPageList.get(index).fragment;
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.remove(current);
        transaction.commit();

        mPageList.set(index, new Page(fragment));
        notifyDataSetChanged();
    }

    /**
     * Remove last page fragment,
     */
    public void removeLastPageFragment() {
        if (mPageList.size() == 0) {
            return;
        }

        mPageList.remove(mPageList.size() - 1);
        notifyDataSetChanged();
    }

    /**
     * Remove page fragment at specific index.
     *
     * @param index Remove index
     */
    public void removePageFragment(int index) {
        if (index < 0 || index >= mPageList.size()) {
            return;
        }

        for (int i = index; i < mPageList.size(); ++i) {
            Page page = mPageList.get(i);
            if (page.fragment.isAdded()) {
                page.position = -1;
            }
        }
        mPageList.remove(index);
        notifyDataSetChanged();
    }

    /**
     * Remove specific page fragment.
     *
     * @param fragment Page fragment to remove
     */
    public void removePageFragment(Fragment fragment) {
        if (fragment == null || !mPageList.contains(fragment)) {
            return;
        }

        final int index = mPageList.indexOf(fragment);
        if (index >= 0) {
            for (int i = index; i < mPageList.size(); ++i) {
                Page page = mPageList.get(i);
                if (page.fragment.isAdded()) {
                    page.position = -1;
                }
            }
            mPageList.remove(fragment);
            notifyDataSetChanged();
        }
    }

    /**
     * Remove all page fragment.
     */
    public void clearAllPageFragments() {
        mPageList.clear();
        notifyDataSetChanged();
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object object = super.instantiateItem(container, position);
        return object;
    }

    @Override
    public Fragment getItem(int position) {
        if (position < 0 || position >= mPageList.size()) {
            return null;
        }
        Fragment fragment = mPageList.get(position).fragment;
        return fragment;
    }

    @Override
    public final int getCount() {
        return mPageList.size();
    }

    @Override
    public final int getItemPosition(Object object) {
        for (int i = 0; i < mPageList.size(); ++i) {
            Page page = mPageList.get(i);
            if (object.equals(page.fragment)) {
                if (page.position >= 0 && page.fragment.isAdded() && !page.fragment.isDetached()) {
                    if (page.position == i) {
                        return POSITION_UNCHANGED;
                    } else {
                        return i;
                    }
                } else {
                    page.position = i;
                    return POSITION_NONE;
                }
            }
        }
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public void startUpdate(ViewGroup container) {
        super.startUpdate(container);
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        super.finishUpdate(container);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        if (object != null && view == ((Fragment) object).getView()) {
            return true;
        }
        return super.isViewFromObject(view, object);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
    }

    @Override
    public Parcelable saveState() {
        return super.saveState();
    }

    private static class Page {
        public final Fragment fragment;
        public int position;

        public Page(Fragment fragment) {
            this.fragment = fragment;
            this.position = -1;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Fragment && o == fragment) {
                return true;
            }
            return super.equals(o);
        }
    }

}
