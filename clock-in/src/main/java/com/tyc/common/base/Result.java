package com.tyc.common.base;

import com.tyc.common.enums.ResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 操作消息提醒
 *
 * @author 唐溢聪
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("响应结果")
public class Result<T> implements Serializable {

    /**
     * 响应码
     */
    @ApiModelProperty("响应码")
    private int code;

    /**
     * 响应信息
     */
    @ApiModelProperty("响应信息")
    private String msg;

    /**
     * 响应数据
     */
    @ApiModelProperty("响应数据")
    private T data;


    /**
     * 返回成功
     */
    public static Result<Void> success() {
        return success(ResultCode.SUCCESS.getMessage());
    }

    /**
     * 返回成功
     */
    public static Result<Void> success(String msg) {
        return success(msg, null);
    }

    /**
     * 返回成功
     */
    public static <T> Result<T> success(T data) {
        return success(ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 返回成功
     */
    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), msg, data);
    }

    /**
     * 返回失败
     */
    public static Result<Void> error() {
        return error(ResultCode.ERROR.getMessage());
    }

    /**
     * 返回失败
     */
    public static Result<Void> error(String msg) {
        return error(msg, null);
    }

    /**
     * 返回失败
     */
    public static Result<Void> error(int code, String msg) {
        return new Result<>(code, msg, null);
    }

    /**
     * 返回失败
     */
    public static <T> Result<T> error(T data) {
        return error(ResultCode.ERROR.getMessage(), data);
    }

    /**
     * 返回失败
     */
    public static <T> Result<T> error(String msg, T data) {
        return new Result<>(ResultCode.ERROR.getCode(), msg, data);
    }

    public static Result<Void> toResult(boolean flag) {
        if (flag) {
            return success();
        }
        return error();
    }
}
