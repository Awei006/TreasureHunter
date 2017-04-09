package com.awei.info;

import java.sql.Date;


public class Question {
    private int questionId;
    private int itemId;
    private int userId;
    private String questionDescription;
    private String questionAnswer;
    private Date questiontime;

    public Question(int questionId, int itemId, int userId, String questionDescription, String questionAnswer, Date questiontime) {
        this.questionId = questionId;
        this.itemId = itemId;
        this.userId = userId;
        this.questionDescription = questionDescription;
        this.questionAnswer = questionAnswer;
        this.questiontime = questiontime;
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

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public Date getQuestiontime() {
        return questiontime;
    }

    public void setQuestiontime(Date questiontime) {
        this.questiontime = questiontime;
    }
}
