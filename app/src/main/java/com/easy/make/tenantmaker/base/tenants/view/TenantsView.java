package com.easy.make.tenantmaker.base.tenants.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.base.component.DividerItemDecoration;
import com.easy.make.tenantmaker.core.tenant.data.model.Tenants;
import com.easy.make.tenantmaker.core.tenant.displayer.TenantsDisplayer;
import com.novoda.notils.caster.Views;

/**
 * Created by ravi on 04/09/16.
 */
public class TenantsView extends LinearLayout implements TenantsDisplayer {


    private final TenantAdapter tenantAdapter;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private TenantInteractionListener tenantInteractionListener;
    private FloatingActionButton addTenantFabButton;
    private ActivityCompat activityCompat;

    public TenantsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        tenantAdapter = new TenantAdapter(LayoutInflater.from(context));
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View.inflate(getContext(), R.layout.merge_tenant_view, this);
        setToolbar();
        setRecyclerView();
        setFabButton();
    }

    private void setFabButton() {
        addTenantFabButton = Views.findById(this, R.id.new_tenant_fab);
    }

    private void setRecyclerView() {
        recyclerView = Views.findById(this, R.id.tenants_recycler_view);
        Drawable dividerDrawable = ContextCompat.getDrawable(getContext(), R.drawable.seperator_72);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(dividerDrawable);
        recyclerView.addItemDecoration(dividerItemDecoration);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(tenantAdapter);
    }

    private void setToolbar() {
        toolbar = Views.findById(this, R.id.tool_bar);
//        toolbar.inflateMenu(R.menu.chat_menu);
//        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setTitle(R.string.str_tenants);

    }


    @Override
    public void toogleToolbarVisbility(boolean toolbarEnabled) {
        toolbar.setVisibility(toolbarEnabled ? VISIBLE : GONE);
    }

    @Override
    public void toogleFabBtnVisibility(boolean fabEnabled) {
        addTenantFabButton.setVisibility(fabEnabled ? VISIBLE : GONE);
    }

    @Override
    public void display(Tenants tenants) {
        tenantAdapter.setData(tenants);
    }

    @Override
    public void attach(TenantInteractionListener tenantInteractionListener) {
        this.tenantInteractionListener = tenantInteractionListener;
        tenantAdapter.attach(tenantInteractionListener);
        addTenantFabButton.setOnClickListener(fabClickListener);
    }

    @Override
    public void detach(TenantInteractionListener tenantInteractionListener) {
        tenantAdapter.detach(tenantInteractionListener);
        this.tenantInteractionListener = null;
        addTenantFabButton.setOnClickListener(null);
    }

    private final OnClickListener fabClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            tenantInteractionListener.onCreateTenant();
        }
    };

    public ActivityCompat getActivityCompat() {
        return activityCompat;
    }

    public void setActivityCompat(ActivityCompat activityCompat) {
        this.activityCompat = activityCompat;
    }
}
