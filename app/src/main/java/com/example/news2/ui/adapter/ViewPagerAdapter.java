package com.example.news2.ui.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.news2.ui.details.CategorizedNewsFragment;
import com.example.news2.ui.details.HomeNewsFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        Fragment fragment = new CategorizedNewsFragment();
        Bundle bundle = new Bundle();

        switch (position) {
            case 0:
                return new HomeNewsFragment();
            case 1:
                bundle.putInt("type", 1);
                fragment.setArguments(bundle);
                return fragment;
            case 2:
                bundle.putInt("type", 2);
                fragment.setArguments(bundle);
                return fragment;
            case 3:
                bundle.putInt("type", 3);
                fragment.setArguments(bundle);
                return fragment;

            default:
                return new HomeNewsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
