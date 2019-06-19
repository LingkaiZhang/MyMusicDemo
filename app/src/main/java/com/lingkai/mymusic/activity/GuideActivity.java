package com.lingkai.mymusic.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.lingkai.mymusic.MainActivity;
import com.lingkai.mymusic.R;
import com.lingkai.mymusic.adapter.GuideAdapter;
import com.lingkai.mymusic.util.PackageUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

public class GuideActivity extends BaseCommonActivity {

    @BindView(R.id.vp)
    ViewPager vp;

    @BindView(R.id.indicator)
    CircleIndicator indicator;

    private GuideAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        //去除状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void initDatas() {
        super.initDatas();
        adapter = new GuideAdapter(getActivity(), getSupportFragmentManager());
        vp.setAdapter(adapter);
        indicator.setViewPager(vp);
        adapter.registerDataSetObserver(indicator.getDataSetObserver());

        ArrayList<Integer> datas = new ArrayList<>();
        datas.add(R.drawable.guide1);
        datas.add(R.drawable.guide2);
        datas.add(R.drawable.guide3);
        adapter.setDatas(datas);
    }

    @OnClick(R.id.bt_login_or_register)
    public void bt_login_or_register() {
        setFirst();
        startActivityAfterFinishThis(LoginActivity.class);
    }

    @OnClick(R.id.bt_enter)
    public void bt_enter() {
        setFirst();
        startActivityAfterFinishThis(MainActivity.class);
    }

    private void setFirst() {
        sp.putBoolean(String.valueOf(PackageUtil.getVersionCode(getApplicationContext())),false);
    }

    /**
     * 不调用父类方法，用户按返回键就不能关闭当前页面了
     */
    @Override
    public void onBackPressed() {

    }
}
