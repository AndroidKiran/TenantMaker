package com.easy.make.tenantmaker.base.component;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.base.component.text.TextView;

/**
 * Created by ravi on 10/09/16.
 */
public class HomeTabLayout extends TabLayout {

    public HomeTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeTabLayout(Context context) {
        super(context);
    }

    public void addTab(String title, String tag) {
        Tab tab = this.newTab();
        View tabView = LayoutInflater.from(getContext()).inflate(R.layout.item_tab_layout, null);
        TextView titleView = (TextView) tabView.findViewById(R.id.tv_tab_title);
        titleView.setText(title);
        tab.setCustomView(tabView);
        tab.setTag(tag);
        this.addTab(tab);
    }
}

