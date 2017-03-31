package com.awei.info;

import java.sql.Date;


public class Question {
    private int questionId;
    private int itemId;
    private int userId;
    private String description;
    private Date time;

    public Question(int questionId, int itemId, int userId, String description, Date time) {
        this.questionId = questionId;
        this.itemId = itemId;
        this.userId = userId;
        this.description = description;
        this.time = time;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
