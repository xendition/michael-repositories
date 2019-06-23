package com.michael.demo.spring.security.security;// package com.scales.api.admin.config.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * 类功能描述:
 * <pre>
 *   登录用户服务封装,实现 UserDetailsService
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/06/16 10:48
 */
@Service("loginUserService")
public class LoginUserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        /**
         * 1. 根据用户名在数据库中查询
         * 2. 如果用户不存在 throw new UsernameNotFoundException
         * 3. 用户存在，根据用户名或者用户ID查询用户权限集合
         * 4. 将权限集合封装为合适的权限对象
         * 5. 封装 UserDetails 返回
         */

        // 以下代码为简化模拟  —— 假装从DB拿到了用户并封装完成
        String passwordSalt = "michael";

        PasswordEncoder passwordEncoder = new MyPasswordEncoder(passwordSalt);
        String encodedPassword = passwordEncoder.encode("root");

        return LoginUser.builder()
                        .username(username)
                        .password(encodedPassword)
                        .salt(passwordSalt)
                        .enabled(true)
                        .authorities(authorities)
                        .build()
                ;
    }

    private static Set<GrantedAuthority> authorities = new HashSet<>();

    static {
        authorities.add(new MyGrantedAuthority("sys:sysDictM:add"));
        authorities.add(new MyGrantedAuthority("sys:sysDictM:edit"));
        authorities.add(new MyGrantedAuthority("sys:sysDictM:view"));
        authorities.add(new MyGrantedAuthority("sys:sysDictM:delete"));
    }
}
