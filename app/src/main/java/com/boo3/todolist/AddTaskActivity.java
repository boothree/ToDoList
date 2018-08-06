package com.boo3.todolist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.boo3.todolist.model.DataHelper;

import io.realm.Realm;

public class AddTaskActivity extends AppCompatActivity {
    EditText mEditText;
    private Realm realm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_item);
        realm = Realm.getDefaultInstance();
        mEditText = findViewById(R.id.editText);
        findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask(mEditText.getText().toString());
                finish();
            }
        });

    }

    public void addTask(String task) {
        if (task == null || task.length() == 0) {
            Toast
                    .makeText(this, "Пустую задачу нельзя добавить!", Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        DataHelper.addItemAsync(realm, task);
    }
}
