package com.maelook.View;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import com.maelook.R;
import java.io.File;


import static com.maelook.app.maelookApp.appDocument;

public class ShareActivity extends Activity{
    private RelativeLayout mRlShareSingleimage;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        mContext = this;
        bindView();
    }

    private void bindView() {
        mRlShareSingleimage = (RelativeLayout) findViewById(R.id.rl_share_singleimage);
        mRlShareSingleimage.setOnClickListener(new ShareSingleImage());
    }

    //分享图片至所有第三方软件
    public class ShareSingleImage implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            File f = new File(appDocument+"/1.jpg");
            Log.e("f","aaa"+f);
            System.out.print(f);
            Uri uri = Uri.fromFile(f);
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
            shareIntent.putExtra(Intent.EXTRA_SUBJECT,"分享");
            shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            shareIntent.setType("image/*");
            startActivity(Intent.createChooser(shareIntent,getTitle()));
        }
    }


    }
