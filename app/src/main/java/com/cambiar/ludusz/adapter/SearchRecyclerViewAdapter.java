package com.cambiar.ludusz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cambiar.ludusz.R;
import com.cambiar.ludusz.interfaces.Blog;
import com.cambiar.ludusz.model.SearchBase;

import java.util.Collections;
import java.util.List;

/**
 * Created by vibes on 28/4/16.
 */
public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.MyViewHolder> {
    List<SearchBase> searchDataList = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public SearchRecyclerViewAdapter(Context context, List<SearchBase> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.searchDataList = data;
    }

    public void delete(int position) {
        searchDataList.remove(position);
        notifyItemRemoved(position);
    }

    public void addItem(SearchBase blog) {
        searchDataList.add(blog);
        notifyItemChanged(getItemCount() - 1);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_search_player, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SearchBase current = searchDataList.get(position);

    }

    @Override
    public int getItemCount() {
        return searchDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imageViewIcons;

        public MyViewHolder(View itemView) {
            super(itemView);

        }
    }

}
