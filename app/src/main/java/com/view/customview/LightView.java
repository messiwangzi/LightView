package com.view.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 自绘控件
 */
public class LightView extends View
{
    private Paint paint;

    private Rect bounds; //用于获取文字的高度

    private int count = 0; //倒计时的时间

    private int color = Color.LTGRAY;

    private boolean isShowCount = false;

    private CountDownTimer countDownTimer; //计时器

    public LightView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        bounds = new Rect();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    /**
     * 绘制代码写在onDraw()
     *
     * @param canvas 靠他来画
     */
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        paint.setColor(color);
        //绘制圆
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, paint);

        if (isShowCount)
        {
            paint.setColor(Color.BLACK);
            paint.setTextSize(100);

            String showText = String.valueOf(count);
            //这里获取showText文字的宽度和高度
            paint.getTextBounds(showText, 0, showText.length(), bounds);
            float textWidth = bounds.width();
            float textHeight = bounds.height();

            //绘制倒计时文字
            canvas.drawText(showText, getWidth() / 2 - textWidth / 2, getHeight() / 2 + textHeight / 2, paint);
        }
    }

    /**
     * 设置开关灯
     *
     * @param isOpen      是否打开
     * @param color       打开什么颜色
     * @param isShowCount 是否显示倒计时
     * @param count       显示时间的长度
     */
    public void setSwitchLight(boolean isOpen, int color, boolean isShowCount, int count)
    {
        if (isOpen)
        {
            this.color = color;
            this.isShowCount = isShowCount;
            this.count = count;
            countDownTimer = new CountDownTimer(count * 1000, 1000)
            {
                @Override
                public void onTick(long millisUntilFinished)
                {
                    invalidate();
                    LightView.this.count--;
                }

                @Override
                public void onFinish()
                {

                }
            };
            countDownTimer.start();
        } else
        {
            this.color = Color.LTGRAY;
            this.isShowCount = false;
        }
        invalidate();
    }
}
