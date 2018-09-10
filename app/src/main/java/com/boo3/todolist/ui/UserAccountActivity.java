package com.boo3.todolist.ui;


import android.databinding.DataBindingUtil;
import android.os.Bundle;

import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.boo3.todolist.R;
import com.boo3.todolist.databinding.UserAccount;
import com.boo3.todolist.model.UserFirebase;


public class UserAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        UserAccount binding = DataBindingUtil.setContentView(this, R.layout.content_user_account);


        binding.setUser(UserFirebase.getInstance());

    }


}