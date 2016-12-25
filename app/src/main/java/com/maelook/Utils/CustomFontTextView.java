package com.maelook.Utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Daiwilliam on 2016-12-25.
 */

public class CustomFontTextView extends TextView {
    public CustomFontTextView(Context context) {
        super(context);
        init(context);
    }

    public CustomFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context){
        AssetManager assetManager=context.getAssets();
        Typeface font=Typeface.createFromAsset(assetManager,"fonts/font/font_gbk.ttf");
    }


}
