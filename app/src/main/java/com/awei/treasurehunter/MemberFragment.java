package com.awei.treasurehunter;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import static com.awei.treasurehunter.Resources.FUNC_LOGIN;


/**
 * A simple {@link Fragment} subclass.
 */
public class MemberFragment extends Fragment {

    private ArrayList<String> listFuncs = new ArrayList<String>();
    private ArrayList<Integer> listIcon = new ArrayList<Integer>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_member, container, false);
        if(!Resources.isLogin){
            Intent intentLogin = new Intent(getActivity(),ActLogin.class);
            startActivityForResult(intentLogin,FUNC_LOGIN);
        }
        for (String s : Resources.TXT_MEMBER)
            listFuncs.add(s);
        for (int i : Resources.ICONS_CLASSIFICATION)
            listIcon.add(i);

        findView();

        return rootView;
    }

    private void findView() {

        grid = (GridView) rootView.findViewById(R.id.grid);
        grid.setAdapter(new AdapterIcon(getActivity(), listFuncs, listIcon));
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch ((int) id) {
                    case R.drawable.ic_mb_eduser:
                        Intent inte = new Intent(getContext(), ActMemberInfo.class);
                        startActivity(inte);
                        break;
                    case R.drawable.ic_mb_myitem:
                        break;
                    case R.drawable.ic_mb_mall:
                        break;
                    case R.drawable.ic_mb_likeitem:
                        break;
                    case R.drawable.ic_mb_sale:
                        break;
                    case R.drawable.ic_mb_evaluation:
                        break;
                    case R.drawable.ic_mb_cash:
                        break;
                }
            }
        });
    }

    private GridView grid;
    private View rootView;
}
