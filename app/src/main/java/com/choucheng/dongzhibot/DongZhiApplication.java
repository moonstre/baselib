package com.choucheng.dongzhibot;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.bjkj.library.utils.SPUtils;
import com.vondear.rxtool.RxTool;

/**
 * Created by liyou on 2018/6/4.
 */

public class DongZhiApplication extends MultiDexApplication{
  @Override public void onCreate() {
    super.onCreate();
    SPUtils.init(this);
    RxTool.init(this);
  }

  @Override
  protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
    MultiDex.install(this);
  }
}
