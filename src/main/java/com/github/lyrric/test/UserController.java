package com.github.lyrric.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理
 */
@Controller
@RequestMapping("/users")
public class UserController {

    /**
     * 分页查询信息
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String list(Model model, User user, @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber)
    {
        Page<User> page = new Page<>();
        List<User> users = new ArrayList<>();
        for (int i=0; i<5; i++) {
            User user1 = new User();
            user1.setEmail("1");
            user1.setId(1);
            user1.setEnabled(true);
            user1.setName("康康");
            user1.setPhone("123456");
            user1.setUsername("康康");
            users.add(user1);
        }
        page.setContent(users);
        page.setPage(1);
        page.setSize(users.size());
        page.setNumber(1);
        //分页查询数据
        model.addAttribute("page", page);
        //查询条件
        model.addAttribute("user", user);
        //页面标题
        model.addAttribute("title", "用户管理");
        //转到待渲染模板，所有模板都在templates文件夹下，users/list指templates文件夹下的users文件夹下的list.html页面。
        return "list";
    }
}
