package il.ac.shenkar.tasklist;

import android.app.IntentService;
import android.content.Intent;

public class GetDailyTaskService extends IntentService {
	public GetDailyTaskService() {super("GetDailyTaskService");}

	
	@Override	
	protected void onHandleIntent(Intent intent) {
		RandomTaskReciver taskReciver = new RandomTaskReciver();
		TaskDetails task = taskReciver.getRandomTask();
    	task.setImageNumber((int)(Math.random()*10)+1);

    	TaskList taskList = TaskList.getInstance(this);

		System.out.println("----------!!!!!!!!!-------");
		System.out.print(task.getName());
    	taskList.addTask(task);
		System.out.println("----------!!!!!!!!!-------");
	}
}