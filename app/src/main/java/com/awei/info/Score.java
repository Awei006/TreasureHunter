package com.awei.info;


public class Score {
    private int scoreId;
    private int requestId;
    private int userIdReq;
    private int userIdRes;
    private int score;
    private String scoreDescription;

    public Score(int scoreId, int requestId, int userIdReq, int userIdRes, int score, String scoreDescription) {
        this.scoreId = scoreId;
        this.requestId = requestId;
        this.userIdReq = userIdReq;
        this.userIdRes = userIdRes;
        this.score = score;
        this.scoreDescription = scoreDescription;
    }

    public int getScoreId() {

        return scoreId;
    }

    public void setScoreId(int scoreId) {
        this.scoreId = scoreId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getUserIdReq() {
        return userIdReq;
    }

    public void setUserIdReq(int userIdReq) {
        this.userIdReq = userIdReq;
    }

    public int getUserIdRes() {
        return userIdRes;
    }

    public void setUserIdRes(int userIdRes) {
        this.userIdRes = userIdRes;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getScoreDescription() {
        return scoreDescription;
    }

    public void setScoreDescription(String scoreDescription) {
        this.scoreDescription = scoreDescription;
    }
}
