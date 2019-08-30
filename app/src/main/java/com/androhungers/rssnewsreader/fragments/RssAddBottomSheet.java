package com.androhungers.rssnewsreader.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.androhungers.rssnewsreader.R;
import com.androhungers.rssnewsreader.common.Common;
import com.androhungers.rssnewsreader.common.Constants;
import com.androhungers.rssnewsreader.common.PreferenceHelper;
import com.androhungers.rssnewsreader.model.addRss.AddRssRequestModel;
import com.androhungers.rssnewsreader.model.addRss.AddRssResponseModel;
import com.androhungers.rssnewsreader.model.deleteRss.DeleteRssRequestModel;
import com.androhungers.rssnewsreader.model.deleteRss.DeleteRssResponseModel;
import com.androhungers.rssnewsreader.viewModel.RssFeedViewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;
import com.victor.loading.rotate.RotateLoading;

import de.mrapp.android.dialog.MaterialDialog;

import static android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;

public class RssAddBottomSheet extends BottomSheetDialogFragment {
    View contentView;
    BottomSheetBehavior bottomSheetBehavior;
    RssFeedViewModel viewModel;


    public RssAddBottomSheet(RssFeedViewModel viewModel) {
        this.viewModel = viewModel;
    }

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }
            if (newState == BottomSheetBehavior.STATE_DRAGGING) {

            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {

        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle);
    }

    @Override
    public void setupDialog(final Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        init(dialog);

        EditText etFeedName = contentView.findViewById(R.id.et_feed_name);
        EditText etLink = contentView.findViewById(R.id.et_link);
        ImageView imgBack = contentView.findViewById(R.id.img_close);
        TextView tvAdd = contentView.findViewById(R.id.btn_add);
        RotateLoading rotateLoading = contentView.findViewById(R.id.progress);


        viewModel.errorMsg.postValue("");
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rotateLoading.start();
                tvAdd.setText("");
                viewModel.feedNameLiveData.setValue(etFeedName.getText().toString());
                viewModel.linkLiveData.setValue(etLink.getText().toString());

                if(viewModel.isValidInput()){
                    String userId = String.valueOf(new Common().getUserDataFromSignIn(new PreferenceHelper(getContext()).getString(Constants.USER_DATA_FIELD)).getId());
                    viewModel.addRssRequestModelMutableLiveData.setValue(new AddRssRequestModel(userId,viewModel.feedNameLiveData.getValue(),viewModel.linkLiveData.getValue()));
                    viewModel.requestForAddRss(viewModel.addRssRequestModelMutableLiveData.getValue()).observe(RssAddBottomSheet.this, new Observer<AddRssResponseModel>() {
                        @Override
                        public void onChanged(AddRssResponseModel addRssResponseModel) {
                            rotateLoading.stop();
                            tvAdd.setText("ADD");
                            if(addRssResponseModel.isSuccess()){
                                viewModel.addRssResponseModelMutableLiveData.postValue(addRssResponseModel);
                                dismiss();
                            }else {
                                MaterialDialog.Builder dialogBuilder = new MaterialDialog.Builder(getContext());
                                dialogBuilder.setButtonTextColor(getResources().getColor(R.color.colorPrimary));
                                dialogBuilder.setTitle("Error !!!");
                                dialogBuilder.setMessage("Something went wrong. Please try again.");

                                dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                                dialogBuilder.setNegativeButton(android.R.string.cancel, null);
                                MaterialDialog dialog = dialogBuilder.create();
                                dialog.show();
                                //Snackbar.make(root, "Something went wrong", Snackbar.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        viewModel.errorMsg.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(!s.equalsIgnoreCase("")){
                    rotateLoading.stop();
                    tvAdd.setText("ADD");
                    MaterialDialog.Builder dialogBuilder = new MaterialDialog.Builder(getContext());
                    dialogBuilder.setButtonTextColor(getResources().getColor(R.color.colorPrimary));
                    dialogBuilder.setTitle("Alert !!!");
                    dialogBuilder.setMessage(s);

                    dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    dialogBuilder.setNegativeButton(android.R.string.cancel, null);
                    MaterialDialog dialog = dialogBuilder.create();
                    dialog.show();
                }

                //Snackbar.make(root, s, Snackbar.LENGTH_LONG).show();
            }
        });

    }


    private void init(Dialog dialog){
        contentView = View.inflate(getContext(), R.layout.rss_add_bottom_sheet, null);
        dialog.setContentView(contentView);


        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent())
                .getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();
        ((View) contentView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));


        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }

        View parent = (View) contentView.getParent();
        parent.setFitsSystemWindows(true);
        bottomSheetBehavior = BottomSheetBehavior.from(parent);
        contentView.measure(0, 0);

        if (params.getBehavior() instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior)params.getBehavior()).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }

        parent.setLayoutParams(params);

        getActivity().getWindow().setSoftInputMode(SOFT_INPUT_ADJUST_RESIZE);

    }


}
