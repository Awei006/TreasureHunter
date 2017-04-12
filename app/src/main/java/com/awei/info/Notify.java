package com.awei.info;

public class Notify {
    private int noticId;
    private int noticType;
    private String noticUrl;
    private String noticDescription;
    private int userId;
    private boolean noticIsRead;

    public int getNoticId() {
        return noticId;
    }

    public void setNoticId(int noticId) {
        this.noticId = noticId;
    }

    public int getNoticType() {
        return noticType;
    }

    public void setNoticType(int noticType) {
        this.noticType = noticType;
    }

    public String getNoticUrl() {
        return noticUrl;
    }

    public void setNoticUrl(String noticUrl) {
        this.noticUrl = noticUrl;
    }

    public String getNoticDescription() {
        return noticDescription;
    }

    public void setNoticDescription(String noticDescription) {
        this.noticDescription = noticDescription;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isNoticIsRead() {
        return noticIsRead;
    }

    public void setNoticIsRead(boolean noticIsRead) {
        this.noticIsRead = noticIsRead;
    }

    public Notify(int noticId, int noticType, String noticUrl, String noticDescription, int userId, boolean noticIsRead) {

        this.noticId = noticId;
        this.noticType = noticType;
        this.noticUrl = noticUrl;
        this.noticDescription = noticDescription;
        this.userId = userId;
        this.noticIsRead = noticIsRead;
    }
}
