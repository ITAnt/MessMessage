package com.itant.messmessage.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import com.itant.messmessage.R;

/**
 * Created by Jason on 2016/8/12.
 */
public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        //setContentView(R.layout.activity_base);
        overridePendingTransition(R.anim.anim_left_in, 0);
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.anim_right_out);
    }
}
