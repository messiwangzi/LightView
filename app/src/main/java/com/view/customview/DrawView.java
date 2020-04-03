package com.view.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawView extends View implements View.OnClickListener
{
    private Paint paint;

    public DrawView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        setOnClickListener(this);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, paint);
    }

    @Override
    public void onClick(View v)
    {
        paint.setColor(Color.BLUE);
        invalidate();
    }
}
