package com.awei.treasurehunter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.awei.info.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class ActLogin extends AppCompatActivity {

    View.OnClickListener btnLogin_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String account = edAccount.getText().toString();
            String password = edPassword.getText().toString();

            RequestPackage p = new RequestPackage();
            p.setUri("http://webapicr3.azurewebsites.net/userInfo/login/" + account + "/" + password);
            p.setMethod("GET");
            new LoginTask().execute(p);
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

    public class LoginTask extends AsyncTask<RequestPackage, Void, String> {

        private ProgressDialog pd = new ProgressDialog(ActLogin.this);
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setMessage("登入中!!請稍後!!");
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
            List<User> listUser = new Gson().fromJson(result,new TypeToken<List<User>>(){}.getType());
            User user = listUser.get(0);
            if(user !=null){
                Resources.user = user;
                Resources.isLogin = true;
                Resources.doRefreshScreen = true;
                finish();
            }else{
                Toast.makeText(ActLogin.this, "查無此人", Toast.LENGTH_LONG).show();
            }
        }
    }
}
