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

import java.util.Collections;
import java.util.List;

/**
 * Created by vibes on 28/4/16.
 */
public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.MyViewHolder> {
    List<Blog> blogList = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public SearchRecyclerViewAdapter(Context context, List<Blog> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.blogList = data;
    }

    public void delete(int position) {
        blogList.remove(position);
        notifyItemRemoved(position);
    }

    public void addItem(Blog blog) {
        blogList.add(blog);
        notifyItemChanged(getItemCount() - 1);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_search_coach, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Blog current = blogList.get(position);

    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imageViewIcons;

        public MyViewHolder(View itemView) {
            super(itemView);

        }
    }

}
