package com.awei.info;

/**
 * Created by aaa86 on 2017/4/10.
 */

public class TrackItem {
    private int trackItemId;
    private int userId;
    private int itemId;

    public TrackItem(int trackItemId, int userId, int itemId) {
        this.trackItemId = trackItemId;
        this.userId = userId;
        this.itemId = itemId;
    }

    public int getTrackItemId() {

        return trackItemId;
    }

    public void setTrackItemId(int trackItemId) {
        this.trackItemId = trackItemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
