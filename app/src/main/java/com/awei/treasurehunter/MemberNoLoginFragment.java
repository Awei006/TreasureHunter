package com.awei.treasurehunter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MemberNoLoginFragment extends Fragment{

    private final int LOGIN_REQUEST = 10;

    public MemberNoLoginFragment() {

    }

    View.OnClickListener btnLogin_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(),ActLogin.class);
            startActivityForResult(intent,LOGIN_REQUEST);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.view_mb_notlogin, container, false);


        initialComponent();
        return rootView;
    }

    private void initialComponent() {
        btnLogin = (Button)rootView.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(btnLogin_click);
    }
    private View rootView;
    private Button btnLogin;
}
