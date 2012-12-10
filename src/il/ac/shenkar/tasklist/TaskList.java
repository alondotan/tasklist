package il.ac.shenkar.tasklist;

import java.util.ArrayList;

import android.content.Context;

public class TaskList {	
	private	static TaskList	instance = null;	
	private	Context	context;
	
	private	 ArrayList<TaskDetails> tasksDetails;

	private	DatabaseHandler db;
	
	private	TaskList(Context context)	{
		this.context = context;
		db = new DatabaseHandler(context);
        this.tasksDetails = db.getAllTasks();
	}
		
	public static TaskList getInstance(Context context)	{	
		if(instance	== null) {	
			instance = new TaskList(context);	
		}
		return instance;
	}
		
	public void addTask(TaskDetails t){
		tasksDetails.add(t);
		db.addTask(t);
	}
	
	public void removeTask(int i){
		tasksDetails.remove(i);
		db.deleteTask(i);
	}
	
	public TaskDetails getTask(int i){
		return tasksDetails.get(i);
	}
	
	public int getSize(){
		return tasksDetails.size();
	}
}