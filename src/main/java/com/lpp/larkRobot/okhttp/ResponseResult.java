package com.lpp.larkRobot.okhttp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult {

    /**
     * 此次请求的Request
     */
    private Request request;

    /**
     * 使用的协议
     */
    private Protocol protocol;

    /**
     * http响应状态码
     */
    private int code;

    /**
     * message（这个没啥用）
     */
    private String message;

    /**
     * TLS握手的记录。对于HTTPS客户机，客户机是<i>local</i>和远程服务器
     * *是它的<i>对等点</i>。
     * *
     * 这个值对象描述了一个完成的握手。使用{@link okhttp3.ConnectionSpec}设置策略
     * *用于新的握手。
     */
    private Handshake handshake;


    /**
     * 单个HTTP消息的头字段。值是未解释的字符串;使用{@code请求}
     * *和{@code Response}用于解释的标题。该类维护头字段的顺序
     * *在HTTP消息中。
     * *
     * 这个类逐行跟踪报头值。具有多个逗号分隔值的字段
     * *相同的行将被该类视为具有单个值的字段。它是来电者的
     * *负责检测和分割逗号，如果他们的字段允许多个值。这
     * *简化了单值字段的使用，这些字段的值通常包含逗号，比如cookie或
     * *日期。
     * *
     * * <p>这个类从值中删除空格。它从不返回带前导或尾随的值
     * *空白。
     * *
     * * /
     */
    private Headers headers;


    /**
     * 响应体的媒体类型
     */
    private MediaType bodyMediaType;

    /**
     * 响应体字符串
     */
    private String responseBodyString;


    /**
     *
     */
    private long sentRequestAtMillis;

    /**
     *
     */
    private long receivedResponseAtMillis;


}
