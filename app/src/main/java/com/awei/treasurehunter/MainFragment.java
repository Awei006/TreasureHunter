package com.awei.treasurehunter;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.awei.info.Item;
import com.awei.info.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import static android.app.Activity.RESULT_OK;


public class MainFragment extends Fragment {

    private final int NEW_ITEM = 876;
    private AdapterItemGridView itemGridView;


    public MainFragment() {

    }
    AdapterView.OnItemClickListener gridItem_itemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Resources.itemClick = (Item) itemGridView.getItem(position);

            RequestPackage p = new RequestPackage();
            p.setUri("http://webapicr3.azurewebsites.net/userInfo/rUserInfo" + Resources.itemClick.userId);
            p.setMethod("GET");
            String strUser = HttpManager.getData(p);
            Gson gson = new Gson();
            User user = gson.fromJson(strUser, new TypeToken<User>() { }.getType());
            //User user = DBController.queryUser(Resources.itemClick.userId);
            Intent inte = new Intent(getActivity(), ActItemInfo.class);
            Bundle bundle = new Bundle();
            bundle.putInt(Dictionary.USER_CITY, user.cityId);
            bundle.putString(Dictionary.USER_NICKNAME, user.userNickname);
            bundle.putString(Dictionary.USER_PHOTO, user.userPhoto);
            bundle.putString(Dictionary.USER_SHIP, "面交,郵寄,黑貓");
            inte.putExtras(bundle);
            startActivity(inte);
        }
    };
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == NEW_ITEM) {
                Toast.makeText(getActivity(), "新增成功", Toast.LENGTH_LONG).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initialComponent();
        return rootView;
    }

    private void initialComponent() {
        gridItem = (GridView) rootView.findViewById(R.id.gridItem);
        itemGridView = new AdapterItemGridView(getActivity());
        gridItem.setAdapter(itemGridView);
        gridItem.setOnItemClickListener(gridItem_itemClick);
    }

    private GridView gridItem;
    private View rootView;
}
