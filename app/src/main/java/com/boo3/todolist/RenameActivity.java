package com.boo3.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.boo3.todolist.model.DataHelper;

import io.realm.Realm;

public class RenameActivity extends AppCompatActivity {
    EditText mEditText;
    private Realm realm;
    private Integer id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rename_item);
        realm = Realm.getDefaultInstance();
        mEditText = findViewById(R.id.rename_edit_text);
        Intent i = getIntent();
        id = i.getIntExtra("id",-1);
        Log.e("getId",String.valueOf(id));
        findViewById(R.id.rename_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                renameTask(mEditText.getText().toString());
            }
        });

    }

    public void renameTask(String task) {
        if (task == null || task.length() == 0 | id == -1) {
            Toast
                    .makeText(this, "Пустую задачу нельзя добавить!", Toast.LENGTH_SHORT)
                    .show();
            return;
        }
            DataHelper.renameTask(realm,id,task);
            finish();

        }
    }

