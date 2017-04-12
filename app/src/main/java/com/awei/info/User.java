package com.awei.info;



public class User {
    public int userId;
    public String userAccount;
    public String userPassword;
    public String userName;
    public String userPhone;
    public String userMail;
    public String userNickname;
    public String userPhoto;
    public String userBirthday;
    public String userSex;
    public int userVip;
    public float userScore;
    public int userMoney;
    public int cityId;
    public int districId;
    public String addressDetial;

    public User(int userId, String userAccount, String userPassword, String userName,
                String userPhone, String userMail, String userNickname, String userPhoto, String userBirthday,
                String userSex, int userVip, float userScore, int userMoney,
                int cityId, int districId, String addressDetial) {
        this.userId = userId;
        this.userAccount = userAccount;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userMail = userMail;
        this.userNickname = userNickname;
        this.userPhoto = userPhoto;
        this.userBirthday = userBirthday;
        this.userSex = userSex;
        this.userVip = userVip;
        this.userScore = userScore;
        this.userMoney = userMoney;
        this.cityId = cityId;
        this.districId = districId;
        this.addressDetial = addressDetial;
    }
}
