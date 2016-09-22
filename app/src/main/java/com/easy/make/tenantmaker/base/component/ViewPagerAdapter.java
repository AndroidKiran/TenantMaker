package com.easy.make.tenantmaker.base.component;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ravi on 10/09/16.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> fragments = new ArrayList<>();
    private final List<String> fragmentTitles = new ArrayList<>();
    private final Context context;

    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    public void addFragment(Fragment fragment, String title) {
        fragments.add(fragment);
        fragmentTitles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitles.get(position);
    }

    public List<Fragment> getFragments() {
        return fragments;
    }

    public List<String> getFragmentTitles() {
        return fragmentTitles;
    }
}