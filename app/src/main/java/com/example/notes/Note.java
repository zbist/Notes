package com.example.notes;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Note{

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private String  id;
    private String name;
    private String content;
    private String date;

    public Note(String name, String content, Date date) {
        this.name = name;
        this.content = content;
        this.date = format.format(date);
    }

    public Note(String name, String content, String date) {
        this.name = name;
        this.content = content;
        this.date = date;
    }

    public Note(String id, String name, String content, String date) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = format.format(date);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
