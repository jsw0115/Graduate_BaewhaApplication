package org.tchtown.baewhaaplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.tchtown.baewhaaplication.databinding.ActivityMealBinding;

public class MealActivity extends AppCompatActivity {

    private ActivityMealBinding binding;

    LunchProFragment lunchproFragment;
    LunchStuFragment lunchStuFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMealBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 버튼 실행
        final Button breakfastButton = (Button)findViewById(R.id.Break_menu_Stu_Button);
        final Button lunchproButton = (Button)findViewById(R.id.lunch_menu_Pro_Button);
        final Button lunchstuButton = (Button)findViewById(R.id.lunch_menu_Stu_Button);

        breakfastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // notice.setVisibility(View.GONE);
                breakfastButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                lunchproButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                lunchstuButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                // calendarButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new CourseFragment());
                fragmentTransaction.commit();

            }
        });

        lunchproButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // notice.setVisibility(View.GONE);
                breakfastButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                lunchproButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                lunchstuButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                // calendarButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new CourseFragment());
                fragmentTransaction.commit();
            }
        });

        lunchstuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // notice.setVisibility(View.GONE);
                breakfastButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                lunchproButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                lunchstuButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                // calendarButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new CourseFragment());
                fragmentTransaction.commit();
            }
        });

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_view);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }



}