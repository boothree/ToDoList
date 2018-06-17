package com.boo3.todolist.adapters;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.boo3.todolist.model.DataHelper;

import io.realm.Realm;

public class TouchHelperCallback extends ItemTouchHelper.SimpleCallback{
    private Realm realm = Realm.getDefaultInstance();


    public TouchHelperCallback() {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
        DataHelper.deleteItemAsync(realm,viewHolder.getItemId()); // for delete
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

}
