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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ItemDetails> image_details = GetSearchResults();
        
        final ListView lv1 = (ListView) findViewById(R.id.listV_main);
        lv1.setAdapter(new ItemListBaseAdapter(this, image_details));
        
        lv1.setOnItemClickListener(new OnItemClickListener() {
    
        	public void onItemClick(AdapterView<?> a, View v, int position, long id) { 
        		Object o = lv1.getItemAtPosition(position);
            	ItemDetails obj_itemDetails = (ItemDetails)o;
        		//Toast.makeText(ListViewImagesActivity.this, "You have chosen : " + " " + obj_itemDetails.getName(), Toast.LENGTH_LONG).show();
        	}  
        });
    }

    private ArrayList<ItemDetails> GetSearchResults(){
    	ArrayList<ItemDetails> results = new ArrayList<ItemDetails>();
    	
    	ItemDetails item_details = new ItemDetails();
    	item_details.setName("homer");
    	item_details.setImageNumber(1);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName("barny");
    	item_details.setImageNumber(2);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName("bart");
    	item_details.setImageNumber(3);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName("lisa");
    	item_details.setImageNumber(4);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName("krasty");
    	item_details.setImageNumber(5);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName("marge");
    	item_details.setImageNumber(6);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName("abu");
    	item_details.setImageNumber(7);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName("grampa abraham");
    	item_details.setImageNumber(8);
    	results.add(item_details);    	

    	item_details = new ItemDetails();
    	item_details.setName("flenders");
    	item_details.setImageNumber(9);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName("berns");
    	item_details.setImageNumber(10);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName("mo");
    	item_details.setImageNumber(11);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName("smiters");
    	item_details.setImageNumber(12);
    	results.add(item_details);    	
    	
    	
    	return results;
    }
    
    public void sendMessage(View view) {
    	Intent intent = new Intent(this, CreateTaskActivity.class);
    	startActivity(intent);
    }
}
