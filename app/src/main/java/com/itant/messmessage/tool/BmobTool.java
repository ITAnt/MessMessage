package com.itant.messmessage.tool;

import com.itant.messmessage.bean.MessUser;
import com.itant.messmessage.bean.OpenDuration;

/**
 * Created by 詹子聪 on 2016/8/12.
 */
public class BmobTool {

    private MessUser messUser;
    private OpenDuration openDuration;
    private BmobTool(){}
    private static class BmobFactory {
        private static BmobTool bmobTool = new BmobTool();
    }
    public static BmobTool getInstance() {
        return BmobFactory.bmobTool;
    }

    public MessUser getMessUser() {
        return messUser;
    }

    public void setMessUser(MessUser messUser) {
        this.messUser = messUser;
    }

    public OpenDuration getOpenDuration() {
        return openDuration;
    }

    public void setOpenDuration(OpenDuration openDuration) {
        this.openDuration = openDuration;
    }
}
