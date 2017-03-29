package com.awei.treasurehunter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aaa86 on 2017/3/19.
 */

class AdapterIcon extends BaseAdapter {
    private int[] vColor = {R.color.wheat,R.color.mistyrose,R.color.lightyellow,R.color.lightcyan,R.color.lavender};

    Context context;
    ArrayList<String> listFuncs;
    ArrayList<Integer> listIcons;

    AdapterIcon(Context c, ArrayList<String> func, ArrayList<Integer> icons) {
        this.context = c;
        listFuncs = func;
        listIcons = icons;
    }

    @Override
    public int getCount() {
        return listFuncs.size();
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
            row = inflater.inflate(R.layout.view_dialog_class, null);
            ImageView image = (ImageView) row.findViewById(R.id.item_img);
            LinearLayout layoutMain = (LinearLayout)row.findViewById(R.id.layoutMain);
            layoutMain.setBackgroundResource(vColor[(int)(Math.random()*5)]);
            image.setImageResource(listIcons.get(position));
            TextView text = (TextView) row.findViewById(R.id.item_text);
            text.setText(listFuncs.get(position));
        }
        return row;
    }
}
