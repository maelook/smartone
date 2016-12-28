package com.maelook.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by andrew on 2016/12/27.
 */
@Entity
public class single {

    @Id
    private String date;
    private String location;
    private String filepath;
    private String imgpath;
    private String record;


    public single() {
    }


    @Generated(hash = 1086534003)
    public single(String date, String location, String filepath, String imgpath,
            String record) {
        this.date = date;
        this.location = location;
        this.filepath = filepath;
        this.imgpath = imgpath;
        this.record = record;
    }


    public String getDate() {
        return this.date;
    }


    public void setDate(String date) {
        this.date = date;
    }


    public String getLocation() {
        return this.location;
    }


    public void setLocation(String location) {
        this.location = location;
    }


    public String getFilepath() {
        return this.filepath;
    }


    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }


    public String getImgpath() {
        return this.imgpath;
    }


    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }


    public String getRecord() {
        return this.record;
    }


    public void setRecord(String record) {
        this.record = record;
    }






}
