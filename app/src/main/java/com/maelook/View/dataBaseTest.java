package com.maelook.View;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.maelook.Bean.singleRecord;
import com.maelook.R;
import com.maelook.Utils.dataBaseUtil;
import com.maelook.daoBean.singleRecordDao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Andrew on 2016/11/1.
 */

public class dataBaseTest extends Activity {

    private Button query;
    private Button add;
    private Button delete;
    private Button modify;
    private TextView show;
    private EditText input;
    private singleRecordDao manager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_show);

        init();


        query = (Button) findViewById(R.id.query);
        add = (Button) findViewById(R.id.add);
        delete = (Button) findViewById(R.id.delete);
        modify = (Button) findViewById(R.id.modify);
        show = (TextView) findViewById(R.id.show);
        input = (EditText) findViewById(R.id.input);

        //增
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String line = null;
                try {
                    line = input.getText().toString();
                } catch (Exception e) {
                    show.append("input is null or have same one object already!\n");
                    e.printStackTrace();
                }
                singleRecord temp = new singleRecord();
                temp.setCqs(line);
                temp.setDate(new SimpleDateFormat("yyyy年mm月dd日——hh:mm:ss").format(new Date()).toString());
                manager.insert(temp);
            }
        });
        //删
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singleRecord temp = new singleRecord();
                try {
                    temp.setCqs(input.getText().toString());
                    manager.delete(temp);
                } catch (Exception e) {
                    show.append("input is null or no this object\n");
                    e.printStackTrace();
                }
            }
        });

        //查
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<singleRecord> list = manager.queryBuilder().where(singleRecordDao.Properties.Cqs.isNotNull()).build().list();
                for(singleRecord s:list){
                    show.append("query:  "+"singleRecord: id="+s.getId()+" Cqs="+s.getCqs()+"\n");
                }
            }
        });

        //改
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.append("note:just modify the first object for simple\n");
                try {
                    singleRecord temp = manager.queryBuilder().where(singleRecordDao.Properties.Id.eq(1)).build().unique();
                    show.append("before modify: id="+temp.getId()+"  Cqs="+temp.getCqs()+"\n");
                    temp.setCqs(input.getText().toString());
                    manager.update(temp);
                    show.append("after modify: ID="+temp.getId()+" Cqs="+temp.getCqs()+"\n");
                } catch (Exception e) {
                    show.append("error happen\n");
                    e.printStackTrace();
                }

            }
        });
    }

    private void init() {
        dataBaseUtil fog = new dataBaseUtil(this.getApplicationContext(),"record");
        this.manager = (com.maelook.daoBean.singleRecordDao) fog.getSingleRecordManager();
    }
}
