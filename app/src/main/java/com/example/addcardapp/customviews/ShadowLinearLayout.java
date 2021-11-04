package com.example.addcardapp.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.addcardapp.R;
import com.example.addcardapp.utils.Helper;

public class ShadowLinearLayout extends LinearLayout {
    public ShadowLinearLayout(Context context) {
        super(context);
        initBackground();
    }

    public ShadowLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initBackground();
    }

    public ShadowLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initBackground();
    }

    private void initBackground() {

        setBackground(Helper.generateBackgroundWithShadow(this, R.color.white,
                R.dimen.radiuscornerborder,R.color.shadowblack,R.dimen.elevation, Gravity.BOTTOM));
    }
}
