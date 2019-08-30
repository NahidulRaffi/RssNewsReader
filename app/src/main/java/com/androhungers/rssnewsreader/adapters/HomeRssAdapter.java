package com.androhungers.rssnewsreader.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androhungers.rssnewsreader.R;
import com.androhungers.rssnewsreader.model.getRss.DataItem;
import com.androhungers.rssnewsreader.model.rssFeed.RssFeedDataModel;

import java.util.ArrayList;

public class HomeRssAdapter extends RecyclerView.Adapter<HomeRssAdapter.CustomViewHolder>{

    public ArrayList<RssFeedDataModel> itemArrayList = new ArrayList<>();
    Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener {

        /**
         * The method declaration for user selected. This method will be fired
         * when user click on check/uncheck the checkbox on the list item.
         *
         * @param position
         * @param item
         */
        public void onItemClick(int position, RssFeedDataModel item);
    }
    public void setListener(HomeRssAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public HomeRssAdapter(Context context){
        this.context = context;
        this.itemArrayList = new ArrayList<>();
    }

    public void setItemList(ArrayList<RssFeedDataModel> list) {

        this.itemArrayList = list;
    }

    public void loadNewItem(ArrayList<RssFeedDataModel> list){
        this.itemArrayList.addAll(list);
    }
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View groceryProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_home_rss, parent, false);
        CustomViewHolder gvh = new CustomViewHolder(groceryProductView);
        return gvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeRssAdapter.CustomViewHolder holder, final int position) {

        final RssFeedDataModel item = itemArrayList.get(position);

        holder.tvDetails.setText(item.getFeedItem().getMdescription());
        holder.tvFeed.setText(item.getFeedItem().getMtitle());
        holder.tvDate.setText(item.getTitle());

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(position,item);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (itemArrayList != null) {
            return itemArrayList.size();
        } else {
            return 0;
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView tvFeed, tvDetails, tvDate;
        RelativeLayout root;

        public CustomViewHolder(View view) {
            super(view);

            tvFeed = view.findViewById(R.id.tv_feed_name);
            tvDate = view.findViewById(R.id.tv_date);
            tvDetails = view.findViewById(R.id.tv_details);
            root = view.findViewById(R.id.rl_root);
        }
    }
}
