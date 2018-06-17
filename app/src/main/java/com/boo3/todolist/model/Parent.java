package com.boo3.todolist.model;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Parent extends RealmObject {
    @SuppressWarnings("unused")
    private RealmList<Task> taskList;

    public RealmList<Task> getTaskList() {
        return taskList;
    }

}