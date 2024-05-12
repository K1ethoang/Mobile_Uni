package com.hoanggiakiet.test.models;

import android.os.Parcelable;

import java.io.Serializable;

public class Category implements Serializable {
    private int CateId;
    private String CateName;

    public Category(int cateId, String cateName) {
        CateId = cateId;
        CateName = cateName;
    }

    public int getCateId() {
        return CateId;
    }

    public void setCateId(int cateId) {
        CateId = cateId;
    }

    public String getCateName() {
        return CateName;
    }

    public void setCateName(String cateName) {
        CateName = cateName;
    }
}
