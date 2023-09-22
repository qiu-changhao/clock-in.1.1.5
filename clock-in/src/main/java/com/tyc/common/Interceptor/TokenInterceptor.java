package com.tyc.common.Interceptor;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.tyc.common.base.Result;
import com.tyc.common.enums.ResultCode;
import com.tyc.common.utils.CurrentAccountUtil;
import com.tyc.common.utils.ServletUtil;
import com.tyc.common.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 拦截器
 *
 * @author 唐溢聪
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    /**
     * 找不到uri
     */
    private static final String NOT_FOUND_URI = "/error";

    private static final String ADMIN_URI = "/admin";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String uri = request.getRequestURI();
        if (uri.contains(NOT_FOUND_URI)) {
            ServletUtil.renderString(response, JSONUtil.toJsonStr(Result.error(ResultCode.NOT_FOUND.getCode(), ResultCode.NOT_FOUND.getMessage())));
            return false;
        }

        String token = request.getHeader("token");
        if (ObjectUtil.isEmpty(token)) {
            ServletUtil.renderString(response, JSONUtil.toJsonStr(Result.error(ResultCode.ERROR.getCode(), "令牌不能为空")));
            return false;
        }
        try {
            Claims claims = TokenUtil.getClaims(token);
            Long id = claims.get("id", Long.class);
            if (uri.contains(ADMIN_URI)){
                CurrentAccountUtil.setAdminId(id);
            }else{
                CurrentAccountUtil.setId(id);
                CurrentAccountUtil.setType(claims.get("type",Byte.class));
            }
        } catch (Exception e) {
            ServletUtil.renderString(response, JSONUtil.toJsonStr(Result.error(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage())));
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        CurrentAccountUtil.removeId();
        CurrentAccountUtil.removeAdminId();
        CurrentAccountUtil.removeType();
    }
}
