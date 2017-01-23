package com.google.smartcity.base.menudetail;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.smartcity.R;
import com.google.smartcity.base.BaseMenuDetailPager;
import com.google.smartcity.bean.PhotosData;
import com.google.smartcity.bean.PhotosData.PhotoInfo;
import com.google.smartcity.http.Constants;
import com.google.smartcity.http.okhttputils.OkHttpUtils;
import com.google.smartcity.http.okhttputils.callback.StringCallback;
import com.google.smartcity.utils.CacheUtils;
import com.google.smartcity.utils.GsonUtil;
import com.google.smartcity.utils.UIUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * ============================================================
 * 
 * 版 权 ： Google互联网有限公司版权所有 (c) 2016
 * 
 * 作 者 : 陈冠杰
 * 
 * 版 本 ： 1.0
 * 
 * 创建日期 ： 2016-3-5 上午10:27:31
 * 
 * 描 述 ： 菜单详情页-组图
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
public class PhotoMenuDetailPager extends BaseMenuDetailPager {
	@Bind(R.id.recyclerview)
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
