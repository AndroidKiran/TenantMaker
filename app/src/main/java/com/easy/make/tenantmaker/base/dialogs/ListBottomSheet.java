package com.easy.make.tenantmaker.base.dialogs;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.base.modules.BottomSheetItem;
import com.easy.make.tenantmaker.base.utils.UtilBundles;
import com.easy.make.tenantmaker.base.utils.Utils;

import java.util.ArrayList;


/**
 * Created by ravi on 11/08/16.
 */
public class ListBottomSheet extends BottomSheetDialogFragment implements Utils.ItemListener {

    private Bundle mArgs;
    private ArrayList<BottomSheetItem> mItemList;
    private Utils.ItemListener mListener;

    public static void show(FragmentManager fragmentManager, Bundle bundle) {
        ListBottomSheet listBottomSheet = new ListBottomSheet();
        listBottomSheet.setArguments(bundle);
        listBottomSheet.show(fragmentManager, listBottomSheet.getTag());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (null == savedInstanceState) {
            mArgs = getArguments();
        } else {
            mArgs = savedInstanceState;
        }

        initArguments();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_bottom_sheet, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initControls(view);
    }

    private void initArguments() {
        if (null != mArgs) {
            mItemList = mArgs.getParcelableArrayList(UtilBundles.ITEM_LIST);
        }
    }

    private void initControls(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.bottom_sheet_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        BottomSheetDialogAdapter bottomSheetDialogAdapter = new BottomSheetDialogAdapter(getActivity());
        bottomSheetDialogAdapter.setItems(mItemList);
        bottomSheetDialogAdapter.setOnItemListener(this);
        recyclerView.setAdapter(bottomSheetDialogAdapter);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (null != savedInstanceState) {
            mItemList = savedInstanceState.getParcelableArrayList(UtilBundles.ITEM_LIST);

        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (Utils.ItemListener) activity;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(UtilBundles.ITEM_LIST, mItemList);
    }

    @Override
    public void onItemListener(BottomSheetItem bottomSheetItem) {
        mListener.onItemListener(bottomSheetItem);
        dismiss();
    }

    public static class BottomSheetDialogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private ArrayList<BottomSheetItem> mItemList;
        private Utils.ItemListener mItemListener;

        public void setOnItemListener(Utils.ItemListener itemListener){
            this.mItemListener = itemListener;
        }

        public BottomSheetDialogAdapter(Context context) {

        }

        public void setItems(ArrayList<BottomSheetItem> items){
            mItemList = items;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bottom_sheet, parent, false);
            return new BottomSHeetDialogViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            BottomSheetItem bottomSheetItem = mItemList.get(position);
            if (bottomSheetItem != null) {
                ((BottomSHeetDialogViewHolder) holder).setBottomSheetItem(bottomSheetItem);
            }
        }

        @Override
        public int getItemCount() {
            if (!Utils.isEmptyList(mItemList)) {
                return mItemList.size();
            }
            return 0;
        }



        public class BottomSHeetDialogViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private final ImageView mBottomSheetIcon;
            private final TextView mBottomSheetListItem;

            public BottomSHeetDialogViewHolder(View itemView) {
                super(itemView);
                mBottomSheetIcon = (ImageView) itemView.findViewById(R.id.icon_bottom_sheet);
                mBottomSheetListItem = (TextView) itemView.findViewById(R.id.list_item_bottom_sheet);
                itemView.findViewById(R.id.bottm_sheet_container).setOnClickListener(this);
            }

            public void setBottomSheetItem(BottomSheetItem bottomSheetItem) {
                mBottomSheetIcon.setImageResource(bottomSheetItem.getBottomSheetIcon());
                mBottomSheetListItem.setText(bottomSheetItem.getBottomSheetItem());
            }

            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.bottm_sheet_container:
//                        mItemListener.onItemListener(mItemList.get(getLayoutPosition()));
                        break;
                }
            }
        }
    }
}
