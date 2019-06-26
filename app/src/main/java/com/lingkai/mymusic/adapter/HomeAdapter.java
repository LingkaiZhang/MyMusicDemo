package com.lingkai.mymusic.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.lingkai.mymusic.fragment.MeFragment;
import com.lingkai.mymusic.fragment.MusicFragment;
import com.lingkai.mymusic.fragment.VideoFragment;


/**
 * Created by smile on 2018/5/26.
 */

public class HomeAdapter extends BaseFragmentPagerAdapter<Integer> {
    public HomeAdapter(Context context, FragmentManager fm) {
        super(context, fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return MeFragment.newInstance();
        } else if (position == 1) {
            return MusicFragment.newInstance();
        } else {
            return VideoFragment.newInstance();
        }
    }
}