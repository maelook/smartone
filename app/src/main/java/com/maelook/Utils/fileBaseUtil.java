package com.maelook.utils;

/**
 * Created by Andrew on 2016/10/30.
 */

public abstract class fileBaseUtil {

    public fileBaseUtil() {
        order();
    }

    private void order() {
        ready();
        while(true){
            if (doing() != true){
                break;
            }
        }

        comleted();
    }

    //准备方法
    public abstract boolean ready();

    //执行中
    public abstract boolean doing();

    //完成时
    public abstract boolean comleted();

}
