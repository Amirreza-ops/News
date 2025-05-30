package com.example.news2.ui.adapter;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.news2.R;
import com.example.news2.data.model.BreakingNewsModel;
import com.example.news2.databinding.ItemBreakingBinding;

import java.util.List;

public class BreakingNewsAdapter extends RecyclerView.Adapter<BreakingNewsAdapter.AdapterViewHolder> {

    private List<BreakingNewsModel> items;
    private Context context;

    public BreakingNewsAdapter(List<BreakingNewsModel> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        ItemBreakingBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_breaking, parent, false);

        return new AdapterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        holder.binding.setModel(items.get(position));

        int radiusInPx = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 16, context.getResources().getDisplayMetrics());

        Glide.with(context)
                .load(items.get(position).getImageUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(radiusInPx)))
                .into(holder.binding.itemImage);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class AdapterViewHolder extends RecyclerView.ViewHolder{

        ItemBreakingBinding binding;

        public AdapterViewHolder(@NonNull ItemBreakingBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }
}
