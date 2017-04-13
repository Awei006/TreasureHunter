package com.awei.info;

public class Question {
    private int questionId;
    private int itemId;
    private int userId;
    private String questionDescription;
    private String questionAnswer;
    private String questionTime;

    public Question(int questionId, int itemId, int userId, String questionDescription, String questionAnswer, String questionTime) {
        this.questionId = questionId;
        this.itemId = itemId;
        this.userId = userId;
        this.questionDescription = questionDescription;
        this.questionAnswer = questionAnswer;
        this.questionTime = questionTime;
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

    public String getQuestiontime() {
        return questionTime;
    }

    public void setQuestiontime(String questiontime) {
        this.questionTime = questiontime;
    }
}
