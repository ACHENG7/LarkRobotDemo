package com.lpp.larkRobot.lark.service;


import com.lpp.larkRobot.lark.dto.LarkGetRobotChatListResponseDTO;
import com.lpp.larkRobot.lark.dto.LarkSendTextMsgResponseDTO;

import java.util.List;

/**
 * @author lpp
 */
public interface LarkAppUtilService {

    String getTenantLarkAccessTokenForXXApp();


    /**
     * 获取 tenant_access_token（企业自建应用）
     *
     * @param appId     应用唯一标识，创建应用后获得
     * @param appSecret 应用秘钥，创建应用后获得
     * @return
     * @Link {https://open.larksuite.com/document/ukTMukTMukTM/uIjNz4iM2MjLyYzM}
     */
    String getTenantLarkAccessToken(String appId, String appSecret);

    /**
     * 发送文本消息
     * 服务端依次读取字段的顺序为 chat_id > open_id > user_id > email
     *
     * @param openId     必填,给用户发私聊消息，只需要填 open_id、email、user_id 中的一个即可
     * @param userId
     * @param email
     * @param chatId     向群里发消息使用群的 chat_id
     * @param rootId     选填,如果需要回复某条消息，填对应消息的消息 ID
     * @param text
     * @param atUserList 将用户的数据封装到<at></at>标签中
     * @return
     * @Link {https://open.larksuite.com/document/ukTMukTMukTM/uUjNz4SN2MjL1YzM}
     */
    LarkSendTextMsgResponseDTO sendTextMessage(String openId, String userId, String email, String chatId, String rootId,
                                               String text, List<String> atUserList);


    /**
     * 获取机器人的所有群列表
     *
     * @param pageSize 分页大小，最大支持 200；默认为 100
     * @param pageToken 分页标记，第一次请求不填，表示从头开始遍历；分页查询还有更多群时会同时返回新的 page_token, 下次遍历可采用该 page_token 获取更多群
     * @return
     * @Link {https://open.larksuite.com/document/ukTMukTMukTM/uITO5QjLykTO04iM5kDN}
     */
    LarkGetRobotChatListResponseDTO getLarkGetRobotChatListByPage(int pageSize, String pageToken);





}
