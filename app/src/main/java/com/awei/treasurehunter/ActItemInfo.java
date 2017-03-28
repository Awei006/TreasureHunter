package com.awei.treasurehunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.widget.ImageView;
import android.widget.TextView;

public class ActItemInfo extends AppCompatActivity {

    private void getInfo() {
        Intent intent = getIntent();

        itemIcon.setImageResource(intent.getExtras().getInt("itemImg"));
        userIcon.setImageResource(intent.getExtras().getInt("userIcon"));
        userName.setText(intent.getExtras().getString("userName"));
        description.setText(intent.getExtras().getString("description"));
        ship.setText(intent.getExtras().getString("ship"));


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
        userName = (TextView) findViewById(R.id.UserName);
        description = (TextView) findViewById(R.id.txtDescription);
        ship = (TextView) findViewById(R.id.txtShip);
        itemIcon = (ImageView) findViewById(R.id.itemImg);
        userIcon = (ImageView) findViewById(R.id.userIcon);
    }

    TextView userName, description, ship;
    ImageView itemIcon, userIcon;
}
