package com.maelook.utils;

import android.content.Context;

import com.maelook.daoBean.DaoMaster;
import com.maelook.daoBean.DaoSession;

/**
 * Created by Andrew on 2016/10/26.
 */

//TODO 在以后尝试在app里面写上静态变量方法，避免每次需要创建的步骤

public class dataBaseUtil<T>{
    private static final String TAG = "dataBaseUtil";
    private DaoMaster.DevOpenHelper helper;
    private DaoMaster daoMaster;
    private DaoSession session;

    //TODO 如果在app中完成启动，那么就不必担心内存泄露
    public dataBaseUtil(Context context,String table) {
        this.helper = new DaoMaster.DevOpenHelper(context,table,null);
        this.daoMaster = new DaoMaster(this.helper.getWritableDatabase());
        this.session = this.daoMaster.newSession();
    }


    public Object getSingleRecordManager(){
        return this.session.getSingleRecordDao();
    }

    public void destoryed(){
        try {
            this.session.clear();
            this.daoMaster = null;
            this.helper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
