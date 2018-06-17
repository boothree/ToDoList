package com.boo3.todolist.model;

import android.util.Log;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Task extends RealmObject {
    @PrimaryKey
    private int id;
    @Required
    private String task;

    //Complete, non complete task status for history
    //0 - active task
    //1 - complete task
    private int status;
    private static AtomicInteger INTEGER_COUNTER = new AtomicInteger(0);


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static int increment() {
        Log.e("key1", String.valueOf(INTEGER_COUNTER.get()));
        if (INTEGER_COUNTER.get() == 0) {
            Realm realm = Realm.getDefaultInstance();
            Number maxId = realm.where(Task.class).max("id");
            int nextId = (maxId == null) ? 1 : maxId.intValue() + 1;
            Log.e("key2", String.valueOf(nextId));
            INTEGER_COUNTER.set(nextId);
        }
        Log.e("key4", String.valueOf(INTEGER_COUNTER.get()));
        return INTEGER_COUNTER.getAndIncrement();
    }


}
