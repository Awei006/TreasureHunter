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

import static android.app.Activity.RESULT_OK;


public class MainFragment extends Fragment {

    private AdapterItemGridView itemGridView;

    public MainFragment() {

    }
    AdapterView.OnItemClickListener gridItem_itemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Item item = (Item) itemGridView.getItem(position);
            User user = DBController.queryUser(item.userId);
            Intent inte = new Intent(getActivity(), ActItemInfo.class);
            Bundle bundle = new Bundle();
            bundle.putInt("itemImg", R.drawable.ic_mb_cash);
            bundle.putInt("userIcon", R.drawable.ic_mb_eduser);
            bundle.putString("userName", user.userNickname);
            bundle.putString("description", item.itemDescription);
            bundle.putString("ship", "面交,郵寄,黑貓");
            inte.putExtras(bundle);
            startActivity(inte);
        }
    };
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Resources.FUNC_NEW_ITEM) {
            if (resultCode == RESULT_OK) {
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
