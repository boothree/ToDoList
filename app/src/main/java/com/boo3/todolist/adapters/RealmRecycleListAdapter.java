package com.boo3.todolist.adapters;


import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.boo3.todolist.R;
import com.boo3.todolist.model.Task;

import java.util.HashSet;
import java.util.Set;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class RealmRecycleListAdapter extends RealmRecyclerViewAdapter<Task, RealmRecycleListAdapter.MyViewHolder> {

    private boolean inDeletionMode = false;
    private boolean isMarkAll = false;
    private Set<Integer> countersToDelete = new HashSet<>();
    private ClickListener clickListener;
    private CheckListener checkListener;


    public RealmRecycleListAdapter(@Nullable OrderedRealmCollection<Task> data) {
        super(data, true);
        setHasStableIds(true);
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setCheckListener(CheckListener checkListener) {
        this.checkListener = checkListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Task obj = getItem(position);
        final int itemId = obj.getId();

        switch (obj.getStatus()) {
            case 0:
                holder.textView.setPaintFlags(Paint.ANTI_ALIAS_FLAG);
                holder.textView.setTypeface(null, Typeface.NORMAL);
                break;
            case 1:
                holder.textView.setPaintFlags(holder.textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                holder.textView.setTypeface(holder.textView.getTypeface(), Typeface.ITALIC);
                break;
            default:
                break;
        }

        holder.textView.setText(obj.getTask());

        if (!isMarkAll) {
            countersToDelete.clear();
            holder.checkBox.setChecked(false);
        } else holder.checkBox.setChecked(true);

        if (inDeletionMode) {
            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        countersToDelete.add(itemId);
                    } else {
                        countersToDelete.remove(itemId);
                    }
                }
            });
        } else {
            holder.checkBox.setOnCheckedChangeListener(null);
        }
        holder.checkBox.setVisibility(inDeletionMode ? View.VISIBLE : View.GONE);
    }


    public void enableDeletionMode(boolean flag) {
        inDeletionMode = flag;
        if (!inDeletionMode) {
            countersToDelete.clear();
        }
        notifyDataSetChanged();
    }


    public Set<Integer> getCountersToDelete() {
        return countersToDelete;
    }

    public boolean isMarkAll() {
        return isMarkAll;
    }

    public void setMarkAll(boolean markAll) {
        isMarkAll = markAll;
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,RadioButton.OnCheckedChangeListener {
        TextView textView;
        CheckBox checkBox;
        RadioButton radioButton;
        View view;
        public Task data;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.textView);
            this.checkBox = itemView.findViewById(R.id.delete_checkBox);
            this.radioButton = itemView.findViewById(R.id.radioButton);
            this.view = itemView;
            itemView.setOnClickListener(this);
            radioButton.setOnCheckedChangeListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null)
                clickListener.onItemClick(itemView, getItem(getAdapterPosition()).getId());
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (checkListener != null){
                if (!radioButton.isChecked() && isChecked) {
                    radioButton.setChecked(true);
                } else radioButton.setChecked(false);
                checkListener.onCheck(itemView,getItem(getAdapterPosition()).getId());
            }

        }
    }
}
