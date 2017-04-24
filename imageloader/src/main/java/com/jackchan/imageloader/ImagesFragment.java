
package com.jackchan.imageloader;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.jackchan.imageloader.core.ImageLoader;

/**
 * ============================================================
 * Copyright：JackChan和他的朋友们有限公司版权所有 (c) 2017
 * Author：   JackChan
 * Email：    815712739@qq.com
 * GitHub：   https://github.com/JackChan1999
 * GitBook：  https://www.gitbook.com/@alleniverson
 * 博客：     http://blog.csdn.net/axi295309066
 * 微博：     AndroidDeveloper
 * <p>
 * Project_Name：ImageLoader
 * Package_Name：com.jackchan.imageloader
 * Version：1.0
 * time：2016/4/24 10:50
 * des ：${TODO}
 * gitVersion：2.12.0.windows.1
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 */

public class ImagesFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gridview_fragment, container, false);
        GridView gridView = (GridView) view.findViewById(R.id.id_gridview);
        gridView.setAdapter(new ImageItemAdaper(getActivity(), 0, MainActivity.imageThumbUrls));
        return view;
    }

    private class ImageItemAdaper extends ArrayAdapter<String> {

        public ImageItemAdaper(Context context, int resource, String[] datas) {
            super(getActivity(), 0, datas);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(
                        R.layout.image_item_layout, parent, false);
            }
            ImageView imageview = (ImageView) convertView
                    .findViewById(R.id.id_img);
            // 加载图片
            ImageLoader.getInstance().displayImage(imageview,
                    getItem(position));
            return convertView;
        }

    } // end ImageItemAdaper

}
