package com.maelook.Interface;


/**
 * Created by Andrew on 2016/10/26.
 */

import com.maelook.Bean.singleRecord;

/**
    * 由于数据库不支持直接存储数据直接存储为数组形式，但为了能够合理转换，给出这个接口
    * 不直接固定的理由是：数据库可能在任何位置读取，如果固定在class中，那么引用的对象必定会持有一个对象引用，有可能造成内存管理上的问题
    * 而直接给出接口，可以直接以参数的形式完成数据解析ihe沟通，没有持有引用，代码解耦，灵活多变
    *
    * 以下方法，由命名即可理解，不做解释。
    * */

public interface recordParsing {

    float[] QaParsing(singleRecord singleRecord);

    int[] cqsParsing(singleRecord singleRecord);

    String _24ColorParsing(singleRecord singleRecord);

}
