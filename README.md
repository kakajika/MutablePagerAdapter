# MutablePagerAdapter
An Android `FragmentPagerAdapter` extension supporting dynamic change of page elements for `ViewPager`.<br>
Androidの`ViewPager`で動的にページを追加・削除できる拡張`FragmentPagerAdapter`.

## Classes

- `MutableStatePagerAdapter`
    - based on FragmentStatePagerAdapter

- `MutablePagerAdapter` (Experimental)
    - based on FragmentPagerAdapter
    - currently implementing
    

## Usage

Basically, same as FragmentPagerAdapter.

```Android

ViewPager pager = (ViewPager)findViewById(R.id.pager);
MutableStatePagerAdapter adapter = new MutableStatePagerAdapter(getSupportFragmentManager(); 
pager.setAdapter(adapter);

// Add page to last
pager.addPageFragment(PageFragment.newInstance());

```

### Use with [PagerSlidingTabStrip](https://github.com/astuetz/PagerSlidingTabStrip)

See [MutableTitlePagerAdapter](blob/master/example/src/main/java/com/labo/kaji/mutablepageradapter/example/MutableTitlePagerAdapter.java) in example.

## Supported Function

- `addPageFragment(Fragment fragment)`
- `addPageFragments(List<Fragment> fragments)`
- `insertPageFragment(Fragment fragment, int index)`
- `replacePageFragment(Fragment fragment, int index)`
- `removePageFragment(int index)`
- `removePageFragment(Fragment fragment)`
- `clearAllPageFragment()`

![ScreenShot](https://raw.github.com/wiki/kakajika/mutablepageradapter/screenshot.png)
