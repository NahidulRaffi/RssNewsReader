package com.androhungers.rssnewsreader.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androhungers.rssnewsreader.R;
import com.androhungers.rssnewsreader.activities.RssFeedActivity;
import com.androhungers.rssnewsreader.adapters.MyRssAdapter;
import com.androhungers.rssnewsreader.common.Common;
import com.androhungers.rssnewsreader.common.Constants;
import com.androhungers.rssnewsreader.common.PreferenceHelper;
import com.androhungers.rssnewsreader.model.addRss.AddRssResponseModel;
import com.androhungers.rssnewsreader.model.deleteRss.DeleteRssRequestModel;
import com.androhungers.rssnewsreader.model.deleteRss.DeleteRssResponseModel;
import com.androhungers.rssnewsreader.model.editRss.EditRssRequestModel;
import com.androhungers.rssnewsreader.model.editRss.EditRssResponseModel;
import com.androhungers.rssnewsreader.model.getRss.DataItem;
import com.androhungers.rssnewsreader.model.getRss.GetRssRequestModel;
import com.androhungers.rssnewsreader.model.getRss.GetRssResponseModel;
import com.androhungers.rssnewsreader.viewModel.RssFeedViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.mrapp.android.dialog.MaterialDialog;


public class MyRssFragment extends Fragment implements MyRssAdapter.OnItemClickListener{
    public static String tag = MyRssFragment.class.toString();
    private RssFeedActivity rssFeedActivity = new RssFeedActivity();
    RssFeedViewModel viewModel;

    @BindView(R.id.img_add)
    ImageView imgAdd;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    LinearLayoutManager linearLayoutManager;
    MyRssAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_rss, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.i("::>>","Child Frag Activity Created");

        rssFeedActivity = (RssFeedActivity) getActivity();
        viewModel = ViewModelProviders.of(getActivity()).get(RssFeedViewModel.class);

        setUpRecyclerView();

        String userId = String.valueOf(new Common().getUserDataFromSignIn(new PreferenceHelper(getContext()).getString(Constants.USER_DATA_FIELD)).getId());
        viewModel.getRssRequestModelMutableLiveData.setValue(new GetRssRequestModel(userId));


        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RssAddBottomSheet bottomSheetLayout = new RssAddBottomSheet(viewModel);
                bottomSheetLayout.show(getFragmentManager(), "OK");
            }
        });

        viewModel.addRssResponseModelMutableLiveData.observe(this, new Observer<AddRssResponseModel>() {
            @Override
            public void onChanged(AddRssResponseModel addRssResponseModel) {
                if(addRssResponseModel.isSuccess()){
                    Log.i(":::","OKKKK");
                    String userId = String.valueOf(new Common().getUserDataFromSignIn(new PreferenceHelper(getContext()).getString(Constants.USER_DATA_FIELD)).getId());
                    viewModel.getRssRequestModelMutableLiveData.setValue(new GetRssRequestModel(userId));

                }
            }
        });

        viewModel.editRssResponseModelMutableLiveData.observe(this, new Observer<EditRssResponseModel>() {
            @Override
            public void onChanged(EditRssResponseModel editRssResponseModel) {
                if(editRssResponseModel.isSuccess()){
                    Log.i(":::","OKKKK");
                    String userId = String.valueOf(new Common().getUserDataFromSignIn(new PreferenceHelper(getContext()).getString(Constants.USER_DATA_FIELD)).getId());
                    viewModel.getRssRequestModelMutableLiveData.setValue(new GetRssRequestModel(userId));

                }
            }
        });

        viewModel.getGetResponse.observe(this, new Observer<GetRssResponseModel>() {
            @Override
            public void onChanged(GetRssResponseModel getRssResponseModel) {
                swipeRefreshLayout.setRefreshing(false);
                if(getRssResponseModel.isSuccess()){
                    adapter.setItemList((ArrayList<DataItem>) getRssResponseModel.getData());
                    adapter.notifyDataSetChanged();
                }
            }
        });

        viewModel.getRssResponseModelMutableLiveData.observe(this, new Observer<GetRssResponseModel>() {
            @Override
            public void onChanged(GetRssResponseModel getRssResponseModel) {
                if(getRssResponseModel.isSuccess()){
                    adapter.setItemList((ArrayList<DataItem>) getRssResponseModel.getData());
                    adapter.notifyDataSetChanged();
                }
            }
        });

        viewModel.getRssRequestModelMutableLiveData.observe(this, new Observer<GetRssRequestModel>() {
            @Override
            public void onChanged(GetRssRequestModel getRssRequestModel) {
                swipeRefreshLayout.setRefreshing(true);
            }
        });

        viewModel.deleteRssResponseModelMutableLiveData.observe(this, new Observer<DeleteRssResponseModel>() {
            @Override
            public void onChanged(DeleteRssResponseModel deleteRssResponseModel) {
                String userId = String.valueOf(new Common().getUserDataFromSignIn(new PreferenceHelper(getContext()).getString(Constants.USER_DATA_FIELD)).getId());
                viewModel.getRssRequestModelMutableLiveData.setValue(new GetRssRequestModel(userId));
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                String userId = String.valueOf(new Common().getUserDataFromSignIn(new PreferenceHelper(getContext()).getString(Constants.USER_DATA_FIELD)).getId());
                viewModel.getRssRequestModelMutableLiveData.setValue(new GetRssRequestModel(userId));

            }
        });

    }

    private void setUpRecyclerView(){
        linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MyRssAdapter(getContext());
        recyclerView.setAdapter(adapter);
        adapter.setListener(this);
    }

    @Override
    public void onEditClick(int position, DataItem item) {
        viewModel.editRssRequestModelMutableLiveData.setValue(new EditRssRequestModel(item.getId(),item.getFeedName(),item.getLink()));
        RssEditBottomSheet bottomSheetLayout = new RssEditBottomSheet(viewModel);
        bottomSheetLayout.show(getFragmentManager(), "OK");
    }

    @Override
    public void onDeleteClick(int position, DataItem item) {
        MaterialDialog.Builder dialogBuilder = new MaterialDialog.Builder(getContext());
        dialogBuilder.setButtonTextColor(getResources().getColor(R.color.colorPrimary));
        dialogBuilder.setTitle("Delete !!!");
        dialogBuilder.setMessage("Are you sure to delete this RSS from your list?");

        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                viewModel.deleteRssRequestModelMutableLiveData.setValue(new DeleteRssRequestModel(item.getId()));
                viewModel.requestForDeleteRss(viewModel.deleteRssRequestModelMutableLiveData.getValue()).observe(MyRssFragment.this, new Observer<DeleteRssResponseModel>() {
                    @Override
                    public void onChanged(DeleteRssResponseModel deleteRssResponseModel) {
                        if(deleteRssResponseModel.isSuccess()){
                            viewModel.deleteRssResponseModelMutableLiveData.setValue(deleteRssResponseModel);
                        }else {

                        }
                    }
                });
            }
        });
        dialogBuilder.setNegativeButton(android.R.string.cancel, null);
        MaterialDialog dialog = dialogBuilder.create();
        dialog.show();
    }
}
