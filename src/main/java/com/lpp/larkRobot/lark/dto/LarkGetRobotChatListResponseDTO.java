package com.lpp.larkRobot.lark.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 获取机器人所在的群列表响应体
 *
 * @author lpp
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LarkGetRobotChatListResponseDTO {

    /**
     * 还有群未读取完
     */
    private boolean has_more;
    private String page_token;
    private List<Groups> groups;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Groups {
        /**
         * 群头像
         */
        private String avatar;
        /**
         * 群描述
         */
        private String description;
        /**
         * 群 ID
         */
        private String chat_id;

        /**
         * 群名称
         */
        private String name;

        /**
         * 群主的 open_id
         */
        private String owner_open_id;

        /**
         * 群主的 user_id（机器人是群主的时候没有这个字段）
         */
        private String owner_user_id;
    }
}
