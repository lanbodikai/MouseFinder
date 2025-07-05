package com.example.mousefinder;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.mousefinder.fragments.HomeFragment;
import com.example.mousefinder.fragments.MouseDatabaseFragment;
import com.example.mousefinder.fragments.MouseRatingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView nav = findViewById(R.id.bottom_nav);
        // Default fragment
        loadFragment(new HomeFragment());

        nav.setOnItemSelectedListener(item -> {
            Fragment selected;
            switch (item.getItemId()) {
                case R.id.nav_database:
                    selected = new MouseDatabaseFragment();
                    break;
                case R.id.nav_rating:
                    selected = new MouseRatingFragment();
                    break;
                default:
                    selected = new HomeFragment();
            }
            return loadFragment(selected);
        });
    }

    private boolean loadFragment(Fragment frag) {
        if (frag == null) return false;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.home_fragment_container, frag)
                .commit();
        return true;
    }
}
