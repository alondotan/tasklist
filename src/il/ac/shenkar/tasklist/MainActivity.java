package il.ac.shenkar.tasklist;

import il.ac.shenkar.tasklist.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;



import android.app.Activity;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
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

	public static final String EXTRA_TASKNAME = "il.ac.shenkar.taskList.TASKNAME";
	
	 private static final String[] GROCERIES = new String[] {
         "חלב", "ביצים", "חלב סויה", "גבינה צהובה", "עגבניות"
     };
	 
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	int DAY_IN_MILLI = 86400000;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		System.out.println("in here2");
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
        		System.out.println("----------- ++++++++++ 000000000000000");
        		Object o = lv1.getItemAtPosition(position);
            	TaskDetails obj_itemDetails = (TaskDetails)o;
            	System.out.println(obj_itemDetails.getName() + " - " + obj_itemDetails.getDescription());
        		//Toast.makeText(MainActivity.this, "You have chosen : " + " " + obj_itemDetails.getName(), Toast.LENGTH_LONG).show();
        	}  
        });
        
		System.out.println("in here1");

		Intent intent =	new	Intent(this, GetDailyTaskService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this,1110,intent,0);
		AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

		
		Calendar cur_cal = new GregorianCalendar();
		cur_cal.setTimeInMillis(System.currentTimeMillis());//set the current time and date for this calendar

		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.DAY_OF_YEAR, cur_cal.get(Calendar.DAY_OF_YEAR));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, cur_cal.get(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cur_cal.get(Calendar.MILLISECOND));
		cal.set(Calendar.DATE, cur_cal.get(Calendar.DATE));
		cal.set(Calendar.MONTH, cur_cal.get(Calendar.MONTH));


		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),DAY_IN_MILLI, pendingIntent);	
		
    }

    final void doSomthing(){
    }
    @Override
	public void onResume() {
        super.onResume();
        taskList = TaskList.getInstance(this);
        final ListView lv1 = (ListView) findViewById(R.id.listV_main);
    	EditText nameText = (EditText) findViewById(R.id.editTextName);
    	nameText.setText("");
    	
        lv1.setAdapter(new ItemListBaseAdapter(this));
    }
    
    public void createNewTask(View view) {
    	Intent intent = new Intent(this, CreateTaskActivity.class);
    	EditText editText = (EditText) findViewById(R.id.editTextName);
    	
    	String taskName = editText.getText().toString();
    	
    	intent.putExtra(EXTRA_TASKNAME, taskName);
    	startActivity(intent);
    }
    
}
