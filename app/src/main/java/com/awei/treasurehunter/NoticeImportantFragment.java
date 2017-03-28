package com.awei.treasurehunter;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;


import java.util.ArrayList;

public class NoticeImportantFragment extends Fragment {

    private String[] content = {"資料修改", "我的物品", "商城物品", "追蹤物品", "追蹤會員", "我的評價", "現金儲值"};
    private String[] dates = {"2015/08/09", "2015/08/09", "2015/08/09", "2015/08/09", "2015/08/09", "2015/08/09", "2015/08/09"};
    private int[] icons = {R.drawable.ic_mb_eduser,
            R.drawable.ic_mb_myitem,
            R.drawable.ic_mb_mall,
            R.drawable.ic_mb_likeitem,
            R.drawable.ic_mb_sale,
            R.drawable.ic_mb_evaluation,
            R.drawable.ic_mb_cash};

    private ArrayList<Integer> listIcons = new ArrayList<Integer>();
    private ArrayList<String> listContent = new ArrayList<String>();
    private ArrayList<String> listTime = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.notic_important, container, false);
        for (int i : icons)
            listIcons.add(i);
        for (String s : content)
            listContent.add(s);
        for (String s : dates)
            listTime.add(s);

        findViewe();

        return rootView;
    }

    private void findViewe() {

        gridView = (GridView) rootView.findViewById(R.id.grid);
        gridView.setAdapter(new AdapterNotice(getContext(), listIcons, listContent, listTime));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private GridView gridView;
    private View rootView;
}
