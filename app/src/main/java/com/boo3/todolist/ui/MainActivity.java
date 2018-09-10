package com.boo3.todolist.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.boo3.todolist.AddTaskActivity;
import com.boo3.todolist.R;
import com.boo3.todolist.adapters.TaskFragmentPagerAdapter;


public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ActivityLoginTestBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        //binding.setUser(UserFirebase.getInstance());

        //mDrawerLayout = findViewById(R.id.drawer_layout);


        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = findViewById(R.id.view_pager);

        // Create an adapter that knows which fragment should be shown on each page
        TaskFragmentPagerAdapter adapter = new TaskFragmentPagerAdapter(this, getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), AddTaskActivity.class);
                startActivity(i);

            }
        });
    }

}
