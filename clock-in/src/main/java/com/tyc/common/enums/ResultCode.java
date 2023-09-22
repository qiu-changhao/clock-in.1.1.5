package com.tyc.common.enums;

/**
 * 消息代码
 *
 * @author 唐溢聪
 */
public enum ResultCode {
    /**
     * 成功
     */
    SUCCESS(200, "操作成功"),
    /**
     * 错误
     */
    ERROR(500, "操作失败"),
    /**
     * 未授权
     */
    UNAUTHORIZED(401, "用户未授权"),
    /**
     * 禁止
     */
    FORBIDDEN(403, "用户授权已过期"),
    /**
     * 404
     */
    NOT_FOUND(404, "接口404");

    /**
     * 编码
     */
    private final Integer code;

    /**
     * 消息
     */
    private final String message;

    private static ResultCode getCode(Integer code) {
        ResultCode[] resultCodes = ResultCode.values();
        for (ResultCode resultCode : resultCodes) {
            if (resultCode.code.equals(code)) {
                return resultCode;
            }
        }
        return null;
    }

    public static String getMessage(Integer code) {
        ResultCode resultCode = getCode(code);
        if (resultCode == null) {
            return "编码不存在";
        }
        return resultCode.message;
    }

    ResultCode(final Integer code, final String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
