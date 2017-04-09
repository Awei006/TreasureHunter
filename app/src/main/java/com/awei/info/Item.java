package com.awei.info;

/**
 * Created by aaa86 on 2017/3/25.
 */

public class Item {

    public int itemId;
    public int userId;
    public String itemName;
    public String itemDescription;
    public int classId;

    public Item(int itemId, int userId, String itemName, String itemDescription, int classId) {
        this.itemId = itemId;
        this.userId = userId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.classId = classId;
    }
}
