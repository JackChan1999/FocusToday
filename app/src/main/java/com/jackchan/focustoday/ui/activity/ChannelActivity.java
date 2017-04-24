package com.jackchan.focustoday.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jackchan.focustoday.R;
import com.jackchan.commons.adapter.ChannelAdapter;
import com.jackchan.focustoday.ui.widget.itemtouchhelper.ItemDragHelperCallback;
import com.jackchan.focustoday.utils.CommonUtil;
import com.jackchan.focustoday.utils.GsonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.jackchan.focustoday.R.id.toolbar;

/**
 * ============================================================
 * Copyright：Google有限公司版权所有 (c) 2017
 * Author：   陈冠杰
 * Email：    815712739@qq.com
 * GitHub：   https://github.com/jackchan1999
 * 博客：     http://blog.csdn.net/axi295309066
 * 微博：     AndroidDeveloper
 * <p>
 * Project_Name：SmartCity
 * Package_Name：com.jackchan.focustoday
 * Version：1.0
 * time：2016/2/16 10:06
 * des ：${TODO}
 * gitVersion：$Rev$
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 **/
public class ChannelActivity extends AppCompatActivity {

    private RecyclerView   mRecyclerView;
    private Toolbar        mToolbar;
    private ChannelAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_channel);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mToolbar = (Toolbar) findViewById(toolbar);
        mToolbar.setTitle("");

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        GridLayoutManager manager = new GridLayoutManager(this, 4);
        mRecyclerView.setLayoutManager(manager);

        ItemDragHelperCallback callback = new ItemDragHelperCallback();
        final ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecyclerView);

        mAdapter = new ChannelAdapter(this, helper, getMyChannel(), getOtherChannel());
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = mAdapter.getItemViewType(position);
                return viewType == ChannelAdapter.TYPE_MY || viewType == ChannelAdapter
                        .TYPE_OTHER ? 1 : 4;
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnMyChannelItemClickListener(new ChannelAdapter.OnMyChannelItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(ChannelActivity.this, mAdapter.getChannnelLst().get(position),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public List<String> getMyChannel() {
        SharedPreferences channel = getSharedPreferences("channel", MODE_PRIVATE);
        String mychannel = channel.getString("mychannel", "");

        if (!mychannel.equals("")) {
            return new Gson().fromJson(mychannel, new TypeToken<List<String>>() {
            }.getType());
        } else {
            return new ArrayList<>(Arrays.asList(CommonUtil.getStringArr(R.array.mychannel)));
        }
    }

    public List<String> getOtherChannel() {
        SharedPreferences channelSP = getSharedPreferences("channel", MODE_PRIVATE);
        String otherchannel = channelSP.getString("otherchannel", "");

        if (!otherchannel.equals("")) {
            return new Gson().fromJson(otherchannel, new TypeToken<List<String>>() {
            }.getType());
        } else {
            return new ArrayList<>(Arrays.asList(CommonUtil.getStringArr(R.array.otherchannel)));
        }
    }

    public void saveChannel() {
        SharedPreferences channelSP = getSharedPreferences("channel", MODE_PRIVATE);
        SharedPreferences.Editor editor = channelSP.edit();
        editor.putString("mychannel", GsonUtil.toJson(mAdapter.getChannnelLst()));
        editor.putString("otherchannel", GsonUtil.toJson(mAdapter.getOtherChannnelLst()));
        editor.apply();
    }

    @Override
    public void finish() {
        saveChannel();
        Intent data = new Intent();
        data.putExtra("isDataChange",mAdapter.isDataChanged());
        setResult(0,data);
        super.finish();
    }
}
