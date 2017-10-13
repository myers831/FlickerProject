package com.example.admin.flickerproject;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.flickerproject.model.Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 10/13/2017.
 */

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {

    Context context;
    List<Item> itemList = new ArrayList<>();

    public RecyclerviewAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.picrepo_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.item = item;
        Glide.with(context).load(item.getMedia().getM()).into(holder.ivPicImage);
        holder.tvPicTitle.setText("Title: " + item.getTitle());
        holder.tvPicAuthor.setText("Author: " + item.getAuthor());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPicImage;
        TextView tvPicTitle, tvPicAuthor;
        Item item;

        public ViewHolder(final View itemView) {
            super(itemView);

            itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {

                    //ImageActivity.item = itemList.get(itemView.getVerticalScrollbarPosition());
                    Intent intent = new Intent(context, ImageActivity.class);
                    intent.putExtra("start", item/*itemList.get(itemView.getVerticalScrollbarPosition())*/);
                    context.startActivity(intent);

                    //ImageActivity.take();
                }
            });

            ivPicImage = itemView.findViewById(R.id.ivPicImage);
            tvPicTitle = itemView.findViewById(R.id.tvPicTitle);
            tvPicAuthor = itemView.findViewById(R.id.tvPicAuthor);
        }
    }
}
