package com.awei.info;

/**
 * Created by aaa86 on 2017/3/25.
 */

public class Item {

    public int itemId;
    public int userId;
    public String itemName;
    public String itemDescription;
    public String itemPicture;
    public int classId;

    public Item(int itemId, int userId, String itemName, String itemDescription, String itemPicture, int classId) {
        this.itemId = itemId;
        this.userId = userId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPicture = itemPicture;
        this.classId = classId;
    }
}
