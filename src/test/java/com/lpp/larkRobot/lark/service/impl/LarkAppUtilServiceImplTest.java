package com.lpp.larkRobot.lark.service.impl;

import com.lpp.larkRobot.LarkRobotDemoApplication;
import com.lpp.larkRobot.lark.dto.LarkGetRobotChatListResponseDTO;
import com.lpp.larkRobot.lark.service.LarkAppUtilService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LarkRobotDemoApplication.class})
class LarkAppUtilServiceImplTest {

    @Autowired
    private LarkAppUtilService larkAppUtilService;


    /**
     * 给指定的人发消息
     */
    @Test
    void sendTextMessageToUser() {
        String text = "塞纳河畔 左岸的咖啡\n" +
                "我手一杯 品尝你的美 留下唇印的嘴\n" +
                "花店玫瑰 名字写错谁\n" +
                "告白气球 风吹到对街 微笑在天上飞\n" +
                "你说你有点难追 想让我知难而退\n" +
                "礼物不需挑最贵 只要香榭的落叶\n" +
                "营造浪漫的约会 不害怕搞砸一切\n" +
                "拥有你就拥有 全世界";

        // 可填充消息接收人的email信息
        larkAppUtilService.sendTextMessage(null, null, "xx@qq.com", null, "", text, Lists.newArrayList("all"));
    }

    /**
     * 给机器人所在的群发消息
     */
    @Test
     void sendTextMessageToChat() {
        String text = "塞纳河畔 左岸的咖啡\n" +
                "我手一杯 品尝你的美 留下唇印的嘴\n" +
                "花店玫瑰 名字写错谁\n" +
                "告白气球 风吹到对街 微笑在天上飞\n" +
                "你说你有点难追 想让我知难而退\n" +
                "礼物不需挑最贵 只要香榭的落叶\n" +
                "营造浪漫的约会 不害怕搞砸一切\n" +
                "拥有你就拥有 全世界";

        // 获取机器人所在的群
        List<LarkGetRobotChatListResponseDTO.Groups> groupsList = new ArrayList<>();

        // 获取第一页数据
        LarkGetRobotChatListResponseDTO responseDTO = larkAppUtilService.getLarkGetRobotChatListByPage(200, "");
        if (Objects.isNull(responseDTO)) {
            log.error("LarkAppUtilServiceImplTest sendTextMessageToChat responseDTO is empty");
            return;
        }
        groupsList.addAll(responseDTO.getGroups());

        boolean hasMore = responseDTO.isHas_more();
        String pageToke = responseDTO.getPage_token();

        // 遍历获取所有页数据
        while (hasMore) {
            LarkGetRobotChatListResponseDTO subDTO = larkAppUtilService.getLarkGetRobotChatListByPage(200, pageToke);
            if (Objects.isNull(subDTO)) {
                log.error("LarkAppUtilServiceImplTest sendTextMessageToChat subDTO is empty");
                return;
            }
            groupsList.addAll(subDTO.getGroups());

            hasMore = subDTO.isHas_more();
            pageToke = subDTO.getPage_token();
        }


        //发消息至群
        if (CollectionUtils.isNotEmpty(groupsList)) {
            groupsList.stream().forEach(groups -> {
                try {
                    String chatId = groups.getChat_id();
                    larkAppUtilService.sendTextMessage(null, null, "xx@qq.com", chatId, "", text, Lists.newArrayList("all"));
                } catch (Exception e) {
                    log.error("LarkAppUtilServiceImplTest sendTextMessageToChat sendTextMessage error:{}", e);
                }
            });
        }
    }

}
