package com.jackchen.focustoday.base.menudetail;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

import com.jackchen.focustoday.R;
import com.jackchen.focustoday.base.BaseMenuDetailPager;
import com.jackchen.focustoday.bean.PhotosData;
import com.jackchen.focustoday.bean.PhotosData.PhotoInfo;
import com.jackchen.focustoday.http.Constants;
import com.jackchen.focustoday.http.okhttputils.OkHttpUtils;
import com.jackchen.focustoday.http.okhttputils.callback.StringCallback;
import com.jackchen.focustoday.utils.CacheUtils;
import com.jackchen.focustoday.utils.GsonUtil;
import com.jackchen.focustoday.utils.UIUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * ============================================================
 * Copyright：Google有限公司版权所有 (c) 2017
 * Author：   陈冠杰
 * Email：    815712739@qq.com
 * GitHub：   https://github.com/JackChen1999
 * 博客：     http://blog.csdn.net/axi295309066
 * 微博：     AndroidDeveloper
 * <p>
 * Project_Name：SmartCity
 * Package_Name：com.jackchen.focustoday
 * Version：1.0
 * time：2016/2/16 10:06
 * des ：${TODO}
 * gitVersion：$Rev$
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 **/
public class PhotoMenuDetailPager extends BaseMenuDetailPager {
	@BindView(R.id.recyclerview)
	RecyclerView mRecyclerView;

	private ArrayList<PhotoInfo> mNews;

	private ImageButton mButton;

	//private PhotoAdapter mAdapter;

	public PhotoMenuDetailPager(Activity mActivity, ImageButton btnPhoto) {
		super(mActivity);
		mButton = btnPhoto;
		mButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				changeDisplay();
			}
		});
	}

	@Override
	protected View initView() {
		View view = UIUtils.inflate(R.layout.menu_photo_pager);
		ButterKnife.bind(this, view);
		return view;
	}

	private Boolean isisListDisplay = true;// 是否是列表展示

	protected void changeDisplay() {
		if (isisListDisplay) {
			isisListDisplay = false;
			setGridAdapter();
			mButton.setImageResource(R.mipmap.icon_pic_list_type);
		} else {
			isisListDisplay = true;
			setListAdapter();
			mButton.setImageResource(R.mipmap.icon_pic_grid_type);
		}
	}

	@Override
	public void initData() {
	/*	String cache = CacheUtils.getCache(Constants.URLS.PHOTOS_URL, mActivity);
		if (!TextUtils.isEmpty(cache)) {

		}

		getDataFromServer();*/
	}

	public void getDataFromServer() {
		OkHttpUtils.get().url(Constants.URLS.PHOTOS_URL).build().execute(new StringCallback() {

			@Override
			public void onResponse(String result) {
				parseData(result);
				CacheUtils.setCache(Constants.URLS.PHOTOS_URL, result, mActivity);
			}

			@Override
			public void onError(Call arg0, Exception arg1) {
				Toast.makeText(mActivity, arg1.getMessage(), Toast.LENGTH_SHORT).show();
				arg1.printStackTrace();
			}
		});

	}

	protected void parseData(String result) {
		PhotosData photosData = GsonUtil.changeGsonToBean(result, PhotosData.class);

		mNews = photosData.data.news;// 组图数据
		if (mNews != null) {
			//mAdapter = new PhotoAdapter(mActivity, R.layout.list_photo_item, mNews);
			setListAdapter();

			/*
			 * mListView.setAdapter(mAdapter); mGridView.setAdapter(mAdapter);
			 */
		}

	}

	private void setListAdapter() {
		/*SlideInBottomAnimatorAdapter slideInBottomAnimatorAdapter = new SlideInBottomAnimatorAdapter<>(mAdapter,
				mRecyclerView);
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
		mRecyclerView.setLayoutManager(linearLayoutManager);
		mRecyclerView.setAdapter(slideInBottomAnimatorAdapter);*/
	}

	private void setGridAdapter() {
		/*StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,
				StaggeredGridLayoutManager.VERTICAL);
		// GridLayoutManager gridLayoutManager = new
		// GridLayoutManager(mActivity,2);
		mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
		mRecyclerView.setAdapter(mAdapter);*/
	}

	/*class PhotoAdapter extends CommonAdapter<PhotoInfo> {

		public PhotoAdapter(Context context, int layoutId, List<PhotoInfo> datas) {
			super(context, layoutId, datas);
		}

		@Override
		public void convert(ViewHolder holder, PhotoInfo info) {
			holder.setText(R.id.tv_title, info.title);
			Glide.with(mActivity).load(info.listimage).error(R.mipmap.news_pic_default)
					.into((ImageView) holder.getView(R.id.iv_pic));
		}

	}*/

}
