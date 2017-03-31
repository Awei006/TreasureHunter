package com.awei.treasurehunter;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.awei.info.Question;

import net.sourceforge.jtds.jdbc.DateTime;

import java.sql.Date;

import static com.awei.treasurehunter.Resources.FUNC_LOGIN;

public class ActItemInfo extends AppCompatActivity {

    private AlertDialog dialogQuestion;
    private Intent intent;
    private void getInfo() {
        intent = getIntent();
        itemIcon.setImageResource(intent.getExtras().getInt("itemImg"));
        userIcon.setImageResource(intent.getExtras().getInt("userIcon"));
        userName.setText(intent.getExtras().getString("userName"));
        description.setText(intent.getExtras().getString("description"));
        ship.setText(intent.getExtras().getString("ship"));


        for(int i=1;i<5;i++){
            LayoutInflater inflater = LayoutInflater.from(this);
            View row = inflater.inflate(R.layout.view_leave_msg, null);
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
                int userId = Resources.user.userId;
                int itemId = intent.getExtras().getInt("itemId");
                String description = edContent.getText().toString();
                Date date = new Date(System.currentTimeMillis());
                Question q = new Question(0,itemId,userId,description,date);
                DBController.cQuestion(q);
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

    View.OnClickListener btnRequest_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    View.OnClickListener btnQuestion_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!Resources.isLogin) {
                Intent intentLogin = new Intent(ActItemInfo.this, ActLogin.class);
                startActivityForResult(intentLogin, FUNC_LOGIN);
            }
            viewQuestion();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_item_info);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initialComponent();
        getInfo();
    }

    private void initialComponent() {
        userName = (TextView) findViewById(R.id.UserName);
        description = (TextView) findViewById(R.id.txtDescription);
        ship = (TextView) findViewById(R.id.txtShip);
        itemIcon = (ImageView) findViewById(R.id.itemImg);
        userIcon = (ImageView) findViewById(R.id.userIcon);
        itemLayout = (LinearLayout)findViewById(R.id.itemLayout);
        btnQuestion = (Button)findViewById(R.id.btnQuestion);
        btnRequest = (Button)findViewById(R.id.btnRequest);

        btnQuestion.setOnClickListener(btnQuestion_click);
        btnRequest.setOnClickListener(btnRequest_click);

    }

    TextView userName, description, ship;
    ImageView itemIcon, userIcon;
    LinearLayout itemLayout;
    Button btnQuestion,btnRequest;
}
