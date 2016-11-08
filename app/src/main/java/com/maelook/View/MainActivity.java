package com.maelook.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.maelook.R;
import com.maelook.Utils.dataBaseUtil;
import com.maelook.Utils.recordToText;
import com.maelook.Widget.maeChartFragment.spectralCurveChart;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private dataBaseUtil db;
    private recordToText recordToText;
    private Button enter;
    private spectralCurveChart res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        res = (spectralCurveChart) findViewById(R.id.res);
        enter = (Button) findViewById(R.id.enterData);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, dataBaseTest.class);
                startActivity(i);
            }
        });

        findViewById(R.id.data1).setOnClickListener(this);
        findViewById(R.id.data2).setOnClickListener(this);
        findViewById(R.id.data3).setOnClickListener(this);
        findViewById(R.id.data4).setOnClickListener(this);


//        new recordToText().write("testting hello1!");
//        try {
//            new Thread().sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        new recordToText().write("testting hello2!");
//        try {
//            new Thread().sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        new recordToText().write("testting hello3!");
//        try {
//            new Thread().sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        new recordToText().write("testting hello4!");
//        //TODO 还得弄好说明文档，便于别人使用
//
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this.getApplicationContext(),"record",null);
//        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
//        DaoSession session = daoMaster.newSession();
//        singleRecordDao singleRecordDao = session.getSingleRecordDao();

//        dataBaseUtil fog = new dataBaseUtil(this,"record");
//        singleRecordDao manager = (com.maelook.daoBean.singleRecordDao) fog.getSingleRecordManager();

//        singleRecord record2 = new singleRecord();
//        record2.setCqs("this is testinagg string1!");
//        record2.setId(3);
//        singleRecordDao.insert(record2);
//        singleRecord record3 = new singleRecord();
//        record3.setCqs("this is testing t4sstring2!");
//        record3.setId(4);
//        singleRecordDao.insert(record3);
//        double a = System.currentTimeMillis();
//        for (int i=2001 ; i< 20000 ; i++) {
//            singleRecord record = new singleRecord();
//            record.setId(i);
//            singleRecordDao.insert(record);
//        }
//        Log.e("TAG","time end:"+(System.currentTimeMillis()-a));

//        double b = System.currentTimeMillis();
//        List<singleRecord> singleRecord = singleRecordDao.queryBuilder().where(com.maelook.daoBean.singleRecordDao.Properties.Id.isNotNull()).build().list();
//        Log.e("TAG","time end:"+(System.currentTimeMillis()-b)+"and get size:"+singleRecord.size());
//        for (singleRecord s:singleRecord) {
////            Toast.makeText(getApplicationContext(),s.getId()+"for",Toast.LENGTH_SHORT).show();
//            Log.e("TAG",s.getId()+"  ");
//        }

//        double b = System.currentTimeMillis();
//        for (int j= 0 ;j < 19999 ; j++) {
//            singleRecord deleteRecord = manager.queryBuilder().where(com.maelook.daoBean.singleRecordDao.Properties.Id.eq(j)).build().unique();
//            if (deleteRecord!= null){
//                manager.delete(deleteRecord);
//                Log.e("TAG","deleted");
//            }
//        }
//        Log.e("TAG","time end:"+(System.currentTimeMillis()-b));

//        singleRecord singleRecord2 = singleRecordDao.queryBuilder().where(com.maelook.daoBean.singleRecordDao.Properties.Cqs.like("this is testing string!")).build().unique();
//        if (singleRecord2!= null){
//            Log.e("TAG","deleted2");
//            Toast.makeText(getApplicationContext(),singleRecord2.getCqs(),Toast.LENGTH_SHORT).show();
//        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.data1:
                res.Refresh(1);
                break;
            case R.id.data2:
                res.Refresh(2);
                break;
            case R.id.data3:
                res.Refresh(3);
                break;
            case R.id.data4:
                Intent i = new Intent(MainActivity.this,testView.class);
                startActivity(i);
                break;
        }
    }
}
