package com.lpp.larkRobot.lark.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * LARK 发送文本消息的响应体
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LarkSendTextMsgResponseDTO {

    /**
     * 消息 ID
     */
    private String message_id;
}
