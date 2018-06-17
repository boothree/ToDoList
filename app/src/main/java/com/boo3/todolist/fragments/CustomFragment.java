package com.boo3.todolist.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.boo3.todolist.R;
import com.boo3.todolist.RenameActivity;
import com.boo3.todolist.adapters.CheckListener;
import com.boo3.todolist.adapters.ClickListener;
import com.boo3.todolist.model.DataHelper;
import com.boo3.todolist.adapters.RealmRecycleListAdapter;
import com.boo3.todolist.adapters.TouchHelperCallback;

import io.realm.Realm;

public class CustomFragment extends Fragment {
    public Menu menu;
    public RealmRecycleListAdapter mAdapter;
    public RecyclerView mRecyclerView;
    public Realm realm = Realm.getDefaultInstance();
    public RadioButton radioButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        this.menu = menu;
        inflater.inflate(R.menu.delete_items, menu);
        menu.setGroupVisible(R.id.start_delete_mode, true);
        menu.setGroupVisible(R.id.delete_mode, false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_start_delete_mode:
                mAdapter.enableDeletionMode(true);
                menu.setGroupVisible(R.id.delete_mode, true);
                menu.setGroupVisible(R.id.start_delete_mode, false);
                return true;
            case R.id.action_end_delete_mode:
                DataHelper.deleteItemsAsync(realm, mAdapter.getCountersToDelete()); //for delete
                // Fall through
            case R.id.action_cancel_delete_mode:
                mAdapter.enableDeletionMode(false);
                mAdapter.setMarkAll(false);
                menu.setGroupVisible(R.id.delete_mode, false);
                menu.setGroupVisible(R.id.start_delete_mode, true);
                return true;
            case R.id.action_mark_all_delete:
                mAdapter.enableDeletionMode(true);
                mAdapter.setMarkAll(mAdapter.isMarkAll() ? false : true);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setUpRecycleView(RealmRecycleListAdapter mAdapter, View recyclerView) {
        this.mAdapter = mAdapter;
        this.mRecyclerView = (RecyclerView) recyclerView;
        ClickListener clickListener = new ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent i = new Intent(getContext(), RenameActivity.class);
                i.putExtra("id",position);
                startActivity(i);
            }
        };
        CheckListener checkListener = new CheckListener() {
            @Override
            public void onCheck(View view, int position) {
                DataHelper.markCompleteItemAsync(realm,position);
            }
        };
        mAdapter.setClickListener(clickListener);
        mAdapter.setCheckListener(checkListener);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));



        TouchHelperCallback touchHelperCallback = new TouchHelperCallback();
        ItemTouchHelper touchHelper = new ItemTouchHelper(touchHelperCallback);
        touchHelper.attachToRecyclerView(mRecyclerView);
    }

}
