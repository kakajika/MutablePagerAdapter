# MutablePagerAdapter
An Android `FragmentPagerAdapter` extension supporting dynamic change of page elements for `ViewPager`.<br>
Androidの`ViewPager`で動的にページを追加・削除・変更できる拡張`FragmentPagerAdapter`.<br>
一个Android的ViewPager可动态添加、删除、替换操作Fragment的`FragmentPagerAdapter`

## Classes 相关类

- **MutableStatePagerAdapter**
    - based on `FragmentStatePagerAdapter`

## Usage 用法

```Android
ViewPager pager = (ViewPager)findViewById(R.id.pager);
MutableStatePagerAdapter adapter = new MutableStatePagerAdapter(getSupportFragmentManager());
pager.setAdapter(adapter);

// Add page fragment to last
pager.addPageFragment(PageFragment.newInstance());
```

### Use with [PagerSlidingTabStrip](https://github.com/astuetz/PagerSlidingTabStrip) 与 [PagerSlidingTabStrip](https://github.com/astuetz/PagerSlidingTabStrip) 一起使用

See [MutableTitlePagerAdapter](example/src/main/java/com/labo/kaji/mutablepageradapter/example/MutableTitlePagerAdapter.java) in example.

### Fork
Android SDK change to 28(Android Pie，9.0).
Android SDK版本提升至28(Android Pie，9.0)。


Margate to AndroidX.
依赖包切换为AndroidX。


Remove unused resources.
移除了一些无用的资源。


## Supported Function 支持的方法

- `addPageFragment(Fragment fragment)`
- `addPageFragments(List<Fragment> fragments)`
- `insertPageFragment(Fragment fragment, int index)`
- `replacePageFragment(Fragment fragment, int index)`
- `removePageFragment(int index)`
- `removePageFragment(Fragment fragment)`
- `clearAllPageFragment()`

![ScreenShot](https://raw.githubusercontent.com/Attect/MutablePagerAdapter/master/screenshot.png)
