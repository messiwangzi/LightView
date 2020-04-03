package com.view.customview;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LightActivity extends AppCompatActivity
{
    private LightView redLight;

    private LightView yellowLight;

    private LightView greenLight;

    private static final int RED = 0;

    private static final int YELLOW = 1;

    private static final int GREEN = 2;

    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case RED:
                    redLight.setSwitchLight(true, Color.RED, true, 10);
                    yellowLight.setSwitchLight(false, Color.LTGRAY, false, 0);
                    greenLight.setSwitchLight(false, Color.LTGRAY, false, 0);
                    break;
                case YELLOW:
                    yellowLight.setSwitchLight(true, Color.YELLOW, true, 3);
                    redLight.setSwitchLight(false, Color.LTGRAY, false, 0);
                    greenLight.setSwitchLight(false, Color.LTGRAY, false, 0);
                    break;
                case GREEN:
                    greenLight.setSwitchLight(true, Color.GREEN, true, 10);
                    redLight.setSwitchLight(false, Color.LTGRAY, false, 0);
                    yellowLight.setSwitchLight(false, Color.LTGRAY, false, 0);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);
        initData();
        initView();
        setListener();
    }

    private void initData()
    {

    }

    private void initView()
    {
        redLight = findViewById(R.id.dv_red_light);
        yellowLight = findViewById(R.id.dv_yellow_light);
        greenLight = findViewById(R.id.dv_green_light);
    }

    private void setListener()
    {
        /**
         * step1.先亮红灯3s
         * step2.再亮黄灯1s
         * step3.最后亮绿灯3s
         * step4.循环1-3
         * */
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while (true)
                {
                    try
                    {
                        handler.sendEmptyMessage(RED);
                        Thread.sleep(10 * 1000);
                        handler.sendEmptyMessage(YELLOW);
                        Thread.sleep(3 * 1000);
                        handler.sendEmptyMessage(GREEN);
                        Thread.sleep(10 * 1000);
                        handler.sendEmptyMessage(YELLOW);
                        Thread.sleep(3 * 1000);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
