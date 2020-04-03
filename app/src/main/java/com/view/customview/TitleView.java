package com.view.customview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TitleView extends FrameLayout
{

    private Button back;

    private TextView title;

    public TitleView(@NonNull Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title, this);
        back = findViewById(R.id.bt_back);
        title = findViewById(R.id.tv_title);
        back.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ((Activity) getContext()).finish();
            }
        });
    }

    public void setBackText(String backText)
    {
        this.back.setText(backText);
    }

    public void setTitleText(String titleText)
    {
        this.title.setText(titleText);
    }

    public void setBackButtonOnClickListener(OnClickListener listener)
    {
        back.setOnClickListener(listener);
    }
}
