package com.michael.demo.spring.security.security;// package com.scales.api.admin.config.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.util.Assert;

/**
 * 类功能描述:
 * <pre>
 *   用户权限资源封装
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/06/16 11:05
 */
public class MyGrantedAuthority implements GrantedAuthority {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final String authority;

    public MyGrantedAuthority(String authority) {
        Assert.hasText(authority, "需要一个明确的授权");
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }

    @Override
    public int hashCode() {
        return this.authority.hashCode();
    }

    @Override
    public String toString() {
        return this.authority;
    }
}
