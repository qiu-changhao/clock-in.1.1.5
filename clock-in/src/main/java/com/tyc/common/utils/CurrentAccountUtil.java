package com.tyc.common.utils;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.util.AntPathMatcher;

import java.util.List;

/**
 * 用户工具类
 *
 * @author 唐溢聪
 */
public class CurrentAccountUtil {

    /**
     * 用户ID
     */
    private static final ThreadLocal<Long> ID = new ThreadLocal<>();

    /**
     * 类型(0学生；1老师)
     */
    private static final ThreadLocal<Byte> TYPE = new ThreadLocal<>();

    private static final ThreadLocal<Long> ADMIN_ID = new ThreadLocal<>();

    public static Long getId() {
        return ID.get();
    }

    public static void setId(Long id) {
        ID.set(id);
    }

    public static void removeId() {
        ID.remove();
    }

    public static Long getAdminId() {
        return ADMIN_ID.get();
    }

    public static void setAdminId(Long adminId) {
        ADMIN_ID.set(adminId);
    }

    public static void removeAdminId() {
        ADMIN_ID.remove();
    }

    public static Byte getType() {
        return TYPE.get();
    }

    public static void setType(Byte type) {
        TYPE.set(type);
    }

    public static void removeType() {
        TYPE.remove();
    }
}
