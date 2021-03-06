package com.maelook.View;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.maelook.Adapter.MyListViewAdapter;
import com.maelook.Bean.single;
import com.maelook.R;
import com.maelook.Utils.GuideUtil;
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
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.maelook.app.maelookApp.appDocument;

public class DataComparisonActivity extends Activity {
    private List<String> mData = new ArrayList<>();
    private ListView mListView;
    private MyListViewAdapter mAdapter;
    private CustomDialog dialog;
    private ImageView takePhotoBn;
    String ImageURL;
    private String filename; //图片名称
    Bitmap myBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyAppCompat);
        setContentView(R.layout.activity_data_comparison);
        initView();


        //图片名称 时间命名
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date date = new Date(System.currentTimeMillis());
        filename = format.format(date);
        //存储至appDocument文件夹
        ImageURL=appDocument+File.separator+"IMG_"+filename+".jpg";

        mListView = (ListView) findViewById(R.id.listView);
        //添加数据
        for (int i = 1; i < 30; i++) {
            mData.add("" + i);
        }
        mAdapter = new MyListViewAdapter(this, mData);
        mListView.setAdapter(mAdapter);
        //长按删除item
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showLostFindDialog(position);
                mListView.setBackgroundResource(R.color.white);
                return false;
            }
        });

        DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(this, null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        DaoSession session = daoMaster.newSession();

        singleDao singleDao = session.getSingleDao();

        //insert into database
        dataBiulderAndPraser biulderAndPraser = new dataBiulderAndPraser();
        for (int i = 1; i < 2; i++) {
            double[] t = new double[401];
            try {
                File file = new File(appDocument + File.separator + "data" + ".txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                String line = "";
                int z = 0;
                while ((line = reader.readLine()) != null) {
                    t[z] = Double.parseDouble(line);
                    z++;
                }
                single single = new single();
                single.setRecord(biulderAndPraser.builderContent("", t));
                single.setFilepath(file.getName());
                String time = new SimpleDateFormat("_HHmm").format(new java.util.Date());
                String day = new SimpleDateFormat("yyyy/MM/dd").format(new java.util.Date());
                single.setDate(day + time);
                single.setLocation("china");
                singleDao.insert(single);
                single.setDate("2017/01/07_01:30");
                singleDao.insert(single);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            //query
            DaoMaster daoMaster1 = new DaoMaster(helper.getReadableDatabase());
            DaoSession session1 = daoMaster.newSession();
            singleDao singleDao1 = session1.getSingleDao();
            List list = singleDao1.queryBuilder().build().list();
            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(new fogAdapter(list, this));
            Log.e("list", "list size:" + list.size());
        }
    }

    class fogAdapter extends BaseAdapter {

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
                convertView = convertView.inflate(this.context,R.layout.data,null);
                left = (TextView) convertView.findViewById(R.id.page_left);
                right = (TextView) convertView.findViewById(R.id.page_right);
                convertView.setTag(new fogAdapter.ViewHolder(left,right));
            }else{
              fogAdapter.ViewHolder v = (fogAdapter.ViewHolder) convertView.getTag();
                left = v.getLeft();
                right = v.getRight();
            }
            final single single = (com.maelook.Bean.single) this.data.get(position);
            left.setText(single.getDate());
            right.setText(single.getFilepath());
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

            public fogAdapter.ViewHolder setLeft(TextView left) {
                this.left = left;
                return this;
            }

            public TextView getRight() {
                return right;
            }

            public fogAdapter.ViewHolder setRight(TextView right) {
                this.right = right;
                return this;
            }
        }

    }

    public void initView(){

        takePhotoBn = (ImageView) this.findViewById(R.id.button1);

    }
    public void TakeAPhoto(View view){
        //将File对象转换为Uri并启动照相程序
        Intent cameraIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(new File(ImageURL)));
        startActivityForResult(cameraIntent, 0);
        //拍完照startActivityForResult() 结果返回onActivityResult()函数
    }

    /**
     * 因为两种方式都用到了startActivityForResult方法
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0){
            Log.d("requestCode", "Need 0");
            if(resultCode == RESULT_OK){
                Log.d("resultCode", "OK!!!" + ImageURL);
                myBitmap = BitmapFactory.decodeFile(ImageURL);
                Toast.makeText(DataComparisonActivity.this, "图片位置---"+ImageURL.toString(), Toast.LENGTH_SHORT).show();
                takePhotoBn.setImageBitmap(myBitmap);
                Log.e("image","pic-------"+myBitmap);
            }else{
                Log.d("resultCode", "" + resultCode);
            }
        }else{
            Log.d("requestCode", "Not Need");
        }
    }


    /**
     * 显示提示窗口
     *
     * @param position
     */
    protected void showLostFindDialog(final int position) {
        dialog = new CustomDialog(this, R.style.mystyle,
                R.layout.dialog, position);
        dialog.show();

    }
    public void btn_my_launcher(View view){
        finish();
    }

    /**
     * 自定义dialog
     */
    class CustomDialog extends Dialog implements
            View.OnClickListener {

        /**
         * 布局文件
         **/
        int layoutRes;

        /**
         * 上下文对象
         **/
        Context context;

        /**
         * 取消按钮
         **/
        private Button bt_cancal;

        /**
         * 删除按钮
         **/
        private Button bt_delect;

        /**
         * 收获地址id
         */
        private int postion;

        public CustomDialog(Context context) {
            super(context);
            this.context = context;
        }

        /**
         * 自定义布局的构造方法
         *
         * @param context
         * @param resLayout
         */
        public CustomDialog(Context context, int resLayout) {
            super(context);
            this.context = context;
            this.layoutRes = resLayout;
        }

        /**
         * 自定义主题及布局的构造方法
         *
         * @param context
         * @param theme
         * @param resLayout
         * @param postion
         */
        public CustomDialog(Context context, int theme, int resLayout,
                            int postion) {
            super(context, theme);
            this.context = context;
            this.layoutRes = resLayout;
            this.postion = postion;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // 指定布局
            this.setContentView(layoutRes);

            // 根据id在布局中找到控件对象
            bt_cancal = (Button) findViewById(R.id.bt_cancal);
            bt_delect = (Button) findViewById(R.id.bt_delect);

            // 为按钮绑定点击事件监听器
            bt_cancal.setOnClickListener(this);
            bt_delect.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                // 删除按钮
                case R.id.bt_delect:
                    // 删除数据
                    deleteItem(postion);
                    dialog.dismiss();
                    break;

                // 取消按钮
                case R.id.bt_cancal:
                    dialog.dismiss();

                default:
                    break;
            }
        }
    }

    /**
     * 删除ListView中的数据
     *
     * @param postion item的位置
     */
    private void deleteItem(int postion) {
        mData.remove(postion);
        mAdapter.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
    public void btn_MyDataComparison(View view){
        finish();
    }

    public void MyDataComparison(View view){
        Intent intent=new Intent(DataComparisonActivity.this,FirstActivity.class);
        startActivity(intent);

    }
    public void next_Going(View view){
        Intent intent=new Intent(DataComparisonActivity.this,DataMapParamActivity.class);
        startActivity(intent);
    }
}