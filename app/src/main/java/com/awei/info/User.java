package com.awei.info;

import java.util.Date;

/**
 * Created by aaa86 on 2017/3/25.
 */

public class User {
    public int userId;
    public String userAccount;
    public String userPassword;
    public String userName;
    public String userPhone;
    public String userMail;
    public String userNickname;
    public String userPhoto;
    public int addressId;
    public Date userBirthday;
    public String userSex;
    public int userVip;
    public float userScore;
    public int userMoney;

    public User(int userId, String userAccount, String userPassword, String userName,
                String userPhone, String userMail, String userNickname, String userPhoto,
                int addressId, Date userBirthday, String userSex, int userVip, float userScore, int userMoney) {
        this.userId = userId;
        this.userAccount = userAccount;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userMail = userMail;
        this.userNickname = userNickname;
        this.userPhoto = userPhoto;
        this.addressId = addressId;
        this.userBirthday = userBirthday;
        this.userSex = userSex;
        this.userVip = userVip;
        this.userScore = userScore;
        this.userMoney = userMoney;
    }
}
