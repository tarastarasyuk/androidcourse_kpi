package com.tarastarasiuk.androidcourse.model;

import java.util.Date;

public class TrackedResultModel {

    private Long id;
    private String result;
    private Date date;

    public TrackedResultModel(Long id, String result, Date date) {
        this.id = id;
        this.result = result;
        this.date = date;
    }

    public TrackedResultModel(String result, Date date) {
        this.result = result;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getResult() {
        return result;
    }

    public Date getDate() {
        return date;
    }
}
