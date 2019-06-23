package com.michael.demo.spring.security.security;// package com.scales.api.admin.config.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Set;

/**
 * 类功能描述:
 * <pre>
 *   登录用户封装,实现 UserDetails
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/06/16 10:16
 */
public class LoginUser implements UserDetails {

    private static final long serialVersionUID = 1L;

    private String password;
    private final String username;
    private final String salt;
    private final boolean enabled;
    private final Set<GrantedAuthority> authorities;

    public LoginUser(
            String username,
            String password,
            String salt,
            boolean enabled,
            Set<GrantedAuthority> authorities
    ) {
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    @JsonIgnore
    public String getSalt() {
        return salt;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public static LoginUserBuilder builder() {
        return new LoginUserBuilder();
    }

    public static class LoginUserBuilder {

        private String password;
        private String username;
        private String salt;
        private boolean enabled;
        private Set<GrantedAuthority> authorities;

        private LoginUserBuilder() {}

        public LoginUserBuilder username(String username) {
            Assert.notNull(username, "username cannot be null");
            this.username = username;
            return this;
        }

        public LoginUserBuilder password(String password) {
            Assert.notNull(password, "password cannot be null");
            this.password = password;
            return this;
        }

        public LoginUserBuilder salt(String salt) {
            Assert.notNull(salt, "salt cannot be null");
            this.salt = salt;
            return this;
        }

        public LoginUserBuilder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public LoginUserBuilder authorities(Set<GrantedAuthority> authorities) {
            this.authorities = authorities;
            return this;
        }

        public UserDetails build() {
            return new LoginUser(username, password, salt, enabled, authorities);
        }
    }
}
