package com.labo.kaji.mutablepageradapter;

import android.support.v4.app.Fragment;

import java.util.List;

/**
 * Common interface of MutablePagerAdapter(s).
 */
public interface IMutablePageControl {

    /**
     * Get page fragment at specific index,
     *
     * @param index
     * @return Page fragment
     */
    public Fragment getPageFragment(int index);

    /**
     * Search position of specific page fragment.
     *
     * @param fragment
     * @return Position of fragment
     */
    public int indexOfPageFragment(Fragment fragment);

    /**
     * Add page fragment to last.
     *
     * @param fragment Page fragment to add
     */
    public void addPageFragment(Fragment fragment);

    /**
     * Add all elements from page fragment list to last.
     *
     * @param fragments Page fragment list to add
     */
    public void addPageFragments(List<Fragment> fragments);

    /**
     * Insert page fragment into specific index.
     *
     * @param fragment Page fragment to insert
     * @param index    Insert index
     */
    public void insertPageFragment(Fragment fragment, int index);

    /**
     * Replace page fragment at specific index to new.
     *
     * @param fragment New page fragment
     * @param index    Replace index
     */
    public void replacePageFragment(Fragment fragment, int index);

    /**
     * Remove last page fragment,
     */
    public void removeLastPageFragment();

    /**
     * Remove page fragment at specific index.
     *
     * @param index Remove index
     */    
    public void removePageFragment(int index);

    /**
     * Remove specific page fragment.
     *
     * @param fragment Page fragment to remove
     */
    public void removePageFragment(Fragment fragment);

    /**
     * Remove all page fragment.
     */
    public void clearAllPageFragments();
}
