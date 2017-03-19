package com.itant.messmessage.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 詹子聪 on 2016/8/12.
 */
public class MessUrl {

    private MessUrl() {
        getMethod = new HashMap<>();

        postMethod = new HashMap<>();
    }

    private static class MessFactory {
        private static MessUrl messUrl = new MessUrl();
    }

    public static MessUrl getMessUrl() {
        return MessFactory.messUrl;
    }

    private Map<String, Map<String, String>> getMethod;
    private Map<String, Map<String, String>> postMethod;

    public Map<String, Map<String, String>> getGetMethod() {
        return getMethod;
    }

    public void setGetMethod(Map<String, Map<String, String>> getMethod) {
        this.getMethod = getMethod;
    }

    public Map<String, Map<String, String>> getPostMethod() {
        return postMethod;
    }

    public void setPostMethod(Map<String, Map<String, String>> postMethod) {
        this.postMethod = postMethod;
    }
}
