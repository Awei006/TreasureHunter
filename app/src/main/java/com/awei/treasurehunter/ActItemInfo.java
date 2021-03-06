package com.awei.treasurehunter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.awei.info.Question;
import com.awei.info.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.sql.Date;
import java.util.ArrayList;

import static com.awei.treasurehunter.Resources.user;

public class ActItemInfo extends AppCompatActivity {

    private AlertDialog dialogQuestion;
    private Intent intent;
    private ArrayList<Question> listQ = new ArrayList<>();
    private String imgPath = "http://cr3fp4.azurewebsites.net/uploads/";


    private void getInfo() {
        intent = getIntent();
        itemIcon.setImageBitmap(Resources.getBitmapFromURL(imgPath + Resources.itemClick.itemId + "A.jpg") );
        userIcon.setImageResource(R.drawable.ic_c_free);
        userName.setText(intent.getExtras().getString(Dictionary.USER_NICKNAME));
        userCity.setText(intent.getExtras().getInt(Dictionary.USER_CITY)+"");
        description.setText(Resources.itemClick.itemDescription);
        ship.setText("測試用貨運");

        RequestPackage p = new RequestPackage();
        p.setUri(Resources.apiUrl +"question/rQuestion/" + Resources.itemClick.itemId);
        p.setMethod("GET");

        String result = HttpManager.getData(p);
        Log.d("result",result);
        listQ = new Gson().fromJson(result,new TypeToken<ArrayList<Question>>(){}.getType());
        addQuestion();
    }

    private void addQuestion(){
        for(final Question q : listQ){
            RequestPackage p = new RequestPackage();
            p.setUri(Resources.apiUrl + "userInfo/rUser/"+q.getUserId());
            p.setMethod("GET");

            String result = HttpManager.getData(p);
            ArrayList<User> listuser = new Gson().fromJson(result,new TypeToken<ArrayList<User>>(){}.getType());
            User user = listuser.get(0);

            LayoutInflater inflater = LayoutInflater.from(this);
            row = inflater.inflate(R.layout.view_leave_msg, null);

            ImageView imgPhoto = (ImageView)row.findViewById(R.id.imgPhoto);
            imgPhoto.setImageResource(R.drawable.ic_c_3c);

            TextView txtName = (TextView)row.findViewById(R.id.txtName);
            txtName.setText(user.userNickname);

            TextView txtContent = (TextView)row.findViewById(R.id.txtContent);
            txtContent.setText(q.getQuestionDescription());

            TextView txtDate = (TextView)row.findViewById(R.id.txtDate);
            String dateForString = q.getQuestiontime().substring(0,10);
            txtDate.setText(dateForString);
            Log.d("DATE",dateForString);

            TextView txtAnswer = (TextView)row.findViewById(R.id.txtAnswer);
            if(q.getQuestionAnswer()!=null)
                txtAnswer.setText(q.getQuestionAnswer());
            else
                txtAnswer.setText("尚未回覆");

            Button btnAns = (Button)row.findViewById(R.id.btnAns);
            if(user != null && Resources.itemClick.userId == user.userId)
                btnAns.setVisibility(View.VISIBLE);

            btnAns.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewAnswer(q.getQuestionId());
                }
            });
            itemLayout.addView(row);
        }
    }
    private void viewQuestion(){
        View mView = getLayoutInflater().inflate(R.layout.view_question, null);
        final EditText edContent = (EditText)mView.findViewById(R.id.edContent);
        Button btnSend = (Button)mView.findViewById(R.id.btnSend);
        Button btnCancel = (Button)mView.findViewById(R.id.btnCancel);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userId = user.userId;
                int itemId = Resources.itemClick.itemId;
                String description = edContent.getText().toString();
                Date date = new Date(System.currentTimeMillis());

                RequestPackage p = new RequestPackage();
                p.setUri(Resources.apiUrl + "question/cQuestion");
                p.setMethod("POST");
                p.setSingleParam("itemId",itemId+"");
                p.setSingleParam("userId",userId+"");
                p.setSingleParam("questionDescription",description);
                p.setSingleParam("questionAnswer",null);
                p.setSingleParam("questiontime",date.toString());
                new cQuestion().execute(p);
                dialogQuestion.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogQuestion.dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(mView);
        dialogQuestion = builder.show();
    }
    private void viewAnswer(final int id){
        View mView = getLayoutInflater().inflate(R.layout.view_question, null);
        final EditText edContent = (EditText)mView.findViewById(R.id.edContent);
        Button btnSend = (Button)mView.findViewById(R.id.btnSend);
        Button btnCancel = (Button)mView.findViewById(R.id.btnCancel);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = edContent.getText().toString();
                dialogQuestion.dismiss();

                RequestPackage p = new RequestPackage();
                p.setUri(Resources.apiUrl + "question/uQuestion/" + id);
                p.setMethod("POST");
                p.setSingleParam("questionAnswer",description);
                new AnswerTesk().execute(p);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogQuestion.dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(mView);
        dialogQuestion = builder.show();
    }

    View.OnClickListener imgbtnTrack_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                //追蹤物品按鈕事件
        }
    };

    View.OnClickListener btnRequest_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    View.OnClickListener btnQuestion_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!Resources.isLogin) {
                Intent intentLogin = new Intent(getApplicationContext(), ActLogin.class);
                startActivity(intentLogin);
            }
            viewQuestion();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_item_info);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initialComponent();
        getInfo();
    }

    private void initialComponent() {
        userName = (TextView) findViewById(R.id.userName);
        description = (TextView) findViewById(R.id.txtDescription);
        ship = (TextView) findViewById(R.id.txtShip);
        itemIcon = (ImageView) findViewById(R.id.itemImg);
        userIcon = (ImageView) findViewById(R.id.userIcon);
        userCity = (TextView)findViewById(R.id.txtCity);
        itemLayout = (LinearLayout)findViewById(R.id.itemLayout);
        btnQuestion = (Button)findViewById(R.id.btnQuestion);
        btnRequest = (Button)findViewById(R.id.btnRequest);
        imgbtnTrack = (ImageButton)findViewById(R.id.imgbtnTrack);


        btnQuestion.setOnClickListener(btnQuestion_click);
        btnRequest.setOnClickListener(btnRequest_click);
        imgbtnTrack.setOnClickListener(imgbtnTrack_click);
    }
    TextView userName, description, ship,userCity;
    ImageView itemIcon, userIcon;
    LinearLayout itemLayout;
    Button btnQuestion,btnRequest;
    ImageButton imgbtnTrack;
    View row;

    public class cQuestion extends AsyncTask<RequestPackage, Void, String> {

        private ProgressDialog pd = new ProgressDialog(ActItemInfo.this);
        protected void onPreExecute()
        {
            super.onPreExecute();
           pd.setMessage("請稍後!!");
           pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(RequestPackage... params)
        {
            String content = HttpManager.getData(params[0]);
            return content;
        }
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);
           pd.hide();
           pd.dismiss();
        }
    }
    public class AnswerTesk extends AsyncTask<RequestPackage, Void, String> {

        protected void onPreExecute()
        {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(RequestPackage... params)
        {
            String content = HttpManager.getData(params[0]);
           return content;
        }

        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);
        }
    }
}
