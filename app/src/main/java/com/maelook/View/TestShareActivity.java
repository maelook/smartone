package com.maelook.View;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.maelook.R;

public class TestShareActivity extends Activity implements View.OnClickListener {
    private RelativeLayout mRlShare;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        bindView();
    }

    private void bindView() {
        mRlShare = (RelativeLayout) findViewById(R.id.rl_share);

        mRlShare.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(TestShareActivity.this,ShareActivity.class));
    }
}
