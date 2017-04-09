package com.awei.treasurehunter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import static com.awei.treasurehunter.Resources.ICONS_CLASSIFICATION;
import static com.awei.treasurehunter.Resources.TXT_CLASSIFICATION;

public class ActNewItem extends AppCompatActivity {

    private final int RESULT_IMG1 = 235;
    private final int RESULT_IMG2 = 236;
    private final int RESULT_IMG3 = 237;
    public final String URL = "http://cr3fp4.azurewebsites.net/uploads/uploadimage.php";
    private static String _imageFileName,_bytes64Sting;
    private RequestPackage rp;

    private void upLoadItem() {


        RequestPackage p = new RequestPackage();
        p.setUri("item/cItem");
        p.setMethod("POST");
        p.setSingleParam("userId",Resources.user.userId + "");
        p.setSingleParam("itemName",edTitle.getText().toString());
        p.setSingleParam("itemDescription",edDescription.getText().toString());
        p.setSingleParam("itemClass",(spClassification.getSelectedItemPosition()+1) +"");
        HttpManager.getData(p);

        /*DBController.newItem(Resources.user.userId, edTitle.getText().toString(),
                edDescription.getText().toString(),_imageFileName,
                spClassification.getSelectedItemPosition()+1);*/


        _imageFileName = String.valueOf(System.currentTimeMillis());
        upLoadImg(itemImg3,_imageFileName + "C");
        upLoadImg(itemImg2,_imageFileName + "B");
        upLoadImg(itemImg1,_imageFileName + "A");

        Resources.doRefreshScreen = true;
    }

    private void upLoadImg(ImageButton image,String fileName){
        if(image.getId() == R.id.item_img1)
            Resources.closeUpload = true;
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bao);
        byte[] byteArray = bao.toByteArray();
        _bytes64Sting = Base64.encodeBytes(byteArray);
        rp = new RequestPackage();
        rp.setMethod("POST");
        rp.setUri(URL);
        rp.setSingleParam("base64", _bytes64Sting);
        rp.setSingleParam("ImageName", fileName + ".jpg");

        new uploadToServer().execute(rp);
    }

    private void spSetAdapter() {
        ArrayList<String> listFuncs = new ArrayList<String>();
        ArrayList<Integer> listIcon = new ArrayList<Integer>();
        for (String s : TXT_CLASSIFICATION)
            listFuncs.add(s);
        for (int i : ICONS_CLASSIFICATION)
            listIcon.add(i);

        spClassification.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                Resources.TXT_CLASSIFICATION));
    }

    View.OnClickListener itemImg1_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(camera_intent, RESULT_IMG1);
        }
    };
    View.OnClickListener itemImg2_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(camera_intent, RESULT_IMG2);
        }
    };
    View.OnClickListener itemImg3_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(camera_intent, RESULT_IMG3);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == RESULT_IMG1) {
                Bitmap cameraImage = (Bitmap) data.getExtras().get("data");
                itemImg1.setImageBitmap(cameraImage);
            }
            if (requestCode == RESULT_IMG2) {
                Bitmap cameraImage = (Bitmap) data.getExtras().get("data");
                itemImg2.setImageBitmap(cameraImage);
            }
            if (requestCode == RESULT_IMG3) {
                Bitmap cameraImage = (Bitmap) data.getExtras().get("data");
                itemImg3.setImageBitmap(cameraImage);
            }

        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getApplicationContext(),
                    "User cancelled image capture", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(),
                    "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                    .show();
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

        if(id == android.R.id.home){
            finish();
        }

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
        initialComponent();

        Resources.closeUpload = false;
    }

    private void initialComponent() {
        itemImg1 = (ImageButton) findViewById(R.id.item_img1);
        itemImg2 = (ImageButton) findViewById(R.id.item_img2);
        itemImg3 = (ImageButton) findViewById(R.id.item_img3);
        edTitle = (EditText) findViewById(R.id.ed_title);
        edDescription = (EditText) findViewById(R.id.ed_description);
        spClassification = (Spinner) findViewById(R.id.sp_classification);

        itemImg1.setOnClickListener(itemImg1_click);
        itemImg2.setOnClickListener(itemImg2_click);
        itemImg3.setOnClickListener(itemImg3_click);
        spSetAdapter();
    }

    ImageButton itemImg1, itemImg2, itemImg3;
    EditText edTitle, edDescription;
    Spinner spClassification;

    public class uploadToServer extends AsyncTask<RequestPackage, Void, String> {

        private ProgressDialog pd = new ProgressDialog(ActNewItem.this);
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("TAG",_imageFileName+".jpg");
            pd.setMessage("物品上傳中!!請稍後");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(RequestPackage... params) {

            String content = MyHttpURLConnection.getData(params[0]);
            return content;

        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            pd.hide();
            pd.dismiss();
            if(Resources.closeUpload)
                finish();
        }
    }
}
