package com.awei.treasurehunter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.awei.info.Item;
import com.awei.info.User;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by iii on 2017/3/22.
 */

public class Resources {
    public static final String imgPath = "http://cr3fp4.azurewebsites.net/uploads/";
    public static final String apiUrl = "http://cr3webapi.azurewebsites.net/";

    public static User user = null;
    public static Item itemClick = null;
    public static List<Item> AllItems= new ArrayList<>();
    public static ArrayList<Item> myItems = new ArrayList<Item>();


    public static boolean isLogin = false;
    public static boolean doRefreshScreen = false;
    public static boolean closeUpload = false;

    public static final String FRAG[] = {"首頁","商城","通知","會員"};

    public static final String[] TXT_CLASSIFICATION = {"全部", "男性", "女性", "寵物", "玩具公仔",
            "家電傢俱", "3C電子", "遊戲", "美妝保養", "書籍",
            "文具用品", "樂器", "交通工具", "零食餅乾", "生活用品",
            "嬰兒用品", "點數票卷", "運動用品", "裝飾配件", "免費贈與"};
    public static final int[] ICONS_CLASSIFICATION = {R.drawable.ic_bar_treasure, R.drawable.ic_c_men, R.drawable.ic_c_women, R.drawable.ic_c_pet,
            R.drawable.ic_c_toy, R.drawable.ic_c_furniture, R.drawable.ic_c_3c, R.drawable.ic_c_game,
            R.drawable.ic_c_cosmetic, R.drawable.ic_c_book, R.drawable.ic_c_stationary, R.drawable.ic_c_instrument,
            R.drawable.ic_c_vehicle, R.drawable.ic_c_dessert, R.drawable.ic_c_daily, R.drawable.ic_c_baby,
            R.drawable.ic_c_ticket, R.drawable.ic_c_sport, R.drawable.ic_c_accessory, R.drawable.ic_c_free,};


    public static Bitmap getBitmapFromURL(String src){

        try {
            URL url = new URL(src);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.connect();

            InputStream input = conn.getInputStream();
            Bitmap mBitmap = BitmapFactory.decodeStream(input);
            return mBitmap;

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
