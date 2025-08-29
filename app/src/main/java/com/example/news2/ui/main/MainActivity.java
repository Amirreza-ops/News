package com.example.news2.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.news2.R;
import com.example.news2.databinding.ActivityMainBinding;
import com.example.news2.utils.NetworkReceiver;
import com.example.news2.utils.Refreshable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private NetworkReceiver networkReceiver;
    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.setActivity(this);

        snackbar = Snackbar.make(findViewById(android.R.id.content),
                "You are offline!!", Snackbar.LENGTH_INDEFINITE);

        View view = snackbar.getView();
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
        params.gravity = Gravity.TOP;
        view.setLayoutParams(params);

        networkReceiver = new NetworkReceiver(isConnected -> {
            if (isConnected){
                if (snackbar.isShown()){
                    snackbar.dismiss();


                    Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frameLayout);
                    if (currentFragment instanceof HomeFragment){
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frameLayout, new HomeFragment());
                        fragmentTransaction.commit();
                    }
                }
            }else {
                if (!snackbar.isShown()){
                    snackbar.show();
                }
            }
        });




    }


    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(networkReceiver);
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
                        if(!(activity.getSupportFragmentManager().findFragmentById(R.id.frameLayout) instanceof HomeFragment)){

                            FragmentTransaction fragmentTransactionHome = activity.getSupportFragmentManager().beginTransaction();
                            fragmentTransactionHome.replace(R.id.frameLayout, new HomeFragment());
                            fragmentTransactionHome.commit();
                        }
                        break;
                    case R.id.nav_bookmarks:
                        if (!(activity.getSupportFragmentManager().findFragmentById(R.id.frameLayout) instanceof SavedNewsFragment)) {

                            FragmentTransaction fragmentTransactionSaved = activity.getSupportFragmentManager().beginTransaction();
                            fragmentTransactionSaved.replace(R.id.frameLayout, new SavedNewsFragment());
                            fragmentTransactionSaved.commit();
                        }
                        break;
                    case R.id.nav_profile:
                        if (!(activity.getSupportFragmentManager().findFragmentById(R.id.frameLayout) instanceof ProfileFragment)){

                            FragmentTransaction fragmentTransactionProfile = activity.getSupportFragmentManager().beginTransaction();
                            fragmentTransactionProfile.replace(R.id.frameLayout, new ProfileFragment());
                            fragmentTransactionProfile.commit();
                        }
                        break;
                }


                return true;
            }
        });

    }
}