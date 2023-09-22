package com.tyc.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 管理员登录VO
 *
 * @author 唐溢聪
 */
@Data
public class AdminLoginVo implements Serializable {

    /**
     * 令牌
     */
    private String token;
}
