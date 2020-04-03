package com.view.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class CustomListView extends ListView implements View.OnTouchListener, GestureDetector.OnGestureListener
{
    private static final String TAG = "CustomListView";

    private IDeleteListener iDeleteListener;

    //手势检测器
    private GestureDetector gestureDetector;

    //是否已经删除此item
    private boolean isDeleteShown = false;

    //当前手指选择的item
    private int selectedItem;

    private View deleteButton;

    private ViewGroup itemLayout;


    public CustomListView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        gestureDetector = new GestureDetector(context, this);
        setOnTouchListener(CustomListView.this);
    }

    public void setiDeleteListener(IDeleteListener iDeleteListener)
    {
        this.iDeleteListener = iDeleteListener;
    }

    /**
     * 触摸到屏幕就会回调onTouch
     */
    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        Log.d(TAG, "onTouch() is need delete?" + deleteButton);
        //item是否需要删除
        if (isDeleteShown)
        {
            itemLayout.removeView(deleteButton);
            deleteButton = null;
            isDeleteShown = false;
            return false;
        } else
        {
            return gestureDetector.onTouchEvent(event);
        }
    }

    /**
     * 当手指按下时,回调onDown
     */
    @Override
    public boolean onDown(MotionEvent e)
    {
        if (!isDeleteShown)
        {
            selectedItem = pointToPosition((int) e.getX(), (int) e.getY());
        }
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e)
    {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e)
    {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
    {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e)
    {

    }

    /**
     * 当手指快速滑动时回调onFling()
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
    {
        if (!isDeleteShown && Math.abs(velocityX) > Math.abs(velocityY))
        {
            deleteButton = LayoutInflater.from(getContext()).inflate(
                    R.layout.delete_button, null);
            deleteButton.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    itemLayout.removeView(deleteButton);
                    deleteButton = null;
                    isDeleteShown = false;
                    iDeleteListener.onDeleteItem(selectedItem);
                }
            });

            /**
             *在当前显示的ListView中,选中的item的位置-当前最上层的item的位置=index
             * 通过getChildAt方法获得item
             * */
            itemLayout = (ViewGroup) getChildAt(selectedItem - getFirstVisiblePosition());

            //设置delete Button在itemGroup中的位置
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            itemLayout.addView(deleteButton, params);

            isDeleteShown = true;
        }
        return false;
    }
}
