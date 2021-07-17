package per.owisho.learn.usercenter.authserver.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import per.owisho.learn.usercenter.common.dto.UserDTO;

@Component
public class UserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //TODO 查询用户信息，用于用户校验
        UserDetails user = User.builder().username(username).password("123456").roles("admin").build();
        return user;
    }

    public UserDTO loadUserDetail(String username){
        //TODO 查询userDTO信息，用于返回给client
        UserDTO user = new UserDTO();
        user.setUserName(username);
        user.setDesc("this is a test user");
        user.setEmail("yangw@opera.com");
        user.setPhone("110");
        user.setProtrait("https://img2.baidu.com/it/u=1337391829,911492398&fm=26&fmt=auto&gp=0.jpg");
        return user;
    }
}
