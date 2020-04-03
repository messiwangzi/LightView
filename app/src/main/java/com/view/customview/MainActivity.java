package com.view.customview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义View的实现由三种方法
 * 1.自绘
 * 2.组合
 * 3.集成
 */
public class MainActivity extends AppCompatActivity
{

    private CustomListView customListView;
    private DogAdapter dogAdapter;

    private List<String> dognameList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        setListener();
    }

    private void initData()
    {
        dognameList = new ArrayList<>();
        dognameList.add("拉布拉多");
        dognameList.add("德国牧羊犬");
        dognameList.add("哈士奇");
        dognameList.add("柯基");
        dognameList.add("金毛");
        dognameList.add("中华田园犬");
        dognameList.add("柴犬");
    }

    private void initView()
    {
        customListView = findViewById(R.id.clv_dogs);
        dogAdapter = new DogAdapter(this, 0, dognameList);
        customListView.setiDeleteListener(new IDeleteListener()
        {
            @Override
            public void onDeleteItem(int selectedItemPosition)
            {
                dognameList.remove(selectedItemPosition);
                dogAdapter.notifyDataSetChanged();

            }
        });
        customListView.setAdapter(dogAdapter);
    }

    private void setListener()
    {

    }
}
