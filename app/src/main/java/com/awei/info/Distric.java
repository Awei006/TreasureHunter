package com.awei.info;

/**
 * Created by aaa86 on 2017/4/11.
 */

public class Distric {
    private int districId;
    private String districName;
    private int cityId;

    public Distric(int districId, String districName, int cityId) {
        this.districId = districId;
        this.districName = districName;
        this.cityId = cityId;
    }

    public int getDistricId() {

        return districId;
    }

    public void setDistricId(int districId) {
        this.districId = districId;
    }

    public String getDistricName() {
        return districName;
    }

    public void setDistricName(String districName) {
        this.districName = districName;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
