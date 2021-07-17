package com.lpp.larkRobot.lark.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lpp.larkRobot.lark.constant.LarkApiConstant;
import com.lpp.larkRobot.lark.dto.*;
import com.lpp.larkRobot.lark.service.LarkAppUtilService;
import com.lpp.larkRobot.okhttp.OkHttpRequestUtils;
import com.lpp.larkRobot.okhttp.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.apache.commons.collections4.CollectionUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LarkAppUtilServiceImpl implements LarkAppUtilService {


    @Override
    public String getTenantLarkAccessTokenForXXApp() {
        //  填充自己应用的的appId、appSecret、数据入口在lark-开发者后台
        String appId = "XX";
        String appSecret = "XX";
        return this.getTenantLarkAccessToken(appId, appSecret);
    }

    @Override
    public String getTenantLarkAccessToken(String appId, String appSecret) {

        // 获取 tenant_access_token url
        String url = LarkApiConstant.GET_TENANT_ACCESS_TOKEN;

        // 组装header头
        Map<String, String> headers = new HashMap<>(1);
        headers.put(LarkApiConstant.CONTENT_TYPE, LarkApiConstant.APPLICATION_JSON_UTF_8);


        // 组装请求体
        LarkTenantAccessTokenRequestBodyDTO requestBody = LarkTenantAccessTokenRequestBodyDTO
                .builder()
                .app_id(appId)
                .app_secret(appSecret)
                .build();


        // 发送post请求，解析响应体
        ResponseResult responseResult = OkHttpRequestUtils.postWithHeaders(url, headers, requestBody);
        if (Objects.nonNull(responseResult)) {
            if (StringUtils.equalsIgnoreCase(responseResult.getBodyMediaType().toString(), LarkApiConstant.APPLICATION_JSON_UTF_8)) {
                LarkTenantAccessTokenResponseDTO responseDTO = JSON.parseObject(responseResult.getResponseBodyString(), LarkTenantAccessTokenResponseDTO.class);
                if (Objects.nonNull(responseDTO) && Objects.nonNull(responseDTO.getCode()) && responseDTO.getCode() == LarkApiConstant.ResponseCode.SUCCESS_CODE.getCode()) {
                    return responseDTO.getTenant_access_token();
                }
            }
        }
        log.error("LarkAppUtilServiceImpl getTenantLarkAccessToken fail, responseResult {}", JSON.toJSONString(responseResult));
        return null;
    }

    @Override
    public LarkSendTextMsgResponseDTO sendTextMessage(String openId, String userId, String email, String chatId, String rootId, String text, List<String> atUserList) {

        //获取tenant_access_token
        String accessToken = this.getTenantLarkAccessTokenForXXApp();
        if (StringUtils.isBlank(accessToken)) {
            log.error("LarkAppUtilServiceImpl sendTextMessage  getTenantLarkAccessTokenForXXApp is empty!");
            return null;
        }


        // 获取 发送文本消息 url
        String url = LarkApiConstant.SEND_TEXT_MESSAGE;

        // 组装header头
        Map<String, String> headers = new HashMap<>(2);
        headers.put(LarkApiConstant.AUTHORIZATION, LarkApiConstant.AUTHORIZATION_PREFIX + accessToken);
        headers.put(LarkApiConstant.CONTENT_TYPE, LarkApiConstant.APPLICATION_JSON_UTF_8);


        // 组装text
        if (CollectionUtils.isNotEmpty(atUserList)) {
            String atTag = atUserList.stream()
                    .filter(id -> StringUtils.isNotBlank(id))
                    .map(id -> LarkApiConstant.getAtTag(id))
                    .collect(Collectors.joining());
            text = text + atTag;
        }

        // 组装请求体
        LarkSendTextMsgRequestBodyDTO.Content content = LarkSendTextMsgRequestBodyDTO.Content.builder()
                .text(text)
                .build();

        LarkSendTextMsgRequestBodyDTO requestBody = LarkSendTextMsgRequestBodyDTO
                .builder()
                .msg_type("text")
                .content(content)
                .build();

        if (StringUtils.isNotBlank(openId)) {
            requestBody.setOpen_id(openId);
        }
        if (StringUtils.isNotBlank(userId)) {
            requestBody.setUser_id(userId);
        }
        if (StringUtils.isNotBlank(email)) {
            requestBody.setEmail(email);
        }
        if (StringUtils.isNotBlank(chatId)) {
            requestBody.setChat_id(chatId);
        }
        if (StringUtils.isNotBlank(rootId)) {
            requestBody.setRoot_id(rootId);
        }

        // 发送post请求，解析响应体
        ResponseResult responseResult = OkHttpRequestUtils.postWithHeaders(url, headers, requestBody);
        if (Objects.nonNull(responseResult)) {
            if (StringUtils.equalsIgnoreCase(responseResult.getBodyMediaType().toString(), LarkApiConstant.APPLICATION_JSON_UTF_8)) {
                LarkApiResponseBodyDTO<LarkSendTextMsgResponseDTO> responseDTO = JSON.parseObject(responseResult.getResponseBodyString(), LarkApiResponseBodyDTO.class);
                if (Objects.nonNull(responseDTO) && Objects.nonNull(responseDTO.getCode()) && responseDTO.getCode() == LarkApiConstant.ResponseCode.SUCCESS_CODE.getCode()) {
                    return JSONObject.parseObject(JSONObject.toJSONString(responseDTO.getData()), LarkSendTextMsgResponseDTO.class);
                }
            }
        }
        log.error("LarkAppUtilServiceImpl sendTextMessage fail, responseResult {}", JSON.toJSONString(responseResult));
        return null;
    }

    @Override
    public LarkGetRobotChatListResponseDTO getLarkGetRobotChatListByPage(int pageSize, String pageToken) {
        //获取tenant_access_token
        String accessToken = this.getTenantLarkAccessTokenForXXApp();
        if (StringUtils.isBlank(accessToken)) {
            log.error("LarkAppUtilServiceImpl sendTextMessage  getTenantLarkAccessTokenForXXApp is empty!");
            return null;
        }

        // 获取 获取机器人所在的群列表 url
        String url = LarkApiConstant.GET_ROBOT_CHAT_LIST;

        // 组装header头
        Map<String, String> headers = new HashMap<>(2);
        headers.put(LarkApiConstant.AUTHORIZATION, LarkApiConstant.AUTHORIZATION_PREFIX + accessToken);
        headers.put(LarkApiConstant.CONTENT_TYPE, LarkApiConstant.APPLICATION_JSON_UTF_8);

        // 组装param
        Map<String, String> params = new HashMap<>();
        if (pageSize > 0) {
            headers.put(LarkApiConstant.PAGE_SIZE, String.valueOf(pageSize));
        }
        if (StringUtils.isNotBlank(pageToken)) {
            headers.put(LarkApiConstant.PAGE_TOKEN, pageToken);
        }

        // 发送post请求，解析响应体
        ResponseResult responseResult = OkHttpRequestUtils.get(url, params, headers);
        if (Objects.nonNull(responseResult)) {
            if (StringUtils.equalsIgnoreCase(responseResult.getBodyMediaType().toString(), LarkApiConstant.APPLICATION_JSON_UTF_8)) {
                LarkApiResponseBodyDTO<LarkGetRobotChatListResponseDTO> responseDTO = JSON.parseObject(responseResult.getResponseBodyString(), LarkApiResponseBodyDTO.class);
                if (Objects.nonNull(responseDTO) && Objects.nonNull(responseDTO.getCode()) && responseDTO.getCode() == LarkApiConstant.ResponseCode.SUCCESS_CODE.getCode()) {
                    return JSONObject.parseObject(String.valueOf(responseDTO.getData()), LarkGetRobotChatListResponseDTO.class);
                }
            }
        }
        log.error("LarkAppUtilServiceImpl getLarkGetRobotChatList fail, responseResult {}", JSON.toJSONString(responseResult));
        return null;
    }


}


