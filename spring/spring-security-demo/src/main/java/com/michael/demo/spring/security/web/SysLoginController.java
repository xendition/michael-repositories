package com.michael.demo.spring.security.web;

import com.michael.demo.spring.security.model.JsonResult;
import com.michael.demo.spring.security.security.MyPasswordEncoder;
import com.michael.demo.spring.security.security.jwt.JwtAuthenticationToken;
import com.michael.demo.spring.security.security.jwt.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 类功能描述:
 * <pre>
 *   系统登录登出处理
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/06/15 20:21
 */
// @Api(tags = "APP版本管理管理")
@RestController
@RequestMapping("/sys/login")
public class SysLoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    // @ResponseStatus(HttpStatus.FORBIDDEN)
    @GetMapping("/noLogin")
    public JsonResult noLogin() {
        return JsonResult.failed("需要登录");
    }

    @GetMapping("/login")
    public JsonResult login(HttpServletRequest request) {

        String username = "root";
        String password = "root";
        String passwordSalt = "michael";

        String encodedPassword = new MyPasswordEncoder(passwordSalt).encode(password);

        JwtAuthenticationToken token = SecurityUtils.login(request, username, encodedPassword, authenticationManager);

        return JsonResult.succeed(token,"登录成功");
    }

    @GetMapping("/logout")
    public JsonResult logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return JsonResult.succeed("登出成功");
    }
}
