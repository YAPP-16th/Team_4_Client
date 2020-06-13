package kr.yapp.teamplay;

import android.app.Application;
import android.content.Context;

import androidx.databinding.DataBindingUtil;

import kr.yapp.teamplay.presentation.matchresultinput.BindingComponent;

public class TeamPlayApplication extends Application {

    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        DataBindingUtil.setDefaultComponent(new BindingComponent());
        appContext = getApplicationContext();
    }
}
