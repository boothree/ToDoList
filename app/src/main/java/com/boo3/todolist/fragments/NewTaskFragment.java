package com.boo3.todolist.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boo3.todolist.R;
import com.boo3.todolist.model.Task;
import com.boo3.todolist.adapters.RealmRecycleListAdapter;


public class NewTaskFragment extends CustomFragment {

    public NewTaskFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpRecycleView(new RealmRecycleListAdapter(realm.where(Task.class).equalTo("status", 0)
                        .findAll())
                , getView().findViewById(R.id.list_item));


    }
}
