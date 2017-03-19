package com.itant.messmessage.activity.bomb;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.itant.messmessage.R;
import com.itant.messmessage.activity.BaseActivity;
import com.itant.messmessage.bean.MessUser;
import com.itant.messmessage.tool.BmobTool;
import com.itant.messmessage.tool.NetTool;
import com.itant.messmessage.tool.StringTool;

import java.util.Random;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 试用界面
 */
public class TrailActivity extends BaseActivity implements View.OnClickListener {
    private Random mRandom;
    private ImageView iv_back;
    private TextView tv_title;

    private Button btn_start;
    private String phone;
    private EditText et_phone;

    private volatile boolean isBombing;
    //private ExecutorService mExecutorService;
    private MessUser mMessUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trail);
        //mExecutorService = Executors.newFixedThreadPool(5);

        mMessUser = BmobTool.getInstance().getMessUser();

        btn_start = (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);
        et_phone = (EditText) findViewById(R.id.et_phone);

        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("试用期间");
        mRandom = new Random();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                if (isBombing) {
                    // 停止
                    isBombing = false;
                    btn_start.setText("开始轰炸");
                } else {
                    if (TextUtils.equals(mMessUser.getTrail(), "false")) {
                        mMessUser.setTrail("true");
                        mMessUser.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if (e == null) {
                                    phone = et_phone.getText().toString();
                                    // 开始轰炸
                                    if (NetTool.isNetworkConnected(TrailActivity.this)) {
                                        if (StringTool.isChinaPhone(phone)) {
                                            isBombing = true;
                                            btn_start.setText("停止轰炸");
                                            new Thread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    while (isBombing) {
                                                        NetTool.startBomb(phone);
                                                        try {
                                                            Thread.sleep(mRandom.nextInt(120000) + 120000);
                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                }
                                            }).start();
                                        } else {
                                            Toast.makeText(TrailActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } else {
                                    Toast.makeText(TrailActivity.this, "指令发送失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(TrailActivity.this, "您已使用过啦，请联系客服购买会员(*^__^*)", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case R.id.iv_back:
                isBombing = false;
                finish();
                break;
            default:
                break;
        }
    }
}
