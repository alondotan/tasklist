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

import android.widget.EditText;

public class RandomTaskReciver {
	TaskDetails getRandomTask(){
		TaskDetails task = new TaskDetails();
		
		String response;
		response = "";
		try {
			URL url	= new URL("http://mobile1-tasks-dispatcher.herokuapp.com/task/random");
			HttpURLConnection urlConnection	= (HttpURLConnection) url.openConnection();	
			InputStream in = new BufferedInputStream (urlConnection.getInputStream());	
			InputStreamReader inReader = new InputStreamReader(in);	
			BufferedReader bufferedReader =	new	BufferedReader(inReader);	
			StringBuilder responseBuilder = new	StringBuilder();	
			for	(String	line=bufferedReader.readLine();	line!=null;	line=bufferedReader.readLine()){	
				responseBuilder.append(line);	
			}	
			response = responseBuilder.toString();

    		try {
    			JSONObject jsonResponse	= new JSONObject(response);
    			task.setName(jsonResponse.getString("topic"));

    	    	task.setDescription(jsonResponse.getString("description"));
    	
    		} catch (JSONException e) {
    			e.printStackTrace();
    		}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		return task;
	}
}
