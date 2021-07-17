package per.owisho.learn.usercenter.authserver.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import per.owisho.learn.usercenter.authserver.service.UserDetailService;
import per.owisho.learn.usercenter.common.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Slf4j
@RestController
public class UserInfoController {

    @Autowired
    private FactoryBean<ConsumerTokenServices> consumerTokenServices;

    @Autowired
    private UserDetailService userDetailService;

    @PostMapping("/user")
    public Object user(HttpServletRequest request, HttpServletResponse response) {
        Enumeration<String> params = request.getParameterNames();
        Enumeration<String> headers = request.getHeaderNames();

        System.out.println("请求参数开始");
        while (params.hasMoreElements()) {
            String next = params.nextElement();
            System.out.println(next);
        }
        System.out.println("请求参数结束");

        System.out.println("请求头开始");
        while (headers.hasMoreElements()) {
            String next = headers.nextElement();
            System.out.println(next);
        }
        System.out.println("请求头结束");

        try {
            String accessToken = request.getParameter("access_token");
            DefaultTokenServices tokenServices = (DefaultTokenServices) consumerTokenServices.getObject();
            OAuth2Authentication auth2Authentication = tokenServices.loadAuthentication(accessToken);
            User user = (User) auth2Authentication.getPrincipal();
            String userName = user.getUsername();

            //TODO 使用username 查询用户信息
            UserDTO userDTO = userDetailService.loadUserDetail(userName);
            JSONObject resp = JSONObject.parseObject(JSONObject.toJSONString(userDTO));
            return resp;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            response.setStatus(400);
            JSONObject resp = new JSONObject();
            resp.put("error", e.getMessage());
            return resp;
        }
    }


}
