package il.ac.shenkar.tasklist;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import il.ac.shenkar.tasklist.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
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
    
    public void randomTask(View view) {
    	new	GetFromWebTask().execute();
    }
    
    
    
    public class GetFromWebTask extends AsyncTask<URL,Integer,TaskDetails>{	
		
    	@Override	
    	protected TaskDetails doInBackground(URL...urls)	{
    		RandomTaskReciver taskReciver = new RandomTaskReciver();
    		
    		return (taskReciver.getRandomTask());
    		
    	}	
    	
    	protected void onPostExecute(TaskDetails task){

	    	EditText nameText = (EditText) findViewById(R.id.editTextName);
	    	nameText.setText(task.getName());
	    	
	    	EditText descriptionText = (EditText) findViewById(R.id.editTextDesc);
	    	descriptionText.setText(task.getDescription());
    		
    	}	
    }
    
}
