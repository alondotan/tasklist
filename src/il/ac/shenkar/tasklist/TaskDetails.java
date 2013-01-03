package il.ac.shenkar.tasklist;

import android.text.format.Time;

public class TaskDetails {
	
	private String name ;
	private int imageNumber;
	private int id;
	private Time creationTime;
	private String description;
	
	public TaskDetails(){
		super();
	}
	
	public TaskDetails(String name,int imageNumber,int id,String description){
		super();
		this.name = name;
		this.imageNumber = imageNumber;
		this.id = id;
		this.creationTime = new Time();
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getImageNumber() {
		return imageNumber;
	}
	public void setImageNumber(int imageNumber) {
		this.imageNumber = imageNumber;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Time getTime(){
		return creationTime;
	}
	
	public void setTime(Time time){
		this.creationTime = time;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
