package com.itant.messmessage.activity.tips;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.itant.messmessage.R;
import com.itant.messmessage.activity.BaseActivity;
import com.itant.messmessage.activity.bomb.FreeActivity;
import com.itant.messmessage.activity.bomb.TrailActivity;
import com.itant.messmessage.activity.bomb.VIPActivity;
import com.itant.messmessage.bean.MessUser;
import com.itant.messmessage.bean.OpenDuration;
import com.itant.messmessage.tool.BmobTool;
import com.itant.messmessage.tool.NetTool;
import com.itant.messmessage.tool.SystemTool;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * 出错界面
 */
public class ErrorActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_try_again;
    private ImageView iv_back;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);

        btn_try_again = (Button) findViewById(R.id.btn_try_again);
        btn_try_again.setOnClickListener(this);

        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("出错啦");
    }

    private void initSystem() {
        // 是否有手机号
        final String phoneNumber = SystemTool.getPhoneNumber(this);
        if (phoneNumber == null || TextUtils.isEmpty(phoneNumber)) {
            Toast.makeText(this, "请插入手机卡后重试", Toast.LENGTH_SHORT).show();
        } else {
            phoneIsValid(phoneNumber, this);
        }
    }

    /**
     * 手机号码有效
     *
     * @param phoneNumber
     * @param context
     */
    private void phoneIsValid(final String phoneNumber, Context context) {
        if (NetTool.isNetworkConnected(context)) {
            BmobQuery<MessUser> query = new BmobQuery<MessUser>();
            final String imei = SystemTool.getIMEI(context);
            query.addWhereEqualTo("phone", phoneNumber);
            query.findObjects(new FindListener<MessUser>() {

                @Override
                public void done(List<MessUser> list, BmobException e) {
                    if (list == null || list.size() == 0) {
                        // 用户不存在，注册
                        doRegister(phoneNumber, imei);
                    } else {
                        // 已注册
                        MessUser messUser = list.get(0);
                        // 设置全局用户
                        BmobTool.getInstance().setMessUser(messUser);
                        // 查看是否非法
                        if (messUser.getLegal() != null && TextUtils.equals("true", messUser.getLegal())) {
                            // 合法
                            //查看是否VIP会员
                            if (messUser.getVip() != null && TextUtils.equals("true", messUser.getVip())) {
                                // 跳转到VIP界面
                                startVip();
                            } else {
                                dealFree(messUser.getTrail());
                            }
                        } else {
                            // 跳到非法用户界面
                            startIllegal();
                        }
                    }
                }
            });
        } else {
            // 没有网络
            Toast.makeText(this, "请检查网络后重试", Toast.LENGTH_SHORT).show();
        }
    }

    private void doRegister(String phoneNumber, String imei) {
        //未注册
        MessUser messUser = new MessUser();
        messUser.setImei(imei);
        messUser.setLegal("true");
        messUser.setPhone(phoneNumber);
        messUser.setTrail("false");
        messUser.setVip("false");
        BmobTool.getInstance().setMessUser(messUser);
        messUser.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    // 注册成功，查看是否处于免费开放期间
                    dealFree("false");
                } else {
                    // 注册失败跳转到ErrorActivity
                    Toast.makeText(ErrorActivity.this, "系统登记失败，请重试", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     *
     * @param trail 1已使用，0未使用
     */
    private void dealFree(final String trail) {
        BmobQuery<OpenDuration> query = new BmobQuery<OpenDuration>();
        query.findObjects(new FindListener<OpenDuration>() {
            @Override
            public void done(List<OpenDuration> list, BmobException e) {
                if (e == null && list != null && list.size() == 1) {
                    OpenDuration openDuration = list.get(0);
                    BmobTool.getInstance().setOpenDuration(openDuration);
                    if (openDuration.getOpen()) {
                        // 全民免费期间
                        startFree();
                    } else {
                        // 不开放免费，查看是否已经试用过了
                        if (trail != null && TextUtils.equals("true", trail)) {
                            // 已经试用过了，联系QQ
                            startContact();
                        } else {
                            startTrail();
                        }
                    }
                }
            }
        });
    }

    /**
     * 进入VIP界面
     *
     * @param
     */
    private void startVip() {
        startActivity(new Intent(ErrorActivity.this, VIPActivity.class));

    }

    /**
     * 进入Trail界面
     *
     * @param
     */
    private void startTrail() {
        startActivity(new Intent(ErrorActivity.this, TrailActivity.class));
    }

    /**
     * 进入联系购买界面
     *
     * @param
     */
    private void startContact() {
        startActivity(new Intent(ErrorActivity.this, ContactActivity.class));

    }

    /**
     * 进入非法用户界面
     *
     * @param
     */
    private void startIllegal() {
        startActivity(new Intent(ErrorActivity.this, IllegalActivity.class));
    }

    /**
     * 进入全民免费界面
     *
     * @param
     */
    private void startFree() {
        startActivity(new Intent(ErrorActivity.this, FreeActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_try_again:
                initSystem();
                break;
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }
}
