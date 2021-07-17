package com.lpp.larkRobot.lark.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 　LARK 发送文本消息请求体
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LarkSendTextMsgRequestBodyDTO {


    /**
     * 给用户发私聊消息，只需要填 open_id、email、user_id 中的一个即可，
     * 向群里发消息使用群的 chat_id（可通过获取群列表接口获取）。
     * 服务端依次读取字段的顺序为 chat_id > open_id > user_id > email
     * ( user_id 对应V3接口的 employee_id , chat_id 对应V3的 open_chat_id )
     */
    private String open_id;
    private String user_id;
    private String email;
    private String chat_id;


    /**
     * 如果需要回复某条消息，填对应消息的消息 ID
     */
    private String root_id;
    /**
     * 消息类型，此处固定填 "text"
     */
    private String msg_type;
    /**
     * 消息内容
     */
    private Content content;


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Content {

        /**
         * 文本消息内容，文本消息中可以 at 个人或全体成员
         * at 全体成员：<at user_id="all"> </at>
         * at 个人：<at user_id="ou_xxxxxxx"></at>，user_id 为用户 user_id或者open_id
         */
        private String text;
    }
}
