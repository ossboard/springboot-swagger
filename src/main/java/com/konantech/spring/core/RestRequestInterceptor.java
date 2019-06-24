package com.konantech.spring.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RestRequestInterceptor extends HandlerInterceptorAdapter {
    private static Logger log = LoggerFactory.getLogger(RestRequestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String port = (request.getServerPort() == 80) ? "" : ":" + request.getServerPort();
        String url = "http://" + request.getServerName() + port + request.getContextPath() + request.getServletPath();
        String fullUrl = url;
        String queryString = request.getQueryString();
        if (queryString != null) {
            fullUrl += "?" + queryString;
        }
        log.debug(request.getRemoteAddr() + " " + request.getMethod() + " " + fullUrl);
        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex) {

    }
}
