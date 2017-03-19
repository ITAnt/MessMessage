package com.itant.messmessage.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.itant.messmessage.MainActivity;
import com.itant.messmessage.R;
import com.itant.messmessage.activity.bomb.FreeActivity;
import com.itant.messmessage.activity.bomb.TrailActivity;
import com.itant.messmessage.activity.bomb.VIPActivity;
import com.itant.messmessage.activity.tips.ContactActivity;
import com.itant.messmessage.activity.tips.ErrorActivity;
import com.itant.messmessage.activity.tips.IllegalActivity;
import com.itant.messmessage.bean.MessUser;
import com.itant.messmessage.bean.OpenDuration;
import com.itant.messmessage.tool.BmobTool;
import com.itant.messmessage.tool.NetTool;
import com.itant.messmessage.tool.SystemTool;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class WelcomeActivity extends Activity {

    private TextView tv_n;
    private TextView tv_m;
    private TextView tv_b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        overridePendingTransition(android.R.anim.fade_in, 0);
        Bmob.initialize(this, "201568cb3d2b13e289f53b6c756003d3");

        tv_n = (TextView) findViewById(R.id.tv_n);
        tv_m = (TextView) findViewById(R.id.tv_m);
        tv_b = (TextView) findViewById(R.id.tv_b);
        showN();
        showM();
        showB();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                WelcomeActivity.this.finish();
            }
        }, 4000);
    }



    private void showN() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_n.setVisibility(View.VISIBLE);

            }
        }, 1000);
    }

    private void showM() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_m.setVisibility(View.VISIBLE);
            }
        }, 2000);
    }

    private void showB() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_b.setVisibility(View.VISIBLE);
            }
        }, 3000);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, android.R.anim.fade_out);
    }
}
