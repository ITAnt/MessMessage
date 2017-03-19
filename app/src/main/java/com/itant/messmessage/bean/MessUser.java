package com.itant.messmessage.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Jason on 2016/8/12.
 */
public class MessUser extends BmobObject {
    private String legal;
    private String vip;
    private String trail;
    private String imei;
    private String phone;

    public String getLegal() {
        return legal;
    }

    public void setLegal(String legal) {
        this.legal = legal;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getTrail() {
        return trail;
    }

    public void setTrail(String trail) {
        this.trail = trail;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
