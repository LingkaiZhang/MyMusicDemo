package com.lingkai.mymusic.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lingkai.mymusic.R;

/**
 * description ： TODO:类的作用
 * author : lingkai
 * date : 2019/6/26 10:08
 */
public class MusicFragment extends BaseCommonFragment {

    public static MusicFragment newInstance() {
        
        Bundle args = new Bundle();
        
        MusicFragment fragment = new MusicFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    protected View getLayoutView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_music,null);
    }
}
