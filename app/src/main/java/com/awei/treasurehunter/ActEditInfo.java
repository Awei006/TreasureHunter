package com.awei.treasurehunter;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ActEditInfo extends AppCompatActivity {

    View.OnClickListener btnEditPwd_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    View.OnClickListener imgBtnPhoto_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    View.OnClickListener btnDatePicker_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Calendar today = Calendar.getInstance();
            new DatePickerDialog(
                    ActEditInfo.this,
                    btnDateSet_Click,
                    today.get(Calendar.YEAR),
                    today.get(Calendar.MONTH),
                    today.get(Calendar.DATE)
            ).show();
        }
    };

    DatePickerDialog.OnDateSetListener btnDateSet_Click = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

            String date = String.valueOf(i)+"/"+String.valueOf(i1+1)+"/"+ String.valueOf(i2);
            edBirthday.setText(date);

        }
    };
    AdapterView.OnItemSelectedListener spCity_itemSelect = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    AdapterView.OnItemSelectedListener spTown_itemSelect = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (!"請選擇".equals(spCity.getSelectedItem().toString()) && !"請選擇".equals(spTown.getSelectedItem().toString())) {
                String city = spCity.getSelectedItem().toString();
                String Town = spTown.getSelectedItem().toString();
                edAddress.setText(city + Town);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.form_check, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            finish();
        }
        if (id == R.id.menuCheck) {
                if(!edName.getText().toString().equals("") &&
                        !edEmail.getText().toString().equals("") &&
                        !edNickname.getText().toString().equals("") &&
                        !edBirthday.getText().toString().equals("") &&
                        !edAddress.getText().toString().equals(""))
                        {

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    Date date;
                    java.sql.Date dateForSql = null;
                    try {
                        date = dateFormat.parse(edBirthday.getText().toString());
                        dateForSql = new java.sql.Date(date.getTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    DBController.uUserInfo(edName.getText().toString(),
                                            edEmail.getText().toString(),
                                            edNickname.getText().toString(),
                                            dateForSql,
                                            2);
                    finish();
                }else {
                    Toast.makeText(this, "輸入資料有誤", Toast.LENGTH_LONG).show();
                }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void loadMyInfo(){
        edName.setText(Resources.user.userName);
        edEmail.setText(Resources.user.userMail);
        edNickname.setText(Resources.user.userNickname);
        if(Resources.user.userBirthday != null){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String date = dateFormat.format(Resources.user.userBirthday);
            edBirthday.setText(date.toString());
        }
        edAddress.setText(Resources.user.userName);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initialComponent();
        if(Resources.isLogin){
            loadMyInfo();
        }
    }

    private void initialComponent() {

        edName = (EditText) findViewById(R.id.edName);
        edEmail = (EditText) findViewById(R.id.edEmail);
        edNickname = (EditText) findViewById(R.id.edNickname);
        edBirthday = (EditText) findViewById(R.id.edBirthday);
        edAddress = (EditText) findViewById(R.id.edAddress);

        btnDatePicker = (Button) findViewById(R.id.btnDatePicker);
        imgBtnPhoto = (ImageButton)findViewById(R.id.imgBtnPhoto);
        btnEditPwd = (Button)findViewById(R.id.btnEditPwd);

        spCity = (Spinner) findViewById(R.id.spCity);
        spCity.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Resources.TXT_CITY));
        spTown = (Spinner) findViewById(R.id.spTown);
        spTown.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Resources.TXT_CITY));

        imgBtnPhoto.setOnClickListener(imgBtnPhoto_click);
        btnDatePicker.setOnClickListener(btnDatePicker_click);
        btnEditPwd.setOnClickListener(btnEditPwd_click);

        spCity.setOnItemSelectedListener(spCity_itemSelect);
        spTown.setOnItemSelectedListener(spTown_itemSelect);
    }

    EditText edName, edEmail, edNickname, edBirthday, edAddress;

    Button btnDatePicker,btnEditPwd;
    Spinner spCity, spTown;
    ImageButton imgBtnPhoto;
}
