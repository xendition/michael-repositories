package com.michael.demo.spring.security.security.jwt;

import com.michael.demo.spring.security.security.LoginUser;
import com.michael.demo.spring.security.security.LoginUserService;
import com.michael.demo.spring.security.security.MyPasswordEncoder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * 类功能描述:
 * <pre>
 *   JwtAuthenticationProvider 用于判断授权的有效性、用户的有效性
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/06/22 22:45
 */
@Component("jwtAuthenticationProvider")
public class JwtAuthenticationProvider extends DaoAuthenticationProvider {

    public JwtAuthenticationProvider(LoginUserService loginUserService) {
        this.setUserDetailsService(loginUserService);
    }

    @Override
    protected void additionalAuthenticationChecks(
            UserDetails userDetails,
            UsernamePasswordAuthenticationToken authentication
    ) throws AuthenticationException {

        if (authentication.getCredentials() == null) {

            if (logger.isDebugEnabled()) {
                logger.debug("Authentication failed: no credentials provided");
            }
            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials",
                    "Bad credentials"
            ));
        }

        String presentedPassword = authentication.getCredentials().toString();

        MyPasswordEncoder passwordEncoder = new MyPasswordEncoder(((LoginUser) userDetails).getSalt());

        if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {

            if (logger.isDebugEnabled()) {
                logger.debug("Authentication failed: password does not match stored value");
            }

            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials",
                    "Bad credentials"
            ));
        }
    }
}
