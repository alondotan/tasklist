package il.ac.shenkar.tasklist;

import il.ac.shenkar.tasklist.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class CreateTaskActivity extends Activity {

	public static final String EXTRA_TASKNAME = "il.ac.shenkar.taskList.TASKNAME";

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_TASKNAME);
    	EditText nameText = (EditText) findViewById(R.id.editTextName);
    	nameText.setText(message);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_create_task, menu);
        return true;
    }
    
    public void createTask(View view) {
    	TaskList taskList = TaskList.getInstance(this);
    	TaskDetails t = new TaskDetails();
    	EditText nameText = (EditText) findViewById(R.id.editTextName);
    	t.setName(nameText.getText().toString());
    	t.setImageNumber((int)(Math.random()*10)+1);
    	taskList.addTask(t);
 //   	final ListView lv1 = (ListView) findViewById(R.id.listV_main);
    	
    	EditText reminder = (EditText) findViewById(R.id.editTextReminder);
    	if (reminder.getText().toString() != "")
    	{
    		Intent intent = new	Intent("il.ac.shenkar.tasklist.reminder_broadcast");
			intent.putExtra(EXTRA_TASKNAME, t.getName());
			
			PendingIntent pendingIntent	= PendingIntent.getBroadcast(this,0,intent,0);		
			AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

			alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 20000, pendingIntent);	
    	}
//        lv1.setAdapter(new ItemListBaseAdapter(this));
    	finish();
    }
    
}
