package per.owisho.learn.usercenter.client.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import per.owisho.learn.usercenter.common.dto.UserDTO;

import java.util.Map;

public class UserInfoUtil {

    /**
     * 将内存用户信息提取出来的方法
     * @return
     */
    public static UserDTO getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        DefaultOAuth2User user = (DefaultOAuth2User)authentication.getPrincipal();
        Map<String,Object> attrMap = user.getAttributes();
        UserDTO dto = JSONObject.parseObject(JSONObject.toJSONString(attrMap),UserDTO.class);
        return dto;
    }

}
