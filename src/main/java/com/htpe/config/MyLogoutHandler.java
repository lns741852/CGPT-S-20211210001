package com.htpe.config;


import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import java.util.Arrays;
import org.springframework.util.StringUtils;

/**
 * 自訂路徑 Cookie註銷 
 *
 */

@Component
public final class MyLogoutHandler implements LogoutHandler {
    private final List<String> cookiesToClear;

    public MyLogoutHandler(String... cookiesToClear) {
        Assert.notNull(cookiesToClear, "List of cookies cannot be null");
        this.cookiesToClear = Arrays.asList(cookiesToClear);
    }

    public void logout(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) {
        for (String cookieName : cookiesToClear) {
            Cookie cookie = new Cookie(cookieName, null);
            String cookiePath = request.getContextPath() + "";
            if (!StringUtils.hasLength(cookiePath)) {
                cookiePath = "/HTPE";
            }
            cookie.setPath(cookiePath);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }
}

