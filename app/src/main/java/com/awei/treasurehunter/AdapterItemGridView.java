package com.awei.treasurehunter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.awei.info.Item;

import java.util.ArrayList;

/**
 * Created by aaa86 on 2017/3/22.
 */

class AdapterItemGridView extends BaseAdapter {
    Context context;
    ArrayList<Item> listItem = new ArrayList<>();

    public AdapterItemGridView(Context c) {
        this.context = c;
        listItem = DBController.queryAllItem();
    }

    @Override
    public int getCount() {
        return listItem.size();
    }

    @Override
    public Object getItem(int position) {
        return listItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listItem.get(position).itemId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        /*Bitmap bitmap = BitmapFactory.decodeByteArray(
                ActMain.listIcons.get(position),0,ActMain.listIcons.get(position).length);*/
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.view_main, null);
            ImageView image = (ImageView) row.findViewById(R.id.item_img);
            image.setImageResource(R.drawable.ic_bar_treasure);
            TextView text = (TextView) row.findViewById(R.id.item_text);
            text.setText(listItem.get(position).itemName);
        }
        return row;
    }
}
