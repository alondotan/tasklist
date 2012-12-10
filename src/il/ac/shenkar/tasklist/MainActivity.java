package il.ac.shenkar.tasklist;

import java.util.ArrayList;

import com.example.tasklist.R;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends Activity {

	TaskList taskList;

	 private static final String[] GROCERIES = new String[] {
         "חלב", "ביצים", "חלב סויה", "גבינה צהובה", "עגבניות"
     };
	 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, GROCERIES);
        
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.editTextName);
        textView.setAdapter(adapter);
        
        
        taskList = TaskList.getInstance(this);
                
        final ListView lv1 = (ListView) findViewById(R.id.listV_main);
        lv1.setAdapter(new ItemListBaseAdapter(this));
        
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
        taskList = TaskList.getInstance(this);
        final ListView lv1 = (ListView) findViewById(R.id.listV_main);
        lv1.setAdapter(new ItemListBaseAdapter(this));
    }

    public void createTask(View view) {
    	TaskList taskList = TaskList.getInstance(this);
    	TaskDetails t = new TaskDetails();
    	EditText nameText = (EditText) findViewById(R.id.editTextName);
    	t.setName(nameText.getText().toString());
    	t.setImageNumber((int)(Math.random()*10)+1);
    	taskList.addTask(t);
    	final ListView lv1 = (ListView) findViewById(R.id.listV_main);
        lv1.setAdapter(new ItemListBaseAdapter(this));
    }
}
