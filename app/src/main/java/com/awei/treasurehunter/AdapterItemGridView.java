package com.awei.treasurehunter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class AdapterItemGridView extends BaseAdapter {
    Context context;
    private String imgPath = "http://cr3fp4.azurewebsites.net/uploads/";

    public AdapterItemGridView(Context c) {
        this.context = c;
    }

    @Override
    public int getCount() {
        return Resources.AllItems.size();
    }

    @Override
    public Object getItem(int position) {
        return Resources.AllItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Resources.AllItems.get(position).itemId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.view_main, null);
            ImageView image = (ImageView) row.findViewById(R.id.item_img);
            image.setImageResource(R.drawable.ic_bar_treasure);
            Log.d("IMGPATH",imgPath + Resources.AllItems.get(position).itemId + "A.jpg");
            image.setImageBitmap(Resources.getBitmapFromURL(imgPath + Resources.AllItems.get(position).itemId + "A.jpg"));
            TextView text = (TextView) row.findViewById(R.id.item_text);
            text.setText(Resources.AllItems.get(position).itemName);
        }
        return row;
    }
}
