package com.example.news2.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.news2.R;
import com.example.news2.data.model.CategorizedNewsModel;
import com.example.news2.data.model.News;
import com.example.news2.data.model.NewsDbModel;
import com.example.news2.databinding.ItemCategorizedBinding;
import com.example.news2.ui.main.NewsActivity;

import java.util.List;

public class CategorizedNewsAdapter extends RecyclerView.Adapter<CategorizedNewsAdapter.AdapterViewHolder> {

    private List<NewsDbModel> items;
    private Context context;

    public CategorizedNewsAdapter(List<NewsDbModel> items, Context context) {
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

        holder.binding.imgProgressBar.setVisibility(View.VISIBLE);



        Glide.with(context)
                .load(items.get(position).getImage())
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


            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, NewsActivity.class);
                    NewsDbModel newsDbModel = items.get(getAdapterPosition());
                    News news = new News(newsDbModel.getId(), newsDbModel.getAuthor(), newsDbModel.getTitle(), newsDbModel.getDescription(), newsDbModel.getUrl()
                            ,newsDbModel.getSource(), newsDbModel.getImage(), newsDbModel.getCategory(), newsDbModel.getLanguage(), newsDbModel.getCountry(), newsDbModel.getPublished_at());
                    intent.putExtra("news", news);
                    context.startActivity(intent);
                }
            });
        }
    }
}
