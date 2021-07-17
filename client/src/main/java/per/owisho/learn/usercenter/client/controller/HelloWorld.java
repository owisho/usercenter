package per.owisho.learn.usercenter.client.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.owisho.learn.usercenter.client.util.UserInfoUtil;
import per.owisho.learn.usercenter.common.dto.UserDTO;

@RestController
public class HelloWorld {

    @GetMapping("/hello")
    public String hello() {
        UserDTO user = UserInfoUtil.getUser();
        return "hello" + user.getUserName();
    }

}
