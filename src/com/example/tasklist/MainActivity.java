package com.example.tasklist;

import java.util.ArrayList;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;


public class MainActivity extends Activity {

	TaskList taskList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskList = TaskList.getInstance();
                
        final ListView lv1 = (ListView) findViewById(R.id.listV_main);
        lv1.setAdapter(new ItemListBaseAdapter(this, taskList.getData()));
        
        lv1.setOnItemClickListener(new OnItemClickListener() {
    
        	public void onItemClick(AdapterView<?> a, View v, int position, long id) { 
        		Object o = lv1.getItemAtPosition(position);
            	TaskDetails obj_itemDetails = (TaskDetails)o;
        		//Toast.makeText(ListViewImagesActivity.this, "You have chosen : " + " " + obj_itemDetails.getName(), Toast.LENGTH_LONG).show();
        	}  
        });
    }

    @Override
	public void onResume() {
        super.onResume();
        taskList = TaskList.getInstance();
        final ListView lv1 = (ListView) findViewById(R.id.listV_main);
        lv1.setAdapter(new ItemListBaseAdapter(this, taskList.getData()));
    }

    
    public void sendMessage(View view) {
    	Intent intent = new Intent(this, CreateTaskActivity.class);
    	startActivity(intent);
    }
    
	public void taskDone(View view){
		
		System.out.println("-------12222222222111111------");
	}
}
