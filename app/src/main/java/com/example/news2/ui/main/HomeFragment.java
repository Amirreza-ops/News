package com.example.news2.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.news2.R;
import com.example.news2.databinding.FragmentHomeBinding;
import com.example.news2.ui.adapter.ViewPagerAdapter;
import com.example.news2.ui.details.CategorizedNewsFragment;
import com.example.news2.ui.details.HomeNewsFragment;
import com.example.news2.utils.Refreshable;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment implements Refreshable {

    private FragmentHomeBinding binding;
    TabLayout tabLayout;
    ViewPager2 viewPager;
    ViewPagerAdapter viewPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        init();


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private void init() {
        tabLayout = binding.tabLayout;
        viewPager = binding.viewPager;
        viewPager.setUserInputEnabled(false);
        viewPagerAdapter = new ViewPagerAdapter(getActivity());
        viewPager.setAdapter(viewPagerAdapter);
    }

    @Override
    public void refreshData() {
        int currentItem = viewPager.getCurrentItem();
        Fragment page = getActivity().getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewPager + ":" + viewPager.getCurrentItem());
        Fragment currentChild;
        if (currentItem == 0 ){
            currentChild = new HomeNewsFragment();
        } else {
            currentChild = new CategorizedNewsFragment();
        }
        if (currentChild instanceof Refreshable){
            ((Refreshable) currentChild).refreshData();
        }
    }
}
