package com.chaozhis.web;

import com.chaozhis.dto.UserDTO;
import com.chaozhis.service.BaseService;
import com.chaozhis.utils.DESUtil;
import com.chaozhis.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 注册
 */
@Controller
public class UserController {

    @Autowired
    private BaseService baseService;

    // fangying
    @RequestMapping(value = "reg", method = {RequestMethod.GET})
    public String reg(Model model, HttpServletRequest request) {
        String rid = WebUtils.getParamValue(request, "rid");
        model.addAttribute("rid", rid);
        return "reg";
    }

    //fangying
    @RequestMapping(value = "login", method = {RequestMethod.GET})
    public String login() {
        return "login";
    }

    //fangying
    @RequestMapping(value = "login", method = {RequestMethod.POST})
    public String loginPost(HttpServletRequest request) {
        String username = WebUtils.getParamValue(request, "username");
        String password = WebUtils.getParamValue(request, "password");
        String sql = "select * from cz_web_user where phone = ? and password = ?";
        DESUtil des = new DESUtil("czkey");
        UserDTO user = baseService.executeQueryOneRecord(sql, UserDTO.class, username, des.encrypt(password));
        if (user == null) {
            return "redirect:/login";
        } else {
            WebUtils.setSessionValue(request, "user", user);
        }
        return "redirect:/u/user-center";
    }

    //fangying
    @RequestMapping(value = "u/login-out", method = {RequestMethod.GET})
    public String loginOut(HttpServletRequest request) {
        WebUtils.removeSessionValue(request, "user");
        request.getSession().invalidate();
        return "redirect:/";
    }

    // fangying
    @RequestMapping(value = "u/user-center", method = {RequestMethod.GET})
    public String userCenter() {
        return "user-center";
    }

    // fangying
    @RequestMapping(value = "u/invite-info", method = {RequestMethod.GET})
    public String inviteInfo(HttpServletRequest request, Model model) {
        String sql = "select * from cz_web_user where referee_id = ? and status = 1";
        List<UserDTO> inviteList = baseService.executeQuery(sql, UserDTO.class, ((UserDTO) WebUtils.getSessionValue(request, "user")).getId());
        model.addAttribute("inviteList", inviteList);
        return "invite-info";
    }

    // fangying
    @RequestMapping(value = "u/post-list", method = {RequestMethod.GET})
    public String shareAudit() {
        return "post-list";
    }

}
