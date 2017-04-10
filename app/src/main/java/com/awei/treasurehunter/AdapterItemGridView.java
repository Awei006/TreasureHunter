package com.awei.treasurehunter;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
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
import java.util.List;

class AdapterItemGridView extends BaseAdapter {
    Context context;
    List<Item> listItem = new ArrayList<>();
    private String imgPath = "http://cr3fp4.azurewebsites.net/uploads/";

    public AdapterItemGridView(Context c) {
        this.context = c;

        RequestPackage p = new RequestPackage();
        p.setUri("http://webapicr3.azurewebsites.net/item/rItem");
        p.setMethod("GET");
        new MyTask().execute(p);
    }

    private void getAllItem(String result){
        Log.d("TAG",result);
        listItem = (List<Item>) new Gson().fromJson(result, new TypeToken<List<Item>>(){}.getType());
    }


    @Override
    public int getCount() {
        if(listItem == null)
            return 0;
        return listItem.size();
    }

    @Override
    public Object getItem(int position) {
        if(listItem == null)
            return null;
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
    public class MyTask extends AsyncTask<RequestPackage, Void, String> {

        private ProgressDialog pd = new ProgressDialog(context);
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setMessage("載入中!!請稍後");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(RequestPackage... params) {

            String content = HttpManager.getData(params[0]);
            return content;

        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            pd.hide();
            pd.dismiss();

            getAllItem(result);
        }
    }

}
