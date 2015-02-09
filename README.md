# MutablePagerAdapter
Android `FragmentPagerAdapter` extention for supporting dynamic change of page elements.<br>
Androidの`ViewPager`で動的にページを追加・削除できる`FragmentPagerAdapter`の拡張クラス.

## Classes

- `MutableStatePagerAdapter`
    - based on `FragmentStatePagerAdapter`
- `MutablePagerAdapter`(Experimental)
    - based on `FragmentPagerAdapter`

## Supported Function

- `addPageFragment(Fragment fragment)`
- `addPageFragments(List<Fragment> fragments)`
- `insertPageFragment(Fragment fragment, int index)`
- `replacePageFragment(Fragment fragment, int index)`
- `removePageFragment(int index)`
- `removePageFragment(Fragment fragment)`
- `clearAllPageFragment()`

![ScreenShot](https://raw.github.com/wiki/kakajika/mutablepageradapter/screenshot.png)

