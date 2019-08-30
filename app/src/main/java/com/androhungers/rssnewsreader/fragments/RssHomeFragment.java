package com.androhungers.rssnewsreader.fragments;

import android.content.Context;
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

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androhungers.rssnewsreader.R;
import com.androhungers.rssnewsreader.activities.RssFeedActivity;
import com.androhungers.rssnewsreader.adapters.HomeRssAdapter;
import com.androhungers.rssnewsreader.adapters.MyRssAdapter;
import com.androhungers.rssnewsreader.model.getRss.DataItem;
import com.androhungers.rssnewsreader.model.getRss.GetRssResponseModel;
import com.androhungers.rssnewsreader.model.rssFeed.Feed;
import com.androhungers.rssnewsreader.model.rssFeed.FeedItem;
import com.androhungers.rssnewsreader.model.rssFeed.RssFeedDataModel;
import com.androhungers.rssnewsreader.viewModel.RssFeedViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RssHomeFragment extends Fragment implements HomeRssAdapter.OnItemClickListener{

    public static String tag = RssHomeFragment.class.toString();
    private RssFeedActivity rssFeedActivity = new RssFeedActivity();
    RssFeedViewModel viewModel;
    @BindView(R.id.img_empty)
    ImageView imgEmpty;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    LinearLayoutManager linearLayoutManager;
    HomeRssAdapter adapter;

    //http://feeds.bbci.co.uk/news/rss.xml

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rss_home, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Init();
        setUpListener();
        setUpRecyclerView();
        swipeRefreshLayout.setRefreshing(true);

        viewModel.myRssList.observe(this, new Observer<ArrayList<DataItem>>() {
            @Override
            public void onChanged(ArrayList<DataItem> dataItems) {
                adapter.itemArrayList.clear();
                adapter.notifyDataSetChanged();
                if(!dataItems.isEmpty()){
                    viewModel.recentData.setValue(new ArrayList<RssFeedDataModel>());
                    viewModel.myRssListCount = dataItems.size()-1;

                    String url = dataItems.get(viewModel.myRssListCount).getLink();

                    setUrl(url);
                }else {
                    swipeRefreshLayout.setRefreshing(false);

                }

            }
        });

        viewModel.getRssFeed.observe(this, new Observer<Feed>() {
            @Override
            public void onChanged(Feed feed) {
                String s = new Gson().toJson(feed);
                Log.d("::Home::", s);
                viewModel.isLoading = false;
                if(!s.isEmpty()){
                    swipeRefreshLayout.setRefreshing(false);

                    try{
                        List<FeedItem> feedItems = feed.getmChannel().getmFeedItems();
                        ArrayList<RssFeedDataModel> rssFeedDataModels = new ArrayList<>();
                        for(int i = 0; i < feed.getmChannel().getmFeedItems().size(); i++){
                            RssFeedDataModel rssFeedDataModel = new RssFeedDataModel(feedItems.get(i),feed.getmChannel().getmTitle());
                            rssFeedDataModels.add(rssFeedDataModel);
                        }

                        viewModel.recentData.setValue(rssFeedDataModels);
                        viewModel.addResults(rssFeedDataModels);
                    }catch (NullPointerException e){
                        if(!viewModel.isLoading){
                            viewModel.isLoading = true;
                            swipeRefreshLayout.setRefreshing(true);
                            if(viewModel.myRssListCount > 0){
                                viewModel.myRssListCount = viewModel.myRssListCount - 1;
                                String url = viewModel.myRssList.getValue().get(viewModel.myRssListCount).getLink();
                                setUrl(url);
                            }else {
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        }
                    }

                }else {
                    if(!viewModel.isLoading){
                        viewModel.isLoading = true;
                        swipeRefreshLayout.setRefreshing(true);
                        if(viewModel.myRssListCount > 0){
                            viewModel.myRssListCount = viewModel.myRssListCount - 1;
                            String url = viewModel.myRssList.getValue().get(viewModel.myRssListCount).getLink();
                            setUrl(url);
                        }else {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }
                }
            }
        });

        viewModel.arrayListMutableLiveData.observe(this, new Observer<ArrayList<RssFeedDataModel>>() {
            @Override
            public void onChanged(ArrayList<RssFeedDataModel> rssFeedDataModels) {
                if(rssFeedDataModels.size() > 0){
                    imgEmpty.setVisibility(View.GONE);
                }
                Log.d("::Home::", ">>>>>>>>>>>>>>");
                adapter.loadNewItem(viewModel.recentData.getValue());
                adapter.notifyDataSetChanged();
            }
        });


    }

    private void setUrl(String url){
        if(viewModel.isValidUrl(url)){
            ArrayList<String> str = (ArrayList<String>) divideUrl(url);
            String s = new Gson().toJson(str);
            Log.d("::Home::", s);
            viewModel.feedBaseUrl.setValue(str.get(0));
            viewModel.feedUrl.setValue(str.get(1));
        }else {
            if(viewModel.myRssListCount > 0){
                viewModel.myRssListCount = viewModel.myRssListCount - 1;
                String url2 = viewModel.myRssList.getValue().get(viewModel.myRssListCount).getLink();
                setUrl(url2);
            }
        }

    }

    private void setUpRecyclerView(){
        linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new HomeRssAdapter(getContext());
        recyclerView.setAdapter(adapter);
        adapter.setListener(this);
        adapter.setItemList(new ArrayList<RssFeedDataModel>());

        viewModel.arrayListMutableLiveData.setValue(new ArrayList<RssFeedDataModel>());
        viewModel.recentData.setValue(new ArrayList<RssFeedDataModel>());
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(final RecyclerView recyclerView, int scrollState) {

                if (!recyclerView.canScrollVertically(1)) {

                    final Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if(!viewModel.isLoading){
                                viewModel.isLoading = true;
                                swipeRefreshLayout.setRefreshing(true);
                                if(viewModel.myRssListCount > 0){
                                    viewModel.myRssListCount = viewModel.myRssListCount - 1;
                                    String url = viewModel.myRssList.getValue().get(viewModel.myRssListCount).getLink();
                                    setUrl(url);
                                }else {
                                    swipeRefreshLayout.setRefreshing(false);
                                }
                            }


                        }
                    },500);
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            }
        });


    }

    private void setUpListener(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.itemArrayList.clear();
                adapter.notifyDataSetChanged();
                if(!viewModel.myRssList.getValue().isEmpty()){

                    viewModel.recentData.setValue(new ArrayList<RssFeedDataModel>());
                    viewModel.myRssListCount = viewModel.myRssList.getValue().size()-1;
                    String url = viewModel.myRssList.getValue().get(viewModel.myRssListCount).getLink();
                    setUrl(url);
                }else {
                    swipeRefreshLayout.setRefreshing(false);

                }

            }
        });
    }

    private void Init(){
        viewModel = ViewModelProviders.of(getActivity()).get(RssFeedViewModel.class);
        rssFeedActivity = (RssFeedActivity) getActivity();
    }

    private List<String> divideUrl(String url){
        ArrayList<String> temp = new ArrayList<>();
        String last = "";
        String base = "";
        boolean isBase = false;
        for(int i = url.length()-1 ; i != -1 ;i--){
            if(url.charAt(i) == '/'){
                isBase = true;
            }
            if(isBase){
                base = base + url.charAt(i);
            }else {
                last = last+url.charAt(i);
            }
        }

        temp.add(new StringBuilder(base).reverse().toString());
        temp.add(new StringBuilder(last).reverse().toString());

        return temp;
    }

    @Override
    public void onItemClick(int position, RssFeedDataModel item) {
        viewModel.selectedData.setValue(item);

        Log.i("::","Clicked");

        rssFeedActivity.provideManager().push(new DetailsWebFragment(), DetailsWebFragment.tag);
    }
}
