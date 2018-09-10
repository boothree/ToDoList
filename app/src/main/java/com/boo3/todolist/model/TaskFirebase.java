package com.boo3.todolist.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class TaskFirebase {
    public int idTask;
    public String bodyTask;
    public int statusTask;

    public TaskFirebase() {
    }

    public TaskFirebase(int idTask, String bodyTask, int statusTask) {
        this.idTask = idTask;
        this.bodyTask = bodyTask;
        this.statusTask = statusTask;
    }
}
