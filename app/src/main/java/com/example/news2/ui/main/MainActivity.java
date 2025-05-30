package com.example.news2.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.news2.R;
import com.example.news2.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.setActivity(this);




    }


    @BindingAdapter(value = {"android:activity"})
    public static void setBottomNav(BottomNavigationView bottomNav, MainActivity activity) {

        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, new HomeFragment());
        fragmentTransaction.commit();

        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        FragmentTransaction fragmentTransactionHome = activity.getSupportFragmentManager().beginTransaction();
                        fragmentTransactionHome.replace(R.id.frameLayout, new HomeFragment());
                        fragmentTransactionHome.commit();
                        break;
                    case R.id.nav_bookmarks:
                        FragmentTransaction fragmentTransactionSaved = activity.getSupportFragmentManager().beginTransaction();
                        fragmentTransactionSaved.replace(R.id.frameLayout, new SavedNewsFragment());
                        fragmentTransactionSaved.commit();
                        break;
                    case R.id.nav_profile:
                        FragmentTransaction fragmentTransactionProfile = activity.getSupportFragmentManager().beginTransaction();
                        fragmentTransactionProfile.replace(R.id.frameLayout, new ProfileFragment());
                        fragmentTransactionProfile.commit();
                        break;
                }


                return true;
            }
        });

    }
}