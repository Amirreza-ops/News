package com.example.news2.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news2.R;
import com.example.news2.data.model.NewsDbModel;
import com.example.news2.databinding.FragmentSavedNewsBinding;
import com.example.news2.ui.adapter.SavedNewsAdapter;
import com.example.news2.viewmodel.SavedNewsViewModel;

import java.util.ArrayList;
import java.util.List;

public class SavedNewsFragment extends Fragment {

    private FragmentSavedNewsBinding binding;

    private SavedNewsViewModel viewModel;
    RecyclerView recyclerView;
    SavedNewsAdapter adapter;
    List<NewsDbModel> news;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_saved_news, container, false);
        init();
        viewModel = new ViewModelProvider(getActivity()).get(SavedNewsViewModel.class);
        getSavedNews();

        return binding.getRoot();
    }


    private void init(){
        recyclerView = binding.recyclerView;
        news = new ArrayList<>();
        adapter = new SavedNewsAdapter(getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }


    private void getSavedNews(){

        viewModel.getSavedNews().observe(getViewLifecycleOwner(), new Observer<List<NewsDbModel>>() {
            @Override
            public void onChanged(List<NewsDbModel> newsDbModels) {
                if (newsDbModels.size() > 0){
                    binding.NoSavedTxt.setVisibility(View.GONE);
                }else {
                    binding.NoSavedTxt.setVisibility(View.VISIBLE);
                }
                adapter.setList(newsDbModels);
            }
        });
    }
}
