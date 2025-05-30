package com.example.news2.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.news2.R;
import com.example.news2.data.model.CategorizedNewsModel;
import com.example.news2.databinding.ItemCategorizedBinding;

import java.util.List;

public class CategorizedNewsAdapter extends RecyclerView.Adapter<CategorizedNewsAdapter.AdapterViewHolder> {

    private List<CategorizedNewsModel> items;
    private Context context;

    public CategorizedNewsAdapter(List<CategorizedNewsModel> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        ItemCategorizedBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_categorized, parent, false);

        return new AdapterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        holder.binding.setModel(items.get(position));

        Glide.with(context)
                .load(items.get(position).getImageUrl())
                .into(holder.binding.newsImageview);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class AdapterViewHolder extends RecyclerView.ViewHolder{

        ItemCategorizedBinding binding;

        public AdapterViewHolder(@NonNull ItemCategorizedBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }
}
