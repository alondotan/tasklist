package il.ac.shenkar.tasklist;

import java.util.ArrayList;

import com.example.tasklist.R;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ItemListBaseAdapter extends BaseAdapter {
//	private static ArrayList<TaskDetails> itemDetailsrrayList;
	private Context context;
	private TaskList taskList;
	
	private Integer[] imgid = {
			R.drawable.homer,
			R.drawable.barny,
			R.drawable.bart,
			R.drawable.lisa,
			R.drawable.krasty,
			R.drawable.marge,
			R.drawable.abu,
			R.drawable.grampa_abraham,
			R.drawable.flenders,
			R.drawable.berns,
			R.drawable.mo,
			R.drawable.smiters
			};
	
	private LayoutInflater l_Inflater;

	public ItemListBaseAdapter(Context context) {
//		itemDetailsrrayList = results;
		l_Inflater = LayoutInflater.from(context);
		this.context = context;
		this.taskList = TaskList.getInstance(context);
	}

	public int getCount() {
		return taskList.getSize();
	}

	public Object getItem(int position) {
		return taskList.getTask(position);
	}

	public long getItemId(int position) {
		return position;
	}
	
	private final OnClickListener doneButtonOnClickListener = new OnClickListener(){		
		
		public void onClick(View view) {
			int	position = (Integer)view.getTag();
			TaskList.getInstance(context).removeTask(position);
			notifyDataSetChanged();	
		}	
	};

	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			convertView = l_Inflater.inflate(R.layout.item_details_view, null);
			holder = new ViewHolder();
			holder.txt_itemName = (TextView) convertView.findViewById(R.id.name);
			holder.itemImage = (ImageView) convertView.findViewById(R.id.photo);

			holder.doneButton = (Button) convertView.findViewById(R.id.button);
			holder.doneButton.setOnClickListener(doneButtonOnClickListener);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.doneButton.setTag(position);	
		holder.txt_itemName.setText(taskList.getTask(position).getName());
		holder.itemImage.setImageResource(imgid[taskList.getTask(position).getImageNumber() - 1]);
		return convertView;
	}
	
	static class ViewHolder {
		TextView txt_itemName;
		ImageView itemImage;
		Button doneButton;
	}
}
