package com.maelook.View.db;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.maelook.Adapter.MyAdapter;
import com.maelook.Adapter.summuryPageListViewAdapter;
import com.maelook.Bean.single;
import com.maelook.R;
import com.maelook.Utils.dataBiulderAndPraser;
import com.maelook.daoBean.DaoMaster;
import com.maelook.daoBean.DaoSession;
import com.maelook.daoBean.singleDao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static com.maelook.app.maelookApp.appDocument;

/**
 * Created by andrew on 2017/1/6.
 */

public class dbExample extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyAppCompat);
        setContentView(R.layout.activity_sing_data);


        DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(this,null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        DaoSession session = daoMaster.newSession();

        singleDao singleDao = session.getSingleDao();

        //insert into database
        dataBiulderAndPraser biulderAndPraser = new dataBiulderAndPraser();
        for (int i=1;i<2;i++){
            double[] t = new double[401];
            try {
                File file = new File(appDocument+File.separator+"data"+i+".txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                String line = "";
                int z = 0;
                while((line = reader.readLine()) != null){
                    t[z] = Double.parseDouble(line);
                    z++;
                }
                single single = new single();
                single.setRecord(biulderAndPraser.builderContent("",t));
                single.setFilepath(file.getPath());
                String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
                String day = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
                single.setDate(day+time);
                single.setLocation("china");
                singleDao.insert(single);
                single.setDate("2017-01-01");
                singleDao.insert(single);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        //query
        DaoMaster daoMaster1 = new DaoMaster(helper.getReadableDatabase());
        DaoSession session1 = daoMaster.newSession();
        singleDao singleDao1 = session1.getSingleDao();
        List  list= singleDao1.queryBuilder().build().list();
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new fogAdapter(list,this));
        Log.e("list","list size:"+list.size());

    }


    class fogAdapter extends BaseAdapter{

        private List data;
        private Context context;

        public fogAdapter(List data, Context context) {
            this.data = data;
            this.context = context;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {
            TextView left = null;
            TextView right = null;
            if (convertView == null) {
                convertView = convertView.inflate(this.context,R.layout.summury_page_attr,null);
                left = (TextView) convertView.findViewById(R.id.summury_page_left);
                right = (TextView) convertView.findViewById(R.id.summury_page_right);
                convertView.setTag(new ViewHolder(left,right));
            }else{
                ViewHolder v = (ViewHolder) convertView.getTag();
                left = v.getLeft();
                right = v.getRight();
            }
            final single single = (com.maelook.Bean.single) this.data.get(position);
            left.setText(single.getDate());
            right.setText(single.getFilepath());
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    double[] data = new dataBiulderAndPraser().praserFromSQLSingle(single.getRecord());
                    Intent i = new Intent(parent.getContext(),show.class);
                    Bundle bundle = new Bundle();
                    bundle.putDoubleArray("data",data);
                    i.putExtras(bundle);
                    Log.e("list","position:"+position);
                    parent.getContext().startActivity(i);
                }
            });
            return convertView;
        }


        class ViewHolder{
            private TextView left;
            private TextView right;

            public ViewHolder(TextView left, TextView right) {
                this.left = left;
                this.right = right;
            }

            public TextView getLeft() {
                return left;
            }

            public ViewHolder setLeft(TextView left) {
                this.left = left;
                return this;
            }

            public TextView getRight() {
                return right;
            }

            public ViewHolder setRight(TextView right) {
                this.right = right;
                return this;
            }
        }

    }



}
