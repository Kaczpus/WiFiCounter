package com.example.kacper.wificounter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Hunt on 2015-01-27.
 */
public class HistoryListAdapter extends ArrayAdapter<History> {

    public HistoryListAdapter(Context context, ArrayList<History> data)
    {
        super(context, R.layout.history_row_layout, data);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
       //
        LayoutInflater myInflater = LayoutInflater.from(getContext());
        //convertView = LayoutInflater.from(getContext()).inflate(R.layout.history_row_layout,parent,false);
        View customView = myInflater.inflate(R.layout.history_row_layout,parent,false);
        History history = getItem(position);
        TextView ConDateTime = (TextView) customView.findViewById(R.id.ConDateTimeText);
        TextView ElapsedTime = (TextView) customView.findViewById(R.id.timeElapsedText);

        ConDateTime.setText(history.getDate());
        ElapsedTime.setText(history.getTime_connection());

        return customView;
    }
}
