package com.jc.community.controller;

import com.jc.community.entity.DiscussPost;
import com.jc.community.entity.Page;
import com.jc.community.entity.User;
import com.jc.community.service.DiscussPostService;
import com.jc.community.service.LikeService;
import com.jc.community.service.UserService;
import com.jc.community.util.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController implements CommunityConstant {
    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndexPage(Model model , Page page,
                               @RequestParam(name = "orderMode", defaultValue = "0") int orderMode){
        // 方法调用前，springMVC会自动实例化model和page，并将page注入model
        //所以，在thymeleaf中可以直接访问page对象中的数据
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index?orderMode=" + orderMode);

        List<DiscussPost> list = discussPostService
                .findDiscussPosts(0, page.getOffset(), page.getLimit(), orderMode);
        ArrayList<Map<String, Object>> discussPosts = new ArrayList<>();
        if (list != null){
            for (DiscussPost discussPost : list) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("post",discussPost);
                User user = userService.findUserById(discussPost.getUserId());
                map.put("user",user);

                long likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_POST, discussPost.getId());
                map.put("likeCount", likeCount);

                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts",discussPosts);
        model.addAttribute("orderMode", orderMode);

        return "/index";
    }

    @RequestMapping(path = "/error", method = RequestMethod.GET)
    public String getErrorPage() {
        return "/error/500";
    }

    @RequestMapping(path = "/denied", method = RequestMethod.GET)
    public String getDeniedPage() {
        return "/error/404";
    }

}
