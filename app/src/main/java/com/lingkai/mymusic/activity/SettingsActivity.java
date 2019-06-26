package com.lingkai.mymusic.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lingkai.mymusic.AppContext;
import com.lingkai.mymusic.R;
import com.lingkai.mymusic.domain.event.LogoutSuccessEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

public class SettingsActivity extends BaseTitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    @Override
    protected void initViews() {
        super.initViews();
        enableBackMenu();
    }

    @OnClick(R.id.bt_logout)
    public void bt_logout() {
        sp.logout();
        //即时通讯退出
        //AppContext.logout();

        //Tencent tencent = Tencent.createInstance(Consts.QQ_KEY, this.getApplicationContext());
        //tencent.logout(this);

        //清除第三方登陆信息
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        if (qq.isAuthValid()) {
            qq.removeAccount(true);
        }

        //发布退出登陆的信息，因为首页要根据登陆状态显示
        EventBus.getDefault().post(new LogoutSuccessEvent());

        finish();
    }
}
