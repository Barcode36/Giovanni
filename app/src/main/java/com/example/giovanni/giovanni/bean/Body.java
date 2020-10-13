package com.example.giovanni.giovanni.bean;

public class Body {

    static final int HEADER_TYPE = 0;
    static final int ITEM_TYPE = 1;

    private int type;
    private String title;
    private String description;

    public Body(int type, String title) {
        this.type = type;
        this.title = title;
    }

    public Body(int type, String title, String description) {
        this.type = type;
        this.title = title;
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}