package com.awei.treasurehunter;

import android.os.StrictMode;

import com.awei.info.Item;
import com.awei.info.Question;
import com.awei.info.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by iii on 2017/3/23.
 */

public class DBController {

    public static Connection getConnection() {
        Connection conn = null;
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String urlstr = "jdbc:jtds:sqlserver://finalproject4.database.windows.net:1433;databaseName=treasure;user=fp4admin@finalproject4;password=CR3fp4te@m;encrypt=false" +
                    ";trustServerCertificate=true;hostNameInCertificate=finalproject4.database.windows.net;loginTimeout=30;";

            //String urlstr = "jdbc:jtds:sqlserver://192.168.1.102:1433;databaseName=Treasure;user=sa;password=as";
            conn = DriverManager.getConnection(urlstr);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static User queryLogin(String account,String password){
        User user = null;
        try {
            Connection conn = getConnection();
            String strSql = "SELECT * from userInfo where userAccount = ? and userPassword = ?";
            PreparedStatement state = conn.prepareStatement(strSql);
            state.setString(1,account);
            state.setString(2,password);
            ResultSet rs = state.executeQuery();

            while (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),
                        rs.getString(5), rs.getString(6),rs.getString(7), rs.getString(8),
                        rs.getInt(9),rs.getDate(10),rs.getString(11),rs.getInt(12),rs.getFloat(13),rs.getInt(14));
            }
            rs.close();
            state.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    public static void uUserInfo(String userName,String userMail,String userNickname,Date userBirthday,int addressId){
        try {
            Connection conn = getConnection();
            String strSql = "UPDATE userInfo SET userName = ?,userMail = ?,userNickname = ?,userBirthday = ?,addressId = ?" +
                    " WHERE userId = ?";
            PreparedStatement state = conn.prepareStatement(strSql);
            state.setString(1, userName);
            state.setString(2, userMail);
            state.setString(3, userNickname);
            state.setDate(4, userBirthday);
            state.setInt(5, addressId);
            state.setInt(6, Resources.user.userId);
            state.executeUpdate();
            state.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User queryUser(int userId){
        User user = null;

        try {
            Connection conn = getConnection();
            if(conn!=null && !conn.isClosed()){
                String strSql = "SELECT * from userInfo where userId = ?";
                PreparedStatement state = conn.prepareStatement(strSql);
                state.setInt(1,userId);
                ResultSet rs = state.executeQuery();

                while (rs.next()) {
                    user = new User(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),
                            rs.getString(5), rs.getString(6),rs.getString(7), rs.getString(8),
                            rs.getInt(9),rs.getDate(10),rs.getString(11),rs.getInt(12),rs.getFloat(13),rs.getInt(14));
                }
                rs.close();
                state.close();
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    public static ArrayList<Item> rMyItem(int userId){
        ArrayList<Item> listItem = new ArrayList<>();

        try {
            Connection conn = getConnection();
            String strSql = "SELECT * from item where userId = ?";
            PreparedStatement state = conn.prepareStatement(strSql);
            state.setInt(1, userId);
            ResultSet rs = state.executeQuery();

            while (rs.next()) {
                Item item = new Item(rs.getInt(1), rs.getInt(2), rs.getString(3),
                        rs.getString(4), rs.getString(6), rs.getInt(5));

                listItem.add(item);
            }
            rs.close();
            state.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listItem;
    }

    public static ArrayList<Item> queryAllItem(){
        ArrayList<Item> listItem = new ArrayList<>();

        try {
            Connection conn = getConnection();
            if(conn!=null && !conn.isClosed()){
                String strSql = "SELECT * from item";
                Statement state = conn.createStatement();
                ResultSet rs = state.executeQuery(strSql);

                while (rs.next()) {
                    Item item = new Item(rs.getInt(1), rs.getInt(2), rs.getString(3),
                            rs.getString(4), rs.getString(6), rs.getInt(5));
                    listItem.add(item);

                }

                rs.close();
                state.close();
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listItem;
    }
    public static Item queryItem(int itemId) {
        Item item = null;

        try {
            Connection conn = getConnection();
            String strSql = "SELECT * from item where itemId = ?";
            PreparedStatement state = conn.prepareStatement(strSql);
            state.setInt(1, itemId);
            ResultSet rs = state.executeQuery();

            while (rs.next()) {
                item = new Item(rs.getInt(1), rs.getInt(2), rs.getString(3),
                        rs.getString(4), rs.getString(6), rs.getInt(5));
            }

            rs.close();
            state.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }
    public static void registered(String userAccount, String userPassword, String userName,
                                  String userPhone, String userMail, String userNickname,
                                  int addressId, java.sql.Date userBirthday, String userSex) {

        try {
            Connection conn = getConnection();
            if (conn != null && !conn.isClosed()) {
                String strSql = "INSERT INTO userInfo (userAccount,userPassword,userName," +
                        "userPhone,userMail,userNickname,userPhoto,addressId,userBirthday,userSex,userVip,userScore,userMoney)" +
                        " values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement state = conn.prepareStatement(strSql);
                state.setString(1, userAccount);
                state.setString(2, userPassword);
                state.setString(3, userName);
                state.setString(4, userPhone);
                state.setString(5, userMail);
                state.setString(6, userNickname);
                state.setString(7, "userPhoto");
                state.setInt(8, addressId);
                state.setDate(9,userBirthday);
                state.setString(10, userSex);
                state.setInt(11, 0);
                state.setFloat(12, 0);
                state.setInt(13, 0);

                state.executeUpdate();
                state.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void newItem(int userId, String itemName, String itemDescription, String itemPicture, int itemClassId) {
        Connection conn = getConnection();
        String strSql = "INSERT INTO item (userId,itemName,itemDescription,itemPicture,itemClassId)" +
                "values(?,?,?,?,?)";
        try {
            PreparedStatement state = conn.prepareStatement(strSql);
            state.setInt(1, userId);
            state.setString(2, itemName);
            state.setString(3, itemDescription);
            state.setString(4, itemPicture);
            state.setInt(5, itemClassId);
            state.executeUpdate();
            state.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void delItem(int itemId) {
        Connection conn = getConnection();
        String strSql = "DELETE FROM item where itemId = ?";
        try {
            PreparedStatement state = conn.prepareStatement(strSql);
            state.setInt(1, itemId);
            state.executeUpdate();
            state.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void cQuestion(Question q){

        try {
            Connection conn = getConnection();
            String strSql = "INSERT INTO question (itemId,userId,questionDescription,questionTime)" +
                    "values(?,?,?,?)";
            PreparedStatement state = conn.prepareStatement(strSql);
            state.setInt(1, q.getItemId());
            state.setInt(2, q.getUserId());
            state.setString(3, q.getDescription());
            state.setDate(4, q.getTime());
            state.executeUpdate();
            state.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Question> rQuestion(int itemId){
        ArrayList<Question> listQuestion = new ArrayList<>();

        try {
            Connection conn = getConnection();
            if(conn!=null && !conn.isClosed()){
                String strSql = "SELECT * from question where itemId = ?";
                PreparedStatement state = conn.prepareStatement(strSql);
                state.setInt(1, Resources.itemClick.itemId);
                ResultSet rs = state.executeQuery();

                while (rs.next()) {
                    Question question = new Question(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                            rs.getString(4),rs.getString(5), rs.getDate(6));
                    listQuestion.add(question);
                }

                rs.close();
                state.close();
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listQuestion;
    }
    public static void uQuestion(int questionId,String ans){
        try {
            Connection conn = getConnection();
            String strSql = "UPDATE question SET questionAnswer = ?" +
                    " WHERE questionId = ?";
            PreparedStatement state = conn.prepareStatement(strSql);
            state.setString(1, ans);
            state.setInt(2, questionId);
            state.executeUpdate();
            state.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<Integer> rTrackItem(){
        ArrayList<Integer> listItemId = new ArrayList<>();

        try {
            Connection conn = getConnection();
            if(conn!=null && !conn.isClosed()){
                String strSql = "SELECT * from trackItem where userId = ?";
                PreparedStatement state = conn.prepareStatement(strSql);
                state.setInt(1, Resources.user.userId);
                ResultSet rs = state.executeQuery();

                while (rs.next()) {
                    listItemId.add(rs.getInt(2));
                }

                rs.close();
                state.close();
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listItemId;
    }
    public static void cTrackItem(){

        try {
            Connection conn = getConnection();
            String strSql = "INSERT INTO trackItem (userId,itemId)" +
                    "values(?,?)";
            PreparedStatement state = conn.prepareStatement(strSql);
            state.setInt(1, Resources.user.userId);
            state.setInt(2, Resources.itemClick.itemId);
            state.executeUpdate();
            state.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void dTrackItem(){

        try {
            Connection conn = getConnection();
            String strSql = "DELETE FROM trackItem WHERE itemId = ?";
            PreparedStatement state = conn.prepareStatement(strSql);
            state.setInt(1, Resources.itemClick.itemId);
            state.executeUpdate();
            state.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
