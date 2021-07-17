package com.lpp.larkRobot.lark.dto;

import lombok.Data;


/**
 * 获取tenant access token 响应体
 *
 * @author lpp
 */
@Data
public class LarkTenantAccessTokenResponseDTO {

    private Integer code;

    private String msg;

    private String tenant_access_token;

    private Integer expire;
}
