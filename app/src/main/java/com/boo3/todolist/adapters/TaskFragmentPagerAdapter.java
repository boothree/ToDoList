package com.boo3.todolist.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.boo3.todolist.R;
import com.boo3.todolist.fragments.AllTaskFragment;
import com.boo3.todolist.fragments.NewTaskFragment;

public class TaskFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public TaskFragmentPagerAdapter(Context mContext, FragmentManager fm) {
        super(fm);
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NewTaskFragment();
        } else
            return new AllTaskFragment();

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.new_task_only);
            case 1:
                return mContext.getString(R.string.all_task);
            default:
                return null;
        }
    }

}
