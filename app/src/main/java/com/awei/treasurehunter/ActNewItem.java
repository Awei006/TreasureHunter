package com.awei.treasurehunter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.kosalgeek.android.photoutil.CameraPhoto;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.awei.treasurehunter.Resources.ICONS_CLASSIFICATION;
import static com.awei.treasurehunter.Resources.TXT_CLASSIFICATION;

public class ActNewItem extends AppCompatActivity {

    CameraPhoto cameraPhoto;

    private void upLoadItem() {
        DBController.newItem(Resources.user.userId, edTitle.getText().toString(),
                edDescription.getText().toString(),"PhotoPath",
                spClassification.getSelectedItemPosition());

        Toast.makeText(this, "新增物品成功", Toast.LENGTH_LONG).show();
        finish();
        /*Bitmap bitmap = ((BitmapDrawable) itemImg1.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bytes = stream.toByteArray();*/

        /*String strSql = "INSERT INTO tItem (fTitle,fDescription,fImage,fClassification)" +
                      "values(?,?,?,?)";
                PreparedStatement state = conn.prepareStatement(strSql);
                state.setString(1,edTitle.getText().toString());
                state.setString(2,edDescription.getText().toString());
                state.setString(4,spClassification.getSelectedItem().toString());
                state.setBytes(3,bytes);
                state.executeUpdate();
                state.close();
                conn.close();*/

        /*Intent intent = getIntent();
        Bundle bund = new Bundle();
        bund.putString("title",edTitle.getText().toString());
        bund.putString("content",edDescription.getText().toString());
        bund.putByteArray("img",bytes);
        bund.putString("classification",spClassification.getSelectedItem().toString());
        intent.putExtras(bund);
        setResult(RESULT_OK,intent);*/

    }

    private void spSetAdapter() {

        ArrayList<String> listFuncs = new ArrayList<String>();
        ArrayList<Integer> listIcon = new ArrayList<Integer>();
        for (String s : TXT_CLASSIFICATION)
            listFuncs.add(s);
        for (int i : ICONS_CLASSIFICATION)
            listIcon.add(i);

        spClassification.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                new String[]{"全部", "男性", "女性", "應兒", "玩具", "書籍", "家電"}));
    }

    View.OnClickListener btnUpload_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(camera_intent, Resources.CAM_REQUEST);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == Resources.CAM_REQUEST) {
                Bitmap cameraImage = (Bitmap) data.getExtras().get("data");
                itemImg1.setImageBitmap(cameraImage);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.form_check, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuCheck) {
            upLoadItem();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_new_item);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cameraPhoto = new CameraPhoto(getApplicationContext());
        initialComponent();
    }

    private void initialComponent() {
        itemImg1 = (ImageView) findViewById(R.id.item_img1);
        itemImg2 = (ImageView) findViewById(R.id.item_img2);
        itemImg3 = (ImageView) findViewById(R.id.item_img3);
        btnUpload = (Button) findViewById(R.id.btn_upload);
        edTitle = (EditText) findViewById(R.id.ed_title);
        edDescription = (EditText) findViewById(R.id.ed_description);
        spClassification = (Spinner) findViewById(R.id.sp_classification);

        btnUpload.setOnClickListener(btnUpload_click);
        spSetAdapter();
    }

    ImageView itemImg1, itemImg2, itemImg3;
    Button btnUpload;
    EditText edTitle, edDescription;
    Spinner spClassification;


}
