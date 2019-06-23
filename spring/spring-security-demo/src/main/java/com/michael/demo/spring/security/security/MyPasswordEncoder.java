package com.michael.demo.spring.security.security;

import cn.hutool.crypto.SecureUtil;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 类功能描述:
 * <pre>
 *   自定义密码编码器
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/06/22 10:34
 */
public class MyPasswordEncoder implements PasswordEncoder {

    private String passwordSalt;

    public MyPasswordEncoder(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    @Override
    public String encode(CharSequence rawPassword) {

        return SecureUtil.md5()
                         .setSalt(passwordSalt.getBytes())
                         .setDigestCount(3)
                         .digestHex(rawPassword.toString(), "UTF-8");

    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.equals(encodedPassword);
    }
}
