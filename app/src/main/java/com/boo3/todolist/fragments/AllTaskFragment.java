package com.boo3.todolist.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boo3.todolist.R;
import com.boo3.todolist.adapters.RealmRecycleListAdapter;
import com.boo3.todolist.model.Task;


public class AllTaskFragment extends CustomFragment  {

    public AllTaskFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_page, container, false);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpRecycleView(new RealmRecycleListAdapter(realm.where(Task.class).sort("status").findAll())
                , getView().findViewById(R.id.list_item));

    }
}
