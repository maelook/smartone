package com.maelook.View;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.maelook.R;
import com.maelook.Utils.GuideUtil;

public class MainActivity extends Activity {
	private GuideUtil guideUtil = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guide_layout);

		guideUtil = GuideUtil.getInstance();

		guideUtil.initGuide(this, R.drawable.newguide4);

		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				guideUtil.initGuide(MainActivity.this, R.drawable.newguide4);
			}
		});

		findViewById(R.id.button2).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				guideUtil.setFirst(false);
				guideUtil.initGuide(MainActivity.this, R.drawable.newguide5);
			}
		});
	}
}
