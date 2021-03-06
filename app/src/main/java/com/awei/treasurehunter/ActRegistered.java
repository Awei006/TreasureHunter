package com.awei.treasurehunter;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ActRegistered extends AppCompatActivity {

    View.OnClickListener btnVerification_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (edVerification.getText().toString().length() == 10) {
                layoutChcek.setVisibility(View.VISIBLE);
            } else {
                Toast.makeText(ActRegistered.this, "號碼輸入錯誤", Toast.LENGTH_LONG).show();
            }
        }
    };

    View.OnClickListener btnCheckCode_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if ("8888".equals(edCheckCode.getText().toString())) {
                layoutInfo.setVisibility(View.VISIBLE);
                edPhone.setText(edVerification.getText().toString());
                edVerification.setEnabled(false);
                edPhone.setEnabled(false);
            } else {
                Toast.makeText(ActRegistered.this, "驗證碼錯誤", Toast.LENGTH_LONG).show();
            }
        }
    };

    View.OnClickListener btnCheckAccount_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    View.OnClickListener btnDatePicker_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Calendar today = Calendar.getInstance();
            new DatePickerDialog(
                    ActRegistered.this,
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
            if(position!=0){
                RequestPackage p = new RequestPackage();
                p.setUri(Resources.apiUrl + "district/" + position);
                p.setMethod("GET");
                new loadDistricInfoTesk().execute(p);
            }
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
                edCity.setText(city + Town);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            if (!"請選擇".equals(spCity.getSelectedItem().toString()) && !"請選擇".equals(spTown.getSelectedItem().toString())) {
                String city = spCity.getSelectedItem().toString();
                String Town = spTown.getSelectedItem().toString();
                edCity.setText(city + Town);
            }
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

        if (id == R.id.menuCheck) {
            if(edPassword.getText().toString().equals(edCheckPassword.getText().toString())){
                if(!edAccount.getText().toString().equals("") &&
                        !edPassword.getText().toString().equals("") &&
                        !edName.getText().toString().equals("") &&
                        !edPhone.getText().toString().equals("") &&
                        !edEmail.getText().toString().equals("") &&
                        !edNickname.getText().toString().equals("") &&
                        !edBirthday.getText().toString().equals("") &&
                        !edAddress.getText().toString().equals("")){

                    String sex = radioBoy.isChecked() ? "男" : "女";
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    Date date;
                    java.sql.Date dateForSql;
                    try {
                        date = dateFormat.parse(edBirthday.getText().toString());
                        dateForSql = new java.sql.Date(date.getTime());

                        RequestPackage p = new RequestPackage();
                        p.setUri(Resources.apiUrl + "userInfo/cUser");
                        p.setMethod("POST");
                        p.setSingleParam("userAccount",edAccount.getText().toString());
                        p.setSingleParam("userPassword",edPassword.getText().toString());
                        p.setSingleParam("userName",edName.getText().toString());
                        p.setSingleParam("userPhone",edPhone.getText().toString());
                        p.setSingleParam("userMail",edEmail.getText().toString());
                        p.setSingleParam("userNickname",edNickname.getText().toString());
                        p.setSingleParam("userBirthday",dateForSql.toString());
                        p.setSingleParam("userSex",sex);
                        p.setSingleParam("userVip","0");
                        p.setSingleParam("userScore","0");
                        p.setSingleParam("userMoney","0");
                        p.setSingleParam("cityId",spCity.getSelectedItemPosition()+1+"");
                        p.setSingleParam("districtId",spTown.getSelectedItemPosition()+"");
                        p.setSingleParam("addressDetail",edAddress.getText().toString());

                        new RegisteredTask().execute(p);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(this, "輸入資料有誤", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(this, "密碼不一致", Toast.LENGTH_LONG).show();
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadCityInfo() {
        RequestPackage p = new RequestPackage();
        p.setUri(Resources.apiUrl + "city");
        p.setMethod("GET");
        new loadCityInfoTesk().execute(p);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_registered);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initialComponent();
        loadCityInfo();
    }



    private void initialComponent() {
        edVerification = (EditText) findViewById(R.id.edVerification);
        edCheckCode = (EditText) findViewById(R.id.edCheckCode);
        edAccount = (EditText) findViewById(R.id.edAccount);
        edPassword = (EditText) findViewById(R.id.edPassword);
        edCheckPassword = (EditText) findViewById(R.id.edCheckPassword);
        edName = (EditText) findViewById(R.id.edName);
        edPhone = (EditText) findViewById(R.id.edPhone);
        edEmail = (EditText) findViewById(R.id.edEmail);
        edNickname = (EditText) findViewById(R.id.edNickname);
        edBirthday = (EditText) findViewById(R.id.edBirthday);
        edAddress = (EditText) findViewById(R.id.edAddress);
        edCity = (EditText) findViewById(R.id.edCity);

        btnVerification = (Button) findViewById(R.id.btnVerification);
        btnCheckCode = (Button) findViewById(R.id.btnCheckCode);
        btnCheckAccount = (Button) findViewById(R.id.btnCheckAccount);
        btnDatePicker = (Button) findViewById(R.id.btnDatePicker);

        spCity = (Spinner) findViewById(R.id.spCity);
        spTown = (Spinner) findViewById(R.id.spTown);

        layoutChcek = (LinearLayout) findViewById(R.id.layoutCheck);
        layoutChcek.setVisibility(View.INVISIBLE);
        layoutInfo = (LinearLayout) findViewById(R.id.layoutInfo);
        layoutInfo.setVisibility(View.INVISIBLE);

        btnVerification.setOnClickListener(btnVerification_click);
        btnCheckCode.setOnClickListener(btnCheckCode_click);
        btnCheckAccount.setOnClickListener(btnCheckAccount_click);
        btnDatePicker.setOnClickListener(btnDatePicker_click);

        spCity.setOnItemSelectedListener(spCity_itemSelect);
        spTown.setOnItemSelectedListener(spTown_itemSelect);

        radioBoy = (RadioButton)findViewById(R.id.radioBoy);
        radioGirl = (RadioButton)findViewById(R.id.radioGirl);
        radioBoy.setChecked(true);
    }

    EditText edVerification, edCheckCode, edAccount, edPassword, edCheckPassword,
            edName, edPhone, edEmail, edNickname, edBirthday, edAddress,edCity;
    Button btnVerification, btnCheckCode, btnCheckAccount,btnDatePicker;
    Spinner spCity, spTown;
    LinearLayout layoutChcek, layoutInfo;
    RadioButton radioBoy,radioGirl;

    public class RegisteredTask extends AsyncTask<RequestPackage, Void, String> {

        private ProgressDialog pd = new ProgressDialog(ActRegistered.this);
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setMessage("註冊中...完成後將回到主畫面");
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
            finish();
        }
    }
    public class loadCityInfoTesk extends AsyncTask<RequestPackage, Void, String> {

        private ProgressDialog pd = new ProgressDialog(ActRegistered.this);
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setMessage("載入中...");
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
            try {
                ArrayList<String> listCity = new ArrayList<>();
                listCity.add("縣市");
                JSONArray array = new JSONArray(result);
                for(int i=0;i<array.length();i++){
                    JSONObject obj = array.getJSONObject(i);
                    listCity.add(obj.getString("cityName"));
                }
                spCity.setAdapter(new ArrayAdapter(ActRegistered.this,android.R.layout.simple_list_item_1,listCity));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public class loadDistricInfoTesk extends AsyncTask<RequestPackage, Void, String> {

        private ProgressDialog pd = new ProgressDialog(ActRegistered.this);
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setMessage("載入中...");
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
            try {
                ArrayList<String> listDistrict = new ArrayList<>();
                JSONArray array = new JSONArray(result);
                for(int i=0;i<array.length();i++){
                    JSONObject obj = array.getJSONObject(i);
                    listDistrict.add(obj.getString("districtName"));

                }
                spTown.setAdapter(new ArrayAdapter(ActRegistered.this,android.R.layout.simple_list_item_1,listDistrict));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
