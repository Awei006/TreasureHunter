package com.awei.info;

import java.sql.Date;


public class Request {
    private int requestId;
    private int itemIdReq;
    private int itemIdRes;
    private int groupId;
    private Date requestDate;

    public Request(int requestId, int itemIdReq, int itemIdRes, int groupId, Date requestDate) {
        this.requestId = requestId;
        this.itemIdReq = itemIdReq;
        this.itemIdRes = itemIdRes;
        this.groupId = groupId;
        this.requestDate = requestDate;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getItemIdReq() {
        return itemIdReq;
    }

    public void setItemIdReq(int itemIdReq) {
        this.itemIdReq = itemIdReq;
    }

    public int getItemIdRes() {
        return itemIdRes;
    }

    public void setItemIdRes(int itemIdRes) {
        this.itemIdRes = itemIdRes;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }
}
