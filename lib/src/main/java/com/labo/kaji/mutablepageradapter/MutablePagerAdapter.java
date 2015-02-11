package com.labo.kaji.mutablepageradapter;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Extended FragmentPagerAdapter for supporting dynamic change of page elements.
 *
 * @author kakajika
 * @deprecated This is experimental class.
 */
@Deprecated
public class MutablePagerAdapter extends FragmentPagerAdapter implements MutablePageControl {

    private final FragmentManager mFragmentManager;
    private final ArrayList<Page> mPageList = new ArrayList<Page>();

    public MutablePagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
    }

    @Override
    public Fragment getPageFragment(int index) {
        if (index < 0 || index >= mPageList.size()) {
            return null;
        }
        return mPageList.get(index).fragment;
    }

    @Override
    public int indexOfPageFragment(Fragment fragment) {
        return mPageList.indexOf(fragment);
    }

    @Override
    public void addPageFragment(Fragment fragment) {
        if (fragment == null || mPageList.contains(fragment)) {
            return;
        }

        mPageList.add(new Page(fragment));
        notifyDataSetChanged();
    }

    @Override
    public void addPageFragments(List<Fragment> fragments) {
        if (fragments == null || fragments.size() == 0) {
            return;
        }

        for (Fragment fragment : fragments) {
            if (!mPageList.contains(fragment)) {
                mPageList.add(new Page(fragment));
            }
        }
        notifyDataSetChanged();
    }

    @Override
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

    @Override
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

    @Override
    public void removeLastPageFragment() {
        if (mPageList.size() == 0) {
            return;
        }

        mPageList.remove(mPageList.size() - 1);
        notifyDataSetChanged();
    }

    @Override
    public void removePageFragment(int index) {
        if (index < 0 || index >= mPageList.size()) {
            return;
        }

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        for (int i = index; i < mPageList.size(); ++i) {
            Page page = mPageList.get(i);
            if (page.fragment.isAdded()) {
                page.position = -1;
                transaction.detach(page.fragment);
            }
        }
        transaction.commit();
        mPageList.remove(index);
        notifyDataSetChanged();
    }

    @Override
    public void removePageFragment(Fragment fragment) {
        if (fragment == null || !mPageList.contains(fragment)) {
            return;
        }

        final int index = mPageList.indexOf(fragment);
        if (index >= 0) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            for (int i = index; i < mPageList.size(); ++i) {
                Page page = mPageList.get(i);
                if (page.fragment.isAdded()) {
                    page.position = -1;
                    transaction.detach(page.fragment);
                }
            }
            transaction.commit();
            mPageList.remove(fragment);
            notifyDataSetChanged();
        }
    }

    @Override
    public void clearAllPageFragments() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        for (Page page : mPageList) {
            transaction.remove(page.fragment);
        }
        transaction.commit();
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
    public long getItemId(int position) {
        return mPageList.get(position).id;
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
        if (position >= getCount()) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.remove((Fragment) object);
            transaction.commit();
        }
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

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        super.restoreState(state, loader);
    }

    private static class Page {
        private static int sSerialNumber = 0;

        public final Fragment fragment;
        public int position;
        public int id;

        public Page(Fragment fragment) {
            this.fragment = fragment;
            this.position = -1;
            this.id = sSerialNumber++;
        }

        public static void resetSerialNumber() {
            sSerialNumber = 0;
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
