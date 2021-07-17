package com.lpp.larkRobot.lark.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 获取tenant access token 请求体
 *
 * @author lpp
 */
@Data
@Builder
public class LarkTenantAccessTokenRequestBodyDTO {
    /**
     * 应用唯一标识，创建应用后获得
     */
    private String app_id;

    /**
     * 应用秘钥，创建应用后获得
     */
    private String app_secret;
}
