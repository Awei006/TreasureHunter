package com.awei.treasurehunter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.awei.info.User;

public class ActLogin extends AppCompatActivity {

    View.OnClickListener btnLogin_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String account = edAccount.getText().toString();
            String password = edPassword.getText().toString();

            User user = DBController.queryLogin(account,password);
            if(user !=null){
                Resources.user = user;
                Resources.isLogin = true;
                Resources.doRefreshScreen = true;
                finish();
            }else{
                Toast.makeText(ActLogin.this, "查無此人", Toast.LENGTH_LONG).show();
            }
        }
    };
    View.OnClickListener btnRegistered_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent inte = new Intent(ActLogin.this, ActRegistered.class);
            startActivity(inte);
        }
    };

    View.OnClickListener btnlater_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_login);

        InitialComponent();
    }

    private void InitialComponent() {
        edAccount = (AutoCompleteTextView) findViewById(R.id.edAccount);
        edPassword = (EditText) findViewById(R.id.edPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegistered = (Button) findViewById(R.id.btnRegistered);
        btnlater = (Button) findViewById(R.id.btnlater);

        btnLogin.setOnClickListener(btnLogin_click);
        btnRegistered.setOnClickListener(btnRegistered_click);
        btnlater.setOnClickListener(btnlater_click);
    }

    private AutoCompleteTextView edAccount;
    private EditText edPassword;
    private Button btnLogin;
    private Button btnRegistered,btnlater;
}
