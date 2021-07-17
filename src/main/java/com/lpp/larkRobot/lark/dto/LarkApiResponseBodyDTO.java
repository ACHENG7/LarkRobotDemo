package com.lpp.larkRobot.lark.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * LARK  api  响应体
 *
 * @author lpp
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LarkApiResponseBodyDTO<T> {
    /**
     * 返回码，0表示请求成功，其他表示请求失败
     */
    private int code;
    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回业务数据
     */
    private T data;

}
