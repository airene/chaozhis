package com.chaozhis.web;

import com.chaozhis.AppConstants;
import com.chaozhis.dto.UserDTO;
import com.chaozhis.service.BaseService;
import com.chaozhis.service.UserService;
import com.chaozhis.utils.CookieUtil;
import com.chaozhis.utils.DESUtil;
import com.chaozhis.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户
 */
@Controller
public class UserController {

    @Autowired
    private BaseService baseService;
    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    // fangying
    @RequestMapping(value = "reg", method = {RequestMethod.GET})
    public String reg(Model model, HttpServletRequest request) {
        String rid = CookieUtil.getCookieValue(request, "rid");
        String flag = WebUtils.getParamValue(request, "flag");
        if (rid != null) {
            model.addAttribute("phone", rid);
        }
        model.addAttribute("flag", flag);
        return "reg";
    }

    // fangying
    @RequestMapping(value = "reg", method = {RequestMethod.POST})
    public String regSave(HttpServletRequest request, RedirectAttributes attr) {
        String phone = WebUtils.getParamValue(request, "phone");
        String valicode = WebUtils.getParamValue(request, "valicode");
        String password = WebUtils.getParamValue(request, "password");
        String nickname = WebUtils.getParamValue(request, "nickname");
        String refphone = WebUtils.getParamValue(request, "refphone");
        String serverValiCode = redisTemplate.opsForValue().get(AppConstants.USER_MOBILE_VALICODE + phone);
        if (valicode.equals(serverValiCode)) {
            String sql = "select id from cz_web_user where phone = ?";
            int referee_id = baseService.executeQueryOneColumnToNumber(sql, refphone);
            DESUtil des = new DESUtil("czkey");
            sql = "insert into cz_web_user (phone,password,nickname,referee_id,source)values(?,?,?,?,?)";
            int user_id = baseService.executeUpdateReturnId(sql, phone, des.encrypt(password), nickname, referee_id, "pc");
            // 登陆
            UserDTO user = userService.queryUserById(user_id);
            WebUtils.setSessionValue(request, "user", user);
        } else {
            attr.addAttribute("flag", true);
            return "redirect:/reg";
        }
        return "redirect:/u/user-center";
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
    @RequestMapping(value = "u/invite-commit/{rid}/{method}", method = {RequestMethod.GET})
    public String inviteCommit(@PathVariable("rid") int rid, @PathVariable("method") String method) {
        int status = "pass".equals(method) ? 2 : 3;
        String sql = "update cz_web_user set status = ? where id = ?";
        baseService.executeUpdate(sql, status, rid);
        return "redirect:/u/invite-info";
    }

    // fangying
    @RequestMapping(value = "u/post-list", method = {RequestMethod.GET})
    public String shareAudit() {
        return "post-list";
    }
}
