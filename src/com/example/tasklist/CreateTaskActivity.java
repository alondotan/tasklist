package com.example.tasklist;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

public class CreateTaskActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_create_task, menu);
        return true;
    }
    
    public void createTask(View view) {
    	TaskList taskList = TaskList.getInstance();
    	TaskDetails t = new TaskDetails();
    	EditText nameText = (EditText) findViewById(R.id.editTextName);
    	t.setName(nameText.getText().toString());
    	t.setImageNumber(2);//(int)(Math.random()*10));
    	taskList.addTask(t);
    	finish();
    }
}
