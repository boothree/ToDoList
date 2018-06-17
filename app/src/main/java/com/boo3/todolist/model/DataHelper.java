package com.boo3.todolist.model;

import android.util.Log;

import java.util.Collection;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class DataHelper {


    public static void addItemAsync(final Realm realm, final String text) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Parent parent = realm.where(Parent.class).findFirst();
                RealmList<Task> items = parent.getTaskList();
                int id = Task.increment();
                Log.e("task_id",String.valueOf(id));
                Task task = realm.createObject(Task.class, id);
                task.setTask(text);
                task.setStatus(0);
                items.add(task);
            }
        });
    }

    public static Task getTask(Realm realm, long id) {
        return realm.where(Task.class).equalTo("id", id).findFirst();
    }


    public static void renameTask(Realm realm, final long id, final String newTaskName) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Task item = getTask(realm, id);
                Log.e("oldTaskName", String.valueOf(id));
                if (item != null) {
                    item.setTask(newTaskName);
                }
            }
        });
    }

    public static void markCompleteItemAsync(Realm realm, final long id) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Task item = getTask(realm, id);
                if (item != null) {
                    item.setStatus(item.getStatus() != 1 ? 1 : 0);
                }
            }
        });
    }


    public static void deleteItemAsync(Realm realm, final long id) {
        Log.e("delete id", String.valueOf(id));
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Task item = getTask(realm, id);
                // Otherwise it has been deleted already.
                if (item != null) {
                    item.deleteFromRealm();
                }
            }
        });
    }

    public static void deleteItemsAsync(Realm realm, Collection<Integer> ids) {
        // Create an new array to avoid concurrency problem.
        final Integer[] idsToDelete = new Integer[ids.size()];
        ids.toArray(idsToDelete);
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (int st : idsToDelete) {
                    Task item = getTask(realm, st);
                    // Otherwise it has been deleted already.
                    if (item != null) {
                        item.deleteFromRealm();
                    }
                }
            }
        });
    }
}

