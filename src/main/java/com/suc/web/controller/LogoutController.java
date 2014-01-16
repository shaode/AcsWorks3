package com.suc.web.controller;

import java.util.Enumeration;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/logout")
public class LogoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger     logger           = Logger.getLogger(LogoutController.class);

    @SuppressWarnings("unchecked")
    @RequestMapping(method = RequestMethod.GET)
    public String doLogout(HttpServletRequest request, HttpServletResponse response, Model model) {
        logger.info("doLogout");
        // 清除session
        Enumeration<String> em = request.getSession().getAttributeNames();
        while (em.hasMoreElements()) {
            request.getSession().removeAttribute(em.nextElement().toString());
        }
        request.getSession().invalidate();
        model.addAttribute("message", "请登录！");
        return "login";
    }
}
