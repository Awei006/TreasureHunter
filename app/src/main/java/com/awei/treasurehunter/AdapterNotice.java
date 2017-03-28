package com.awei.treasurehunter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class AdapterNotice extends BaseAdapter {
    Context context;
    ArrayList<String> listContent;
    ArrayList<String> listTime;
    ArrayList<Integer> listIcons;

    AdapterNotice(Context c, ArrayList<Integer> icons, ArrayList<String> content, ArrayList<String> time) {
        this.context = c;
        listIcons = icons;
        listContent = content;
        listTime = time;
    }

    @Override
    public int getCount() {
        return listTime.size();
    }

    @Override
    public Object getItem(int position) {
        return listIcons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listIcons.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.view_notic, null);
            ImageView image = (ImageView) row.findViewById(R.id.title_img);
            image.setImageResource(listIcons.get(position));
            TextView txtContent = (TextView) row.findViewById(R.id.txtContent);
            txtContent.setText(listContent.get(position));
            TextView txtTime = (TextView) row.findViewById(R.id.txtTime);
            txtTime.setText(listTime.get(position));

        }
        return row;
    }
}
