package com.maelook.Utils;

import android.content.Context;

import com.maelook.daoBean.DaoMaster;
import com.maelook.daoBean.DaoSession;

/**
 * Created by Andrew on 2016/10/26.
 */

//TODO  记得注释

public class dataBaseUtil<T>{
    private static final String TAG = "dataBaseUtil";
    private DaoMaster.DevOpenHelper helper;
    private DaoMaster daoMaster;
    private DaoSession session;

    //TODO 这里有可能会导致context内存泄露，到时候检测一下
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
