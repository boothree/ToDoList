package com.boo3.todolist.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boo3.todolist.R;
import com.boo3.todolist.adapters.RealmRecycleListAdapter;
import com.boo3.todolist.model.Task;

import io.realm.Sort;


public class AllTaskFragment extends CustomFragment  {

    public AllTaskFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String []fieldNames={"status","id"};
        Sort sort[]={Sort.ASCENDING, Sort.ASCENDING};
        setUpRecycleView(new RealmRecycleListAdapter(realm.where(Task.class)
                        .sort(fieldNames,sort)
                        .findAll())
                , getView().findViewById(R.id.list_item));

    }
}
