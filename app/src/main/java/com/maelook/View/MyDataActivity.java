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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.maelook.Adapter.MyListViewAdapter;
import com.maelook.R;
import com.maelook.Utils.GuideUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.maelook.app.maelookApp.appDocument;

public class MyDataActivity extends Activity {
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
        setContentView(R.layout.activity_mydata);
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
    }
    public void initView(){
        takePhotoBn = (ImageView) findViewById(R.id.button1);

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
            Toast.makeText(MyDataActivity.this, "ActivityResult resultCode error", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(MyDataActivity.this, "剪裁图片", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(MyDataActivity.this, imageUri.toString(), Toast.LENGTH_SHORT).show();
                    Log.e("aaa","Java"+bitmap);
                    takePhotoBn.setImageBitmap(bitmap); //将剪裁后照片显示出来
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