package com.lingkai.mymusic.fragment;


import com.lingkai.mymusic.util.OrmUtil;
import com.lingkai.mymusic.util.SharedPreferencesUtil;

/**
 * Created by smile on 02/03/2018.
 */

public abstract class BaseCommonFragment extends BaseFragment {
    protected SharedPreferencesUtil sp;
    protected OrmUtil orm;

    @Override
    protected void initViews() {
        super.initViews();

        sp = SharedPreferencesUtil.getInstance(getActivity().getApplicationContext());
        orm = OrmUtil.getInstance(getActivity().getApplicationContext());
    }


}
