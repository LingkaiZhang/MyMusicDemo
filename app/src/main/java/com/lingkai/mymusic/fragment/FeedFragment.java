package com.lingkai.mymusic.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lingkai.mymusic.R;
import com.lingkai.mymusic.util.Consts;

import org.apache.commons.lang3.StringUtils;

/**
 * description ： TODO:类的作用
 * author : lingkai
 * date : 2019/6/26 10:08
 */
public class FeedFragment extends BaseCommonFragment {

    public static FeedFragment newInstance(String userId) {

        Bundle args = new Bundle();
        if (StringUtils.isNotBlank(userId)) {
            args.putString(Consts.ID,userId);
        }

        FeedFragment fragment = new FeedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static FeedFragment newInstance() {
        return newInstance(null);
    }
    
    @Override
    protected View getLayoutView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me,null);
    }
}
