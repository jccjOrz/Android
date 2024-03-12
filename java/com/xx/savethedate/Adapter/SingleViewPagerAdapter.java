package com.xx.savethedate.Adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

// 创建一个ViewPagerAdapter
public class SingleViewPagerAdapter extends PagerAdapter {
    private View view;

    public SingleViewPagerAdapter(View view) {
        this.view = view;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(view);
    }

    @Override
    public int getCount() {
        return 1;  // 只有一个视图
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
