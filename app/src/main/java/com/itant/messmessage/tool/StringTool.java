package com.itant.messmessage.tool;

import android.content.Context;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jason on 2016/8/14.
 */
public class StringTool {
    public static boolean isChinaPhone(String phone) {
        String pattern = "(13\\d|14[57]|15[^4,\\D]|17[678]|18\\d)\\d{8}|170[059]\\d{7}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(phone);
        return m.matches();
    }

}
