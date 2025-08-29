package com.example.news2.ui.details;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.news2.R;
import com.example.news2.data.model.BreakingNewsModel;
import com.example.news2.data.model.News;
import com.example.news2.data.model.NewsDbModel;
import com.example.news2.data.model.SliderItem;
import com.example.news2.databinding.FragmentHomeNewsBinding;
import com.example.news2.ui.adapter.BreakingNewsAdapter;
import com.example.news2.ui.adapter.SliderAdapter;
import com.example.news2.ui.main.NewsActivity;
import com.example.news2.utils.Refreshable;
import com.example.news2.viewmodel.NewsViewModel;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;
import java.util.List;


public class HomeNewsFragment extends Fragment implements Refreshable {

    private FragmentHomeNewsBinding binding;
    ViewPager2 viewPager2;
    private Handler sliderHandler = new Handler();
    private Runnable sliderRunnable;

    private ValueAnimator autoScrollAnimator;
    private final long SPEED_MS_PER_PIXEL = 15;
    RecyclerView breakingRecycler, hotRecycler;
    LinearLayoutManager lm = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
    DotsIndicator dotsIndicator;
    List<NewsDbModel> sliderItems;
    SliderAdapter sliderAdapter;
    List<NewsDbModel> breakingNewsItems;
    List<NewsDbModel> hotNewsItems;
    BreakingNewsAdapter breakingNewsAdapter;
    BreakingNewsAdapter hotNewsAdapter;

    List<News> newsList = new ArrayList<>();

    NewsViewModel viewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_news, container, false);

        init();
        viewModel = new ViewModelProvider(getActivity()).get(NewsViewModel.class);

        getNews();

        sliderRunnable = new Runnable() {
            @Override
            public void run() {
                if (sliderAdapter.getItemCount() != 0){

                    int nextItem = (viewPager2.getCurrentItem() + 1) % sliderAdapter.getItemCount();
                    viewPager2.setCurrentItem(nextItem, true);
                    sliderHandler.postDelayed(this, 4000);
                }
            }
        };

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {


            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                resetSliderTimer();
            }

        });

        breakingRecycler.smoothScrollBy(10, 0, new LinearInterpolator(), 5000);
        hotRecycler.smoothScrollBy(10, 0, new LinearInterpolator(), 5000);
        


        return binding.getRoot();
    }


    private void resetSliderTimer() {
        stopSlider();
        startSlider();
    }

    private void startSlider() {
        sliderHandler.postDelayed(sliderRunnable, 4000);
    }

    private void stopSlider() {
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        startSlider();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopSlider();
    }

    public void init() {
        viewPager2 = binding.viewPagerSlider;
        dotsIndicator = binding.dotsIndicator;
        breakingRecycler = binding.breakingRecycler;
        hotRecycler = binding.hotRecycler;

        sliderItems = new ArrayList<>();
        breakingNewsItems = new ArrayList<>();
        hotNewsItems = new ArrayList<>();

        sliderAdapter = new SliderAdapter(getContext(), sliderItems);
        breakingNewsAdapter = new BreakingNewsAdapter(breakingNewsItems, getContext());
        hotNewsAdapter = new BreakingNewsAdapter(hotNewsItems, getContext());

        viewPager2.setAdapter(sliderAdapter);
        dotsIndicator.attachTo(viewPager2);

        breakingRecycler.setAdapter(breakingNewsAdapter);
        breakingRecycler.setLayoutManager(lm);
        breakingRecycler.setHasFixedSize(true);

        hotRecycler.setAdapter(hotNewsAdapter);
        hotRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        hotRecycler.setHasFixedSize(true);

    }


    public void getNews() {


        viewModel.getTopHeadlines().observe(getViewLifecycleOwner(), new Observer<List<NewsDbModel>>() {
            @Override
            public void onChanged(List<NewsDbModel> newsDbModels) {
                if (newsDbModels.size() >= 9) {

                    sliderItems.add(newsDbModels.get(0));
                    sliderItems.add(newsDbModels.get(1));
                    sliderItems.add(newsDbModels.get(2));
                    breakingNewsItems.add(newsDbModels.get(3));
                    breakingNewsItems.add(newsDbModels.get(4));
                    breakingNewsItems.add(newsDbModels.get(5));
                    hotNewsItems.add(newsDbModels.get(6));
                    hotNewsItems.add(newsDbModels.get(7));
                    hotNewsItems.add(newsDbModels.get(8));


                    sliderAdapter.notifyDataSetChanged();
                    breakingNewsAdapter.notifyDataSetChanged();
                    hotNewsAdapter.notifyDataSetChanged();
//
                }
            }
        });



//        View child = viewPager2.getChildAt(0);
//        if (child != null) {
//            child.setOnTouchListener((v, event) -> {
//                v.getParent().requestDisallowInterceptTouchEvent(true);
//                return false;
//            });
//        }

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void refreshData() {
        refreshData();
    }
}
