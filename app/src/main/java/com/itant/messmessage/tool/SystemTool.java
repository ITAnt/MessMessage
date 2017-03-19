package com.itant.messmessage.tool;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * Created by Jason on 2016/8/12.
 */
public class SystemTool {

    /**
     * 获取手机的IMEI
     *
     * @param context 上下文
     * @return 手机的IMEI
     */
    public static String getIMEI(Context context) {
        return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
    }

    public static String getPhoneNumber(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return manager.getLine1Number();
    }
}
