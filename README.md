# MutablePagerAdapter
Android FragmentPagerAdapter extention for supporting dynamic change of page elements.<br>
AndroidのViewPagerで動的にページを追加・削除できるFragmentPagerAdapterの拡張クラス.

## Supported Function

- **addPageFragment(Fragment fragment)**
- **insertPageFragment(Fragment fragment, int index)**
- **replacePageFragment(Fragment fragment, int index)**
- **removePageFragment(int index)**
- **removePageFragment(Fragment fragment)**
- **clearAllPageFragment()**

![ScreenShot](https://raw.github.com/wiki/kakajika/mutablepageradapter/screenshot.png =256x)