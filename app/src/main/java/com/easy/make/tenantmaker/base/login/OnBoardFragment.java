package com.easy.make.tenantmaker.base.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.base.utils.UtilBundles;

/**
 * Created by ravi on 12/09/16.
 */
public class OnBoardFragment extends Fragment {
    public static final int TYPE_ONE = 1;
    public static final int TYPE_TWO = 2;
    public static final int TYPE_THREE = 3;

    public static final String TAG_1 = "OnBoard screen 1";
    public static final String TAG_2 = "OnBoard screen 2";
    public static final String TAG_3 = "OnBoard screen 3";

    private Bundle args;
    private TextView headingTxt;
    private AppCompatImageView boardingImg;
    private TextView detailTxt;

    public static OnBoardFragment newInstance(Bundle bundle){
        OnBoardFragment onBoardFragment = new OnBoardFragment();
        onBoardFragment.setArguments(bundle);
        return onBoardFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null){
            args = getArguments();
        } else {
            args = savedInstanceState.getBundle(UtilBundles.EXTRA_BUNDLE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_onboard, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initControls(view);
        populateBoardingScreen(args.getInt(UtilBundles.EXTRA_INT));
    }

    private void initControls(View view){
        headingTxt = (TextView) view.findViewById(R.id.txt_heading);
        boardingImg = (AppCompatImageView) view.findViewById(R.id.boarding_img);
        detailTxt = (TextView) view.findViewById(R.id.txt_details);
    }

    private void populateBoardingScreen(int type){
        switch (type){
            case TYPE_ONE:
                headingTxt.setText(getString(R.string.str_boarding_heading_type_one));
                boardingImg.setImageDrawable(VectorDrawableCompat.create(getResources(), R.drawable.ic_photo_size_select_actual_black_24dp, null));
                detailTxt.setText(getString(R.string.str_boarding_footer_type_one));
                break;
            case TYPE_TWO:
                headingTxt.setText(getString(R.string.str_boarding_heading_type_two));
                boardingImg.setImageDrawable(VectorDrawableCompat.create(getResources(), R.drawable.ic_photo_size_select_actual_black_24dp, null));
                detailTxt.setText(getString(R.string.str_boarding_footer_type_two));
                break;
            case TYPE_THREE:
                headingTxt.setText(getString(R.string.str_boarding_heading_type_three));
                boardingImg.setImageDrawable(VectorDrawableCompat.create(getResources(), R.drawable.ic_contact_mail_black_24dp, null));
                detailTxt.setText(getString(R.string.str_boarding_footer_type_three));
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(UtilBundles.EXTRA_BUNDLE, args);
    }
}
