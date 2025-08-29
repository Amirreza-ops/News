package com.example.news2.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.news2.R;
import com.example.news2.data.model.News;
import com.example.news2.data.model.NewsDbModel;
import com.example.news2.data.model.SliderItem;
import com.example.news2.databinding.ItemSliderBinding;
import com.example.news2.ui.main.NewsActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    private List<NewsDbModel> sliderItems;
    private Context context;
//    OnItemClickListener listener;

    public SliderAdapter(Context context, List<NewsDbModel> sliderItems) {
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


        holder.binding.imgProgressBar.setVisibility(View.VISIBLE);

        Glide.with(context)
                .load(sliderItems.get(position).getImage())
                .error(R.drawable.news)
                .fallback(R.drawable.news)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, @Nullable Object model, @NonNull Target<Drawable> target, boolean isFirstResource) {
                        holder.binding.imgProgressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(@NonNull Drawable resource, @NonNull Object model, Target<Drawable> target, @NonNull DataSource dataSource, boolean isFirstResource) {
                        holder.binding.imgProgressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
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


            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, NewsActivity.class);
                    NewsDbModel newsDbModel = sliderItems.get(getAdapterPosition());
                    News news = new News(newsDbModel.getId(), newsDbModel.getAuthor(), newsDbModel.getTitle(), newsDbModel.getDescription(), newsDbModel.getUrl()
                    ,newsDbModel.getSource(), newsDbModel.getImage(), newsDbModel.getCategory(), newsDbModel.getLanguage(), newsDbModel.getCountry(), newsDbModel.getPublished_at());
                    intent.putExtra("news",  news);
                    context.startActivity(intent);
                }
            });
        }
    }


//    public interface OnItemClickListener {
//        void onItemClick(News item);
//    }

//    public void setList(List<SliderItem> sliderItems) {
//        this.sliderItems = sliderItems;
//        notifyDataSetChanged();
//    }
}
