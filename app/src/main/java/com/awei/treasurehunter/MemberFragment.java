package com.awei.treasurehunter;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.awei.info.Item;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MemberFragment extends Fragment {

    BottomNavigationView.OnNavigationItemSelectedListener Nav_click = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            if(item.getItemId() == R.id.menu_item){
                if(Resources.isLogin){

                    RequestPackage p = new RequestPackage();
                    p.setUri("http://webapicr3.azurewebsites.net/item/getMyItems/" + Resources.user.userId);
                    p.setMethod("GET");
                    String strMyItems = HttpManager.getData(p);
                    Gson gson = new Gson();
                    final ArrayList<Item> items = gson.fromJson(strMyItems, new TypeToken<ArrayList<Item>>() { }.getType());
                    //final ArrayList<Item> items = DBController.rMyItem(Resources.user.userId);
                    gridview_mb.setAdapter(new AdapterMyItem(getActivity(),items));
                    gridview_mb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Toast.makeText(getActivity(),parent.getSelectedItemId() + "",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }else if(item.getItemId() == R.id.menu_mall){
                Toast.makeText(getActivity(),"商城",Toast.LENGTH_LONG).show();
            }else if(item.getItemId() == R.id.menu_record){
                Toast.makeText(getActivity(),"請求紀錄",Toast.LENGTH_LONG).show();
            }
            return false;
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_member, container, false);
        initialComponent();
        loaduserInfo();
        return rootView;
    }
    private void loaduserInfo() {
        if(Resources.user!=null){
            txtMBName.setText(Resources.user.userNickname);
            txtMBScore.setText(Resources.user.userScore + "");
        }
    }
    private void initialComponent() {
        Nav = (BottomNavigationView)rootView.findViewById(R.id.bottom_navigation);
        Nav.setOnNavigationItemSelectedListener(Nav_click);

        gridview_mb = (GridView)rootView.findViewById(R.id.gridview_mb);
        imgUserPhoto = (ImageView)rootView.findViewById(R.id.imgUserPhoto);
        btnEditInfo = (Button)rootView.findViewById(R.id.btnEditInfo);
        btnRecord = (Button)rootView.findViewById(R.id.btnRecord);
        btnEvaluation = (Button)rootView.findViewById(R.id.btnEvaluation);
        btnTrack = (Button)rootView.findViewById(R.id.btnTrack);
        txtMBScore = (TextView)rootView.findViewById(R.id.txtMBScore);
        txtMBName = (TextView)rootView.findViewById(R.id.txtMBName);

        btnEditInfo.setOnClickListener(btnEditInfo_click);
        btnRecord.setOnClickListener(btnRecord_click);
        btnEvaluation.setOnClickListener(btnEvaluation_click);
        btnTrack.setOnClickListener(btnTrack_click);

    }
    View.OnClickListener btnEditInfo_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(),ActEditInfo.class);
            startActivity(intent);
        }
    };
    View.OnClickListener btnRecord_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(),ActRecord.class);
            startActivity(intent);
        }
    };
    View.OnClickListener btnEvaluation_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(),ActEvaluation.class);
            startActivity(intent);
        }
    };
    View.OnClickListener btnTrack_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(),ActTrack.class);
            startActivity(intent);
        }
    };
    private View rootView;
    private BottomNavigationView Nav;
    private GridView gridview_mb;
    private ImageView imgUserPhoto;
    private Button btnEditInfo,btnRecord,btnEvaluation,btnTrack,btnEditPwd;
    private TextView txtMBScore,txtMBName;

    class AdapterMyItem extends BaseAdapter {

        Context context;
        ArrayList<Item> listItem;

        AdapterMyItem(Context c, ArrayList<Item> listItem) {
            this.context = c;
            this.listItem = listItem;
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
                image.setImageBitmap(Resources.getBitmapFromURL(Resources.imgPath + listItem.get(position).itemId + "A.jpg"));
                TextView text = (TextView) row.findViewById(R.id.item_text);
                text.setText(listItem.get(position).itemName);
            }
            return row;
        }
    }
}
