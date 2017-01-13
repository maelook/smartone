package com.maelook.View.db;
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

public class SingDataActivity extends Activity {
    private List<String> mData = new ArrayList<>();
    private ListView mListView;
    private MyListViewAdapter mAdapter;
    private CustomDialog dialog;
    //自定义变量
    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;
    private ImageView takePhotoBn;
    private Uri imageUri; //图片路径
    private String filename; //图片名称
    private GuideUtil guideUtil = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyAppCompat);
        setContentView(R.layout.activity_sing_data);
        initView();


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
                    Intent i = new Intent(parent.getContext(),ShowSpectralCurveChartActivity    .class);
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

    public void initView(){

        takePhotoBn = (ImageView) this.findViewById(R.id.button1);
        takePhotoBn = new ImageView(this);
        /*takePhotoBn.set*/
    }
    public void TakeAPhoto(View view){
        //图片名称 时间命名
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date date = new Date(System.currentTimeMillis());
        filename = format.format(date);
        //创建File对象用于存储拍照的图片 SD卡根目录
        //存储至appDocument文件夹


    /*    File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);*/
        File outputImage = new File(appDocument+File.separator+"IMG_"+filename+".jpg");
        try {
            if(outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch(IOException e) {
            e.printStackTrace();
        }
        //将File对象转换为Uri并启动照相程序
        imageUri = Uri.fromFile(outputImage);
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE"); //照相
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri); //指定图片输出地址
        startActivityForResult(intent,TAKE_PHOTO); //启动照相
        //拍完照startActivityForResult() 结果返回onActivityResult()函数
    }

    /**
     * 因为两种方式都用到了startActivityForResult方法
     * 这个方法执行完后都会执行onActivityResult方法, 所以为了区别到底选择了那个方式获取图片要进行判断
     * 这里的requestCode跟startActivityForResult里面第二个参数对应
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            Toast.makeText(SingDataActivity.this, "ActivityResult resultCode error", Toast.LENGTH_SHORT).show();
            return;
        }
        switch(requestCode) {
            case TAKE_PHOTO:
                Intent intent = new Intent("com.android.camera.action.CROP"); //剪裁
                intent.setDataAndType(imageUri, "image/*");
                intent.putExtra("scale", true);
                //设置宽高比例
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                //设置裁剪图片宽高
                intent.putExtra("outputX", 340);
                intent.putExtra("outputY", 340);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                Toast.makeText(SingDataActivity.this, "剪裁图片", Toast.LENGTH_SHORT).show();
                //广播刷新相册
                Intent intentBc = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                intentBc.setData(imageUri);
                this.sendBroadcast(intentBc);
                startActivityForResult(intent, CROP_PHOTO); //设置裁剪参数显示图片至ImageView
                break;
            case CROP_PHOTO:
                try {
                    //图片解析成Bitmap对象
                    Bitmap bitmap = BitmapFactory.decodeStream(
                            getContentResolver().openInputStream(imageUri));
                    Toast.makeText(SingDataActivity.this, imageUri.toString(), Toast.LENGTH_SHORT).show();
                    Log.e("aaa","Java"+bitmap);
                    if (bitmap != null) {
                        takePhotoBn.setImageBitmap(bitmap); //将剪裁后照片显示出来
                    }
                } catch(FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
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

}
