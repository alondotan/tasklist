package il.ac.shenkar.tasklist;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
	 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 3;
 
    // Database Name
    private static final String DATABASE_NAME = "tasksManager";
 
    // tasks table name
    private static final String TABLE_TASKS = "tasks";
 
    // tasks Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_IMG_NO = "image_number";
    private static final String KEY_DESC = "description";
    
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TASKS_TABLE = "CREATE TABLE " + TABLE_TASKS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_IMG_NO + " INTEGER," + KEY_DESC + " TEXT " + ")";
        db.execSQL(CREATE_TASKS_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
 
        // Create tables again
        onCreate(db);
    }
    
    public void addTask(TaskDetails task) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues  values = new ContentValues ();
        values.put(KEY_NAME, task.getName()); // task Name
        values.put(KEY_IMG_NO, task.getImageNumber()); // task image Number
        values.put(KEY_DESC, task.getDescription()); // task Name
        
        // Inserting Row
        db.insert(TABLE_TASKS, null, values);
        db.close(); // Closing database connection
    }
    
    public TaskDetails getTask(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
     
        Cursor cursor = db.query(TABLE_TASKS, new String[] {KEY_IMG_NO ,
                KEY_NAME, KEY_ID ,KEY_DESC }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
     
        TaskDetails task = new TaskDetails(cursor.getString(1),
        		Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(2)),cursor.getString(3));
        // return task
        return task;
    }
    
    public ArrayList<TaskDetails> getAllTasks() {
    	ArrayList<TaskDetails> tasksList = new ArrayList<TaskDetails>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TASKS;
     
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
     
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	TaskDetails task = new TaskDetails();
            	task.setId(Integer.parseInt(cursor.getString(0)));
            	task.setName(cursor.getString(1));
            	task.setImageNumber(Integer.parseInt(cursor.getString(2)));
               	task.setDescription(cursor.getString(3));
                // Adding task to list
            	tasksList.add(task);
            } while (cursor.moveToNext());
        }
     
        // return tasks list
        return tasksList;
    }
    
    public void deleteTask(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASKS, KEY_ID + " = ?",
                new String[] { String.valueOf(id)});
        db.close();
    }
    
}