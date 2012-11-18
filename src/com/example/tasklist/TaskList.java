package com.example.tasklist;

import java.util.ArrayList;

public class TaskList {	
	private	static TaskList	instance = null;	
		
	private	 ArrayList<TaskDetails> tasksDetails;// = new ArrayList<TaskDetails>();
	
	private	TaskList()	{	
				this.tasksDetails = new ArrayList<TaskDetails>();
	}
		
	public static TaskList getInstance()	{	
		if(instance	== null) {	
			instance = new TaskList();	
		}
		return instance;
	}
	
	public ArrayList<TaskDetails> getData(){
		return this.tasksDetails;
	}
	
	public void addTask(TaskDetails t){
		tasksDetails.add(t);
	}
	
	public void removeTask(int i){
		tasksDetails.remove(i);
	}
}