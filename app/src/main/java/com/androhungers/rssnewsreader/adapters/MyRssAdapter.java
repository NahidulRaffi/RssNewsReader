package com.androhungers.rssnewsreader.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androhungers.rssnewsreader.R;
import com.androhungers.rssnewsreader.model.getRss.DataItem;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyRssAdapter extends RecyclerView.Adapter<MyRssAdapter.CustomViewHolder>{

    public ArrayList<DataItem> itemArrayList;
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
        public void onEditClick(int position, DataItem item);
        public void onDeleteClick(int position, DataItem item);

    }
    public void setListener(MyRssAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public MyRssAdapter( Context context){
        this.context = context;
    }

    public void setItemList(ArrayList<DataItem> list) {
        itemArrayList = list;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View groceryProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_my_rss, parent, false);
        CustomViewHolder gvh = new CustomViewHolder(groceryProductView);
        return gvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyRssAdapter.CustomViewHolder holder, final int position) {

        final DataItem item = itemArrayList.get(position);

        holder.tvLink.setText(item.getLink());
        holder.tvFeed.setText(item.getFeedName());

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDeleteClick(position,item);
            }
        });

        holder.llEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onEditClick(position,item);
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

        TextView tvFeed, tvLink;
        ImageView imgDelete;
        LinearLayout llEdit;

        public CustomViewHolder(View view) {
            super(view);

            tvFeed = view.findViewById(R.id.tv_feed_name);
            tvLink = view.findViewById(R.id.tv_link);
            llEdit = view.findViewById(R.id.ll_edit);
            imgDelete = view.findViewById(R.id.img_delete);
        }
    }
}
