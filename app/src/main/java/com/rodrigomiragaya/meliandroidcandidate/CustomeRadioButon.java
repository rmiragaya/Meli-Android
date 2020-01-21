package com.rodrigomiragaya.meliandroidcandidate;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatRadioButton;

public class CustomeRadioButon extends AppCompatRadioButton {

    public CustomeRadioButon(Context context) {
        super(context);
    }

    public CustomeRadioButon(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomeRadioButon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        setButtonDrawable(null);
    }

}