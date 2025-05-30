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
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.news2.R;
import com.example.news2.data.model.SliderItem;
import com.example.news2.databinding.ItemSliderBinding;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    private List<SliderItem> sliderItems;
    private Context context;

    public SliderAdapter(Context context, List<SliderItem> sliderItems) {
        this.context = context;
        this.sliderItems = sliderItems;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        ItemSliderBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_slider, parent, false);

        return new SliderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {

        holder.binding.setModel(sliderItems.get(position));

        int radiusInPx = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 16, context.getResources().getDisplayMetrics());

        Glide.with(context)
                .load(sliderItems.get(position).getImageUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(radiusInPx)))
                .into(holder.binding.sliderImage);
    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder {

        ItemSliderBinding binding;

        public SliderViewHolder(@NonNull ItemSliderBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }


    public void setList(List<SliderItem> sliderItems) {
        this.sliderItems = sliderItems;
        notifyDataSetChanged();
    }
}
