package com.itant.messmessage.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by 詹子聪 on 2016/8/12.
 */
public class OpenDuration extends BmobObject {
    private Boolean isOpen;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }
}
