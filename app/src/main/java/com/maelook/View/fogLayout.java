package com.maelook.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Daiwilliam on 2016/12/13.
 */

public class fogLayout extends LinearLayout {
    public fogLayout(Context context) {
        super(context);
    }

    public fogLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }
}


