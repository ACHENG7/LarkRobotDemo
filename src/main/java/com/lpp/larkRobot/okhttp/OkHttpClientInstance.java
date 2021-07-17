package com.lpp.larkRobot.okhttp;

import okhttp3.OkHttpClient;


/**
 * 枚举实现的单例的OkHttpClient
 **/
public class OkHttpClientInstance {

    private OkHttpClientInstance() {
    }

    public static OkHttpClient getOkHttpClient() {
        return Singleton.INSTANCE.getOkHttpClient();
    }


    private enum Singleton {
        /**
         *
         */
        INSTANCE;

        private OkHttpClient okHttpClient;

        Singleton() {
            okHttpClient = new OkHttpClient();
        }

        public OkHttpClient getOkHttpClient() {
            return okHttpClient;
        }

    }


}
