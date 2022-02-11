package com.jc.community.controller;

import com.jc.community.entity.User;
import com.jc.community.event.EventProducer;
import com.jc.community.service.LikeService;
import com.jc.community.util.CommunityConstant;
import com.jc.community.util.CommunityUtil;
import com.jc.community.util.Event;
import com.jc.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LikeController implements CommunityConstant {

    @Autowired
    private LikeService likeService;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private EventProducer eventProducer;

    @RequestMapping(path = "/like", method = RequestMethod.POST)
    @ResponseBody
    public String like(int entityType, int entityId, int entityUserId, int postId) {
        User user = hostHolder.getUser();

        // 点赞
        likeService.like(user.getId(), entityType, entityId, entityUserId);

        // 数量
        long likeCount = likeService.findEntityLikeCount(entityType, entityId);
        // 状态
        int likeStatus = likeService.findEntityLikeStatus(user.getId(), entityType, entityId);
        // 返回结果
        Map<String, Object> map = new HashMap<>();
        map.put("likeCount", likeCount);
        map.put("likeStatus", likeStatus);

        // 触发点赞事件
        if (likeStatus == 1) {
            Event event = new Event()
                    .setUserId(hostHolder.getUser().getId())
                    .setTopic(TOPIC_LIKE)
                    .setEntityId(entityId)
                    .setEntityType(entityType)
                    .setData("postId", postId)
                    .setEntityUserId(entityUserId);

            eventProducer.fireEvent(event);
        }

        return CommunityUtil.getJSONString(0, null, map);
    }


}
