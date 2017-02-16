 package com.google.smartcity.ui.fragment;

 import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.google.smartcity.R;
import com.google.smartcity.base.impl.NewsCenterPager;
import com.google.smartcity.ui.activity.MainActivity;
import com.google.smartcity.ui.adapter.abslistview.CommonAdapter;
import com.google.smartcity.ui.adapter.abslistview.ViewHolder;
import com.google.smartcity.ui.widget.slidingmenu.SlidingMenu;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
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
  * Package_Name：com.google.smartcity
  * Version：1.0
  * time：2016/2/16 10:06
  * des ：${TODO}
  * gitVersion：$Rev$
  * updateAuthor：$Author$
  * updateDate：$Date$
  * updateDes：${TODO}
  * ============================================================
  **/
public class LeftMenuFragment extends BaseFragment {
	@Bind(R.id.list_view)
	ListView mListView;

	private MenuAdapter mAdapter;

	private List<String> mDatas;

	private int mCurrentPos;// 当前被点击的菜单项

	@Override
	public View initView(LayoutInflater inflater, ViewGroup container) {
		View view = inflater.inflate(R.layout.fragment_left_menu, container,false);
		ButterKnife.bind(this, view);
		return view;
	}

	@Override
	public void initData() {
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mCurrentPos = position;
				mAdapter.notifyDataSetChanged();
				setCurrentMenuDetailPager(position);
				toggleSlidingMenu();
			}
		});
	}

	/**
	 * 切换slidingmenu的状态
	 */
	protected void toggleSlidingMenu() {
		MainActivity mainUi = (MainActivity) getActivity();
		SlidingMenu slidingMenu = mainUi.getSlidingMenu();
		slidingMenu.toggle();// 切换状态, 显示时隐藏, 隐藏时显示
	}

	/**
	 * 设置网络数据
	 * 
	 * @param data
	 */
	public void setMenuData(List<String> data) {
		mDatas = data;
		mAdapter = new MenuAdapter(getActivity(), R.layout.list_menu_item, mDatas);
		mListView.setAdapter(mAdapter);
	}

	/**
	 * 设置当前菜单详情页
	 */
	protected void setCurrentMenuDetailPager(int position) {
		MainActivity mainUi = (MainActivity) getActivity();
		ContentFragment fragment = mainUi.getContentFragment();// 获取主页面fragment
		NewsCenterPager newsCenterPager = fragment.getNewsCenterPager();// 获取新闻中心页面
		newsCenterPager.setCurrentMenuDetailPager(position);// 设置当前菜单详情页
	}

	/**
	 * 侧边栏数据适配器
	 * 
	 * @author AllenIverson
	 * 
	 */
	class MenuAdapter extends CommonAdapter<String> {

		public MenuAdapter(Context context, int layoutId, List<String> datas) {
			super(context, layoutId, datas);
		}

		@Override
		protected void convert(ViewHolder holder, String title, int position) {
			holder.setText(R.id.tv_title, title);
			if (mCurrentPos == position) {// 判断当前绘制的view是否被选中
				// 显示红色
				holder.getView(R.id.tv_title).setEnabled(true);

			} else {
				// 显示白色
				holder.getView(R.id.tv_title).setEnabled(false);
			}
		}
	}

}
