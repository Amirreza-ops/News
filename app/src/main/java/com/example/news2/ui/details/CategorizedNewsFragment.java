package com.example.news2.ui.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news2.R;
import com.example.news2.data.model.BreakingNewsModel;
import com.example.news2.data.model.CategorizedNewsModel;
import com.example.news2.data.model.News;
import com.example.news2.data.model.NewsDbModel;
import com.example.news2.databinding.FragmentCategorizedNewsBinding;
import com.example.news2.ui.adapter.CategorizedNewsAdapter;
import com.example.news2.viewmodel.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

public class CategorizedNewsFragment extends Fragment {

    private FragmentCategorizedNewsBinding binding;

    RecyclerView recyclerView;
    CategorizedNewsAdapter adapter;
    List<NewsDbModel> newsList;

    NewsViewModel viewModel;

    int type;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_categorized_news, container, false);

        if (getArguments() != null) {
            type = getArguments().getInt("type");
        }

        init();
        viewModel = new ViewModelProvider(getActivity()).get(NewsViewModel.class);
        getNews(type);

        return binding.getRoot();
    }

    private void init() {
        recyclerView = binding.recyclerView;
        newsList = new ArrayList<>();
        adapter = new CategorizedNewsAdapter(newsList, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void getNews(int type) {

        switch (type) {
            case 1:
                binding.txtCategory.setText("HEALTH");
                getHealt();
                break;
            case 2:
                binding.txtCategory.setText("SPORTS");
                getSports();
                break;
            case 3:
                binding.txtCategory.setText("BUSINESS");
                getBusiness();
                break;
            default:
                binding.txtCategory.setText("HEALTH");
                getNews(1);
                break;
        }


    }


    private void getHealt() {

        viewModel.getHealthNews().observe(getViewLifecycleOwner(), new Observer<List<NewsDbModel>>() {
            @Override
            public void onChanged(List<NewsDbModel> newsDbModels) {
                newsList.addAll(newsDbModels);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void getSports() {

        viewModel.getSportNews().observe(getViewLifecycleOwner(), new Observer<List<NewsDbModel>>() {
            @Override
            public void onChanged(List<NewsDbModel> newsDbModels) {
                newsList.addAll(newsDbModels);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void getBusiness() {

        viewModel.getBusinessNews().observe(getViewLifecycleOwner(), new Observer<List<NewsDbModel>>() {
            @Override
            public void onChanged(List<NewsDbModel> newsDbModels) {
                newsList.addAll(newsDbModels);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
