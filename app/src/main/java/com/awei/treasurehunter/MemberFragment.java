package com.awei.treasurehunter;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MemberFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_member, container, false);
        /*if(!Resources.isLogin){
            Intent intentLogin = new Intent(getActivity(),ActLogin.class);
            startActivityForResult(intentLogin,FUNC_LOGIN);
        }*/

        initialComponent();
        return rootView;
    }

    private void initialComponent() {

    }
    private View rootView;
    private FloatingActionButton fab;
}
