package com.example.news2.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.news2.R;
import com.example.news2.data.model.News;
import com.example.news2.data.model.NewsDbModel;
import com.example.news2.databinding.ActivityNewsBinding;
import com.example.news2.viewmodel.SavedNewsViewModel;

public class NewsActivity extends AppCompatActivity {

    ActivityNewsBinding binding;
    private SavedNewsViewModel viewModel;
    private boolean isSaved = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news);
//        setContentView(R.layout.activity_news);

        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        int halfHeight = screenHeight / 2;

//        Toast.makeText(this, String.valueOf(halfHeight), Toast.LENGTH_SHORT).show();

        ViewGroup.LayoutParams imgParams = binding.imageViewNews.getLayoutParams();
        imgParams.height = halfHeight;
        binding.imageViewNews.setLayoutParams(imgParams);

        ViewGroup.LayoutParams gradientParams = binding.gradientView.getLayoutParams();
        gradientParams.height = (halfHeight * 3)/4;
        binding.gradientView.setLayoutParams(gradientParams);



        News news = (News) getIntent().getSerializableExtra("news");


        binding.setNews(news);

        binding.imgProgressBar.setVisibility(View.VISIBLE);

        Glide.with(getApplicationContext())
                .load(news.getImage())
                .error(R.drawable.news)
                .fallback(R.drawable.news)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, @Nullable Object model, @NonNull Target<Drawable> target, boolean isFirstResource) {
                        binding.imgProgressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(@NonNull Drawable resource, @NonNull Object model, Target<Drawable> target, @NonNull DataSource dataSource, boolean isFirstResource) {
                        binding.imgProgressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(binding.imageViewNews);

        viewModel = new ViewModelProvider(this).get(SavedNewsViewModel.class);

        viewModel.getNews(news.getId()).observe(this, new Observer<NewsDbModel>() {
            @Override
            public void onChanged(NewsDbModel newsDbModel) {
                if (newsDbModel != null){
                    isSaved = true;
                    binding.imgSave.setImageResource(R.drawable.bookmarked);
                } else {
                    isSaved = false;
                    binding.imgSave.setImageResource(R.drawable.news_bookmark);
                }
            }
        });

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });


        binding.imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewsDbModel newsDbModel = new NewsDbModel(news.getId(), news.getAuthor(), news.getTitle(), news.getDescription(), news.getUrl(),
                        news.getSource(), news.getImage(), news.getCategory(), news.getLanguage(), news.getCountry(), news.getPublished_at());

                if (isSaved){
                    binding.imgSave.setImageResource(R.drawable.news_bookmark);
                    viewModel.deleteNews(newsDbModel);
                    isSaved = false;
                }else {
                    binding.imgSave.setImageResource(R.drawable.bookmarked);
                    viewModel.saveNews(newsDbModel);
                    isSaved = true;
                }
            }
        });




    }
}