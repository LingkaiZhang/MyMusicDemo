package com.lingkai.mymusic.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.lingkai.mymusic.fragment.FMFragment;
import com.lingkai.mymusic.fragment.FeedFragment;
import com.lingkai.mymusic.fragment.RecommendFragment;

/**
 * description ： 首页二级ViewPage切换
 * author : lingkai
 * date : 2019/6/27 14:24
 */
public class MusicUIAdapter extends BaseFragmentPagerAdapter<Integer> {

    private static String[] titleNames = {"推荐", "朋友", "电台"};

    public MusicUIAdapter(Context context, FragmentManager fm) {
        super(context, fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return RecommendFragment.newInstance();
        } else if (position == 1) {
            return FeedFragment.newInstance();
        } else {
            return FMFragment.newInstance();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleNames[position];
    }
}
