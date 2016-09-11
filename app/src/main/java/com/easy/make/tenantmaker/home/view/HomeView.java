package com.easy.make.tenantmaker.home.view;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import com.easy.make.core.home.displayer.HomeDisplayer;
import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.component.HomeTabLayout;
import com.easy.make.tenantmaker.component.ViewPagerAdapter;
import com.lapism.searchview.SearchView;
import com.novoda.notils.caster.Views;

import java.util.List;

/**
 * Created by ravi on 09/09/16.
 */
public class HomeView extends CoordinatorLayout implements HomeDisplayer {

    private HomeTabLayout tabLayout;
    private ViewPager viewPager;
    private AppCompatActivity appCompatActivity;
    private ViewPagerAdapter viewPagerAdapter;
    private HomeInteractionListener homeInteractionListener;
    private FloatingActionButton fabButton;
    private SearchView searchView;

    public HomeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View.inflate(getContext(), R.layout.merge_home_view, this);
        initControls();
    }

    private void initControls() {
        tabLayout = Views.findById(this, R.id.tab_layout);
        viewPager = Views.findById(this, R.id.vp_home);
        fabButton = Views.findById(this, R.id.fab_button);
        searchView = Views.findById(this, R.id.search_view);
    }

    @Override
    public void setUpViewPager() {
        addTabs();
        viewPager.setAdapter(getViewPagerAdapter());
        viewPager.setOffscreenPageLimit(2);

    }

    private void addTabs() {
        List<String> titles = getViewPagerAdapter().getFragmentTitles();
        for (String title : titles) {
            tabLayout.addTab(title, title);
        }
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void attach(HomeInteractionListener homeInteractionListener) {
        this.homeInteractionListener = homeInteractionListener;
        viewPager.addOnPageChangeListener(onPageChangeListener);
        fabButton.setOnClickListener(onClickListener);
        searchView.setOnQueryTextListener(onQueryTextListener);

    }

    @Override
    public void detach(HomeInteractionListener homeInteractionListener) {
        viewPager.addOnPageChangeListener(null);
        fabButton.setOnClickListener(null);
        this.homeInteractionListener = null;
        searchView.setOnQueryTextListener(null);

    }


    final OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.fab_button:
                    homeInteractionListener.onFabBtnClicked(tabLayout.getSelectedTabPosition());
                    break;
            }
        }
    };


    final ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            fabButton.setImageResource(position == 0 ? R.drawable.ic_person_add_white : R.drawable.ic_add_white);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    final SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }

        @Override
        public boolean onQueryTextSubmit(String query) {
            homeInteractionListener.onQueryTextChange(query);
            return false;
        }
    };


    public AppCompatActivity getAppCompatActivity() {
        return appCompatActivity;
    }

    public void setAppCompatActivity(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    public ViewPagerAdapter getViewPagerAdapter() {
        return viewPagerAdapter;
    }

    public void setViewPagerAdapter(ViewPagerAdapter viewPagerAdapter) {
        this.viewPagerAdapter = viewPagerAdapter;
    }
}
