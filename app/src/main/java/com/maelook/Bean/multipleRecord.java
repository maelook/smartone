package com.maelook.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Andrew on 2016/11/5.
 */

//TODO 写关于多次测量光谱的注释

@Entity
public class multipleRecord {

    @Id
    private String date;
    private String singleRecord1;
    private String singleRecord2;
    private String singleRecord3;
    private String singleRecord4;
    private String singleRecord5;
    private String singleRecord6;
    private String singleRecord7;
    private String singleRecord8;
    private String singleRecord9;
    private String singleRecord10;
    @Generated(hash = 1292081578)
    public multipleRecord(String date, String singleRecord1, String singleRecord2,
            String singleRecord3, String singleRecord4, String singleRecord5,
            String singleRecord6, String singleRecord7, String singleRecord8,
            String singleRecord9, String singleRecord10) {
        this.date = date;
        this.singleRecord1 = singleRecord1;
        this.singleRecord2 = singleRecord2;
        this.singleRecord3 = singleRecord3;
        this.singleRecord4 = singleRecord4;
        this.singleRecord5 = singleRecord5;
        this.singleRecord6 = singleRecord6;
        this.singleRecord7 = singleRecord7;
        this.singleRecord8 = singleRecord8;
        this.singleRecord9 = singleRecord9;
        this.singleRecord10 = singleRecord10;
    }
    @Generated(hash = 1550579526)
    public multipleRecord() {
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getSingleRecord1() {
        return this.singleRecord1;
    }
    public void setSingleRecord1(String singleRecord1) {
        this.singleRecord1 = singleRecord1;
    }
    public String getSingleRecord2() {
        return this.singleRecord2;
    }
    public void setSingleRecord2(String singleRecord2) {
        this.singleRecord2 = singleRecord2;
    }
    public String getSingleRecord3() {
        return this.singleRecord3;
    }
    public void setSingleRecord3(String singleRecord3) {
        this.singleRecord3 = singleRecord3;
    }
    public String getSingleRecord4() {
        return this.singleRecord4;
    }
    public void setSingleRecord4(String singleRecord4) {
        this.singleRecord4 = singleRecord4;
    }
    public String getSingleRecord5() {
        return this.singleRecord5;
    }
    public void setSingleRecord5(String singleRecord5) {
        this.singleRecord5 = singleRecord5;
    }
    public String getSingleRecord6() {
        return this.singleRecord6;
    }
    public void setSingleRecord6(String singleRecord6) {
        this.singleRecord6 = singleRecord6;
    }
    public String getSingleRecord7() {
        return this.singleRecord7;
    }
    public void setSingleRecord7(String singleRecord7) {
        this.singleRecord7 = singleRecord7;
    }
    public String getSingleRecord8() {
        return this.singleRecord8;
    }
    public void setSingleRecord8(String singleRecord8) {
        this.singleRecord8 = singleRecord8;
    }
    public String getSingleRecord9() {
        return this.singleRecord9;
    }
    public void setSingleRecord9(String singleRecord9) {
        this.singleRecord9 = singleRecord9;
    }
    public String getSingleRecord10() {
        return this.singleRecord10;
    }
    public void setSingleRecord10(String singleRecord10) {
        this.singleRecord10 = singleRecord10;
    }



}
