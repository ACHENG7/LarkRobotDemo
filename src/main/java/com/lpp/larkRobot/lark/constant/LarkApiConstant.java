package com.lpp.larkRobot.lark.constant;

import lombok.Data;
import lombok.Getter;

/**
 * @author lpp
 * LARK API 常量
 */
@Data
public class LarkApiConstant {

    public static String CONTENT_TYPE = "Content-Type";

    public static String APPLICATION_JSON_UTF_8 = "application/json; charset=utf-8";

    /***调用服务需要使用的 AUTHORIZATION ***/
    public static String AUTHORIZATION = "Authorization";

    /**
     * 调用服务
     * Authorization 的必须带有的前缀
     */
    public static String AUTHORIZATION_PREFIX = "Bearer ";

    public static String PAGE_SIZE = "page_size";

    public static String PAGE_TOKEN = "page_token";

    /***获取 tenant_access_token（企业自建应用）***/
    public static String GET_TENANT_ACCESS_TOKEN = "https://open.larksuite.com/open-apis/auth/v3/tenant_access_token/internal/";


    /***发送文本消息***/
    public static String SEND_TEXT_MESSAGE = "https://open.larksuite.com/open-apis/message/v4/send/";


    /**获取机器人所在的群列表***/
    public static String GET_ROBOT_CHAT_LIST= "https://open.larksuite.com/open-apis/chat/v4/list";


    /**
     *
     * @param string
     * @return
     */
    public static String getAtTag(String string) {
        return "<at user_id=\"" + string + "\"></at>";

    }

    @Getter
    public enum ResponseCode {

        /**
         * 错误码，非 0 表示失败
         */
        SUCCESS_CODE(0, "0表示成功");


        private int code;

        private String msg;

        ResponseCode(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }
}
