package com.easy.make.tenantmaker.utils;

/**
 * Created by ravi on 17/07/16.
 */
public class BottomSheetDialogView {

//    public static BottomSheetDialog BottomSheetDialogView(Context context, ArrayList<BottomSheetItem> bottomSheetItemArrayList){
//        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
//        View view = LayoutInflater.from(context).inflate(R.layout.layout_bottom_sheet, null);
//        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.bottom_sheet_recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(context));
//        recyclerView.setAdapter(new BottomSheetDialogAdapter(context, bottomSheetItemArrayList));
//        bottomSheetDialog.setContentView(view);
//        return bottomSheetDialog;
//    }
//
//
//    public static class BottomSheetDialogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
//
//        private final Context mContext;
//        private final ArrayList<BottomSheetItem> mBottemSheetItemList;
//
//        public BottomSheetDialogAdapter(Context context, ArrayList<BottomSheetItem> bottomSheetItemList){
//            this.mContext = context;
//            this.mBottemSheetItemList = bottomSheetItemList;
//        }
//
//        @Override
//        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bottom_sheet, parent, false);
//            return new BottomSHeetDialogViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//            BottomSheetItem bottomSheetItem = mBottemSheetItemList.get(position);
//            if (bottomSheetItem != null){
//                ((BottomSHeetDialogViewHolder) holder).setBottomSheetItem(bottomSheetItem);
//            }
//        }
//
//        @Override
//        public int getItemCount() {
//            if (!Utils.isEmptyList(mBottemSheetItemList)){
//                return mBottemSheetItemList.size();
//            }
//            return 0;
//        }
//
//
//        public static class BottomSHeetDialogViewHolder extends RecyclerView.ViewHolder {
//
//            private final ImageView mBottomSheetIcon;
//            private final TextView mBottomSheetListItem;
//
//            public BottomSHeetDialogViewHolder(View itemView) {
//                super(itemView);
//                mBottomSheetIcon = (ImageView) itemView.findViewById(R.id.icon_bottom_sheet);
//                mBottomSheetListItem = (TextView) itemView.findViewById(R.id.list_item_bottom_sheet);
//            }
//
//            public void setBottomSheetItem(BottomSheetItem bottomSheetItem){
//                mBottomSheetIcon.setImageResource(bottomSheetItem.getBottomSheetIcon());
//                mBottomSheetListItem.setText(bottomSheetItem.getBottomSheetItem());
//            }
//        }
//    }
}
