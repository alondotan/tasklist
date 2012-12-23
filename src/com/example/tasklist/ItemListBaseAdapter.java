package com.example.tasklist;

import java.util.ArrayList;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ItemListBaseAdapter extends BaseAdapter {
	private static ArrayList<TaskDetails> itemDetailsrrayList;
		
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

	public ItemListBaseAdapter(Context context, ArrayList<TaskDetails> results) {
		itemDetailsrrayList = results;
		l_Inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return itemDetailsrrayList.size();
	}

	public Object getItem(int position) {
		return itemDetailsrrayList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		final int pos = position;
		if (convertView == null) {
			convertView = l_Inflater.inflate(R.layout.item_details_view, null);
			holder = new ViewHolder();
			holder.txt_itemName = (TextView) convertView.findViewById(R.id.name);
			holder.itemImage = (ImageView) convertView.findViewById(R.id.photo);
			holder.doneButton = (Button) convertView.findViewById(R.id.button);
			
			holder.doneButton.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					TaskList.getInstance().removeTask(pos);
					notifyDataSetChanged();
				}
			});
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.txt_itemName.setText(itemDetailsrrayList.get(position).getName());
		holder.itemImage.setImageResource(imgid[itemDetailsrrayList.get(position).getImageNumber() - 1]);
		return convertView;
	}
	
	static class ViewHolder {
		TextView txt_itemName;
		ImageView itemImage;
		Button doneButton;
	}
}
