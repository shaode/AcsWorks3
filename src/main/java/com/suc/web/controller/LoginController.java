package com.suc.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.suc.web.entity.MUser;
import com.suc.web.jpa.service.MUserService;
import com.suc.web.utils.Constants;

@Controller
@RequestMapping("/login")
@SessionAttributes(Constants.USER_INFO_SESSION)
public class LoginController {
    private static Logger logger = Logger.getLogger(LoginController.class);
    @Autowired
    private MUserService  userService;

    @RequestMapping(method = RequestMethod.POST)
    public String doLogin(MUser user, Model model) throws Exception {
        logger.debug("user=" + user);
        String userName = user.getName();
        MUser user1 = userService.findByName(userName);
        if (user1 == null) {
            model.addAttribute("message", "用户不存在");
            return "login";
        } else if (user.getPassword() == null || !user.getPassword().equals(user1.getPassword())) {
            model.addAttribute("message", "密码错误");
            return "login";
        } else {
            model.addAttribute(Constants.USER_INFO_SESSION, user1);
            return "main";
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public String toLoginForm(Model model) throws Exception {
        logger.debug("Not allow login in Get");
        model.addAttribute("message", "请登录！");
        return "login";
    }
}
