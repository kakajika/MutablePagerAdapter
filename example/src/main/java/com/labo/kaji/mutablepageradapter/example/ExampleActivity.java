package com.labo.kaji.mutablepageradapter.example;

import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;

public class ExampleActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mPager;
    private MutableTitlePagerAdapter mPagerAdapter;
    private PagerListener mPagerListener = new PagerListener();
    private PagerSlidingTabStrip mTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        // Pager
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new MutableTitlePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setOnPageChangeListener(mPagerListener);

        // First page
        ExamplePageFragment.resetSerialNumber();
        mPagerAdapter.addPageFragment(ExamplePageFragment.newInstance(null));

        // Sliding tabs example
        mTabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        mTabs.setViewPager(mPager);
        mTabs.setOnPageChangeListener(mPagerListener);
        mPagerAdapter.setTabStrip(mTabs);
        resetTabIndicatorColor();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sample, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            if (mPager.getCurrentItem() > 0 && mPagerAdapter.getCount() > 0) {
                mPager.setCurrentItem(mPager.getCurrentItem() - 1, true);
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    private class PagerListener extends ViewPager.SimpleOnPageChangeListener {
        private boolean mPageChanging = false;
        private PageAnimationListener mPageAnimationListener = null;

        public void setPageAnimationListener(PageAnimationListener listener) {
            mPageAnimationListener = listener;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            super.onPageScrollStateChanged(state);
            if (mPageChanging && state == ViewPager.SCROLL_STATE_SETTLING) {
                int position = mPager.getCurrentItem();

                if (position >= mPagerAdapter.getCount() - 1) {
                    return;
                }

                if (mPageAnimationListener != null) {
                    mPageAnimationListener.onPageAnimationEnd();
                }

                mPageChanging = false;
            }
        }

        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
            mPageChanging = true;
            resetTabIndicatorColor();
        }
    }

    private interface PageAnimationListener {
        public void onPageAnimationEnd();
    }

    @Override
    public void onClick(View v) {
        final int pageIndex = mPager.getCurrentItem();
        switch (v.getId()) {
            case R.id.button1:
                mPagerAdapter.addPageFragment(ExamplePageFragment.newInstance(null));
                mPager.setCurrentItem(mPagerAdapter.getCount() - 1);
                break;
            case R.id.button2:
                mPagerAdapter.insertPageFragment(ExamplePageFragment.newInstance(null), pageIndex);
                break;
            case R.id.button3:
                mPagerAdapter.replacePageFragment(ExamplePageFragment.newInstance(null), pageIndex);
                break;
            case R.id.button4:
                if (pageIndex == mPagerAdapter.getCount() - 1) {
                    mPagerListener.setPageAnimationListener(new PageAnimationListener() {
                        @Override
                        public void onPageAnimationEnd() {
                            mPagerListener.setPageAnimationListener(null);
                            mPagerAdapter.removePageFragment(pageIndex);
                        }
                    });
                    mPager.setCurrentItem(pageIndex - 1, true);
                } else {
                    mPagerAdapter.removePageFragment(pageIndex);
                }
                break;
            case R.id.button5:
                if (pageIndex == mPagerAdapter.getCount() - 1) {
                    mPagerListener.setPageAnimationListener(new PageAnimationListener() {
                        @Override
                        public void onPageAnimationEnd() {
                            mPagerListener.setPageAnimationListener(null);
                            mPagerAdapter.removeLastPageFragment();
                        }
                    });
                    mPager.setCurrentItem(pageIndex - 1, true);
                } else {
                    mPagerAdapter.removeLastPageFragment();
                }
                break;
            case R.id.button6:
                mPagerAdapter.clearAllPageFragments();
                mPagerAdapter.addPageFragment(ExamplePageFragment.newInstance(null));
                break;
        }
        resetTabIndicatorColor();
    }

    private void resetTabIndicatorColor() {
        ExamplePageFragment fragment = (ExamplePageFragment) mPagerAdapter.getPageFragment(mPager.getCurrentItem());
        if (fragment != null) {
            mTabs.setIndicatorColor(fragment.getArguments().getInt("color"));
        }
    }

}
