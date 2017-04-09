package com.awei.treasurehunter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.awei.info.Item;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

class AdapterItemGridView extends BaseAdapter {
    Context context;
    ArrayList<Item> listItem = new ArrayList<>();
    private String imgPath = "http://cr3fp4.azurewebsites.net/uploads/";

    public AdapterItemGridView(Context c) {
        this.context = c;

        RequestPackage p = new RequestPackage();
        p.setUri("item/rItem");
        p.setMethod("GET");
        String strItem = HttpManager.getData(p);
        Gson gson = new Gson();
        listItem = gson.fromJson(strItem, new TypeToken<ArrayList<Item>>() { }.getType());
        //listItem = DBController.queryAllItem();
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
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.view_main, null);
            ImageView image = (ImageView) row.findViewById(R.id.item_img);
            image.setImageResource(R.drawable.ic_bar_treasure);
            image.setImageBitmap(Resources.getBitmapFromURL(imgPath + listItem.get(position).itemId + "A.jpg"));
            TextView text = (TextView) row.findViewById(R.id.item_text);
            text.setText(listItem.get(position).itemName);
        }
        return row;
    }
}
