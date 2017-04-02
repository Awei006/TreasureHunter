package com.awei.treasurehunter;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class MemberFragment extends Fragment {

    BottomNavigationView.OnNavigationItemSelectedListener Nav_click = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if(item.getItemId() == R.id.menu_item){
                Toast.makeText(getActivity(),"我的物品",Toast.LENGTH_LONG).show();
            }else if(item.getItemId() == R.id.menu_mall){
                Toast.makeText(getActivity(),"商城",Toast.LENGTH_LONG).show();
            }

            return false;
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_member, container, false);
        /*if(!Resources.isLogin){
            Intent intentLogin = new Intent(getActivity(),ActLogin.class);
            startActivityForResult(intentLogin,FUNC_LOGIN);
        }*/

        Nav = (BottomNavigationView)rootView.findViewById(R.id.bottom_navigation);

        Nav.setOnNavigationItemSelectedListener(Nav_click);

        initialComponent();
        return rootView;
    }

    private void initialComponent() {

    }
    private View rootView;
    private BottomNavigationView Nav;
}
