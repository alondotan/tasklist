package il.ac.shenkar.tasklist;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ReminderBroadCastReceiver extends BroadcastReceiver {
	public void onReceive(Context context,Intent intent){
		
		Intent myIntent	= new Intent(context,MainActivity.class);
		PendingIntent pendingIntent	= PendingIntent.getActivity(context,0,myIntent,0);	
		NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);	
											
        String message = intent.getStringExtra(CreateTaskActivity.EXTRA_TASKNAME);
        
		Notification notification = new Notification (R.drawable.ic_launcher,"You got new task to handle",System.currentTimeMillis());	
		notification.setLatestEventInfo(context,"The Time is UP","It's time for "+message, pendingIntent);	
		notificationManager.notify(0,notification);	//0	is	id	
	}	
}	