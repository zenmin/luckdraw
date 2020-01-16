package com.zm.cj.intercepter;

import com.zm.cj.entity.CommonException;
import com.zm.cj.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Describle This Class Is 验证所有请求权限
 * @Author ZengMin
 * @Date 2019/1/3 19:18
 */
@Slf4j
public class RequestInterceptor implements HandlerInterceptor {

    @Autowired
    RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            throw new CommonException(999, "禁止访问！");
        }
        String json = redisUtil.get("TOKEN");
        if (StringUtils.isBlank(json)) {
            throw new CommonException(999, "禁止访问！");
        }
        if (!json.equals(token)) {
            throw new CommonException(999, "禁止访问！");
        }
        return true;
    }

}
