package com.tyc.common.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 表格分页数据对象
 *
 * @author 唐溢聪
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("响应分页结果")
public class ResultPage<T> implements Serializable {
    /**
     * 总记录数
     */
    @ApiModelProperty("响应总记录数")
    private long total;

    /**
     * 分页数据
     */
    @ApiModelProperty("响应分页数据")
    private List<T> rows;

    /**
     * 状态码
     */
    @ApiModelProperty("响应码")
    private int code;

    /**
     * 消息内容
     */
    @ApiModelProperty("响应信息")
    private String msg;

    /**
     * 分页
     *
     * @param list  列表数据
     * @param total 总记录数
     */
    public ResultPage(List<T> list, long total) {
        this.rows = list;
        this.total = total;
        success();
    }

    private void success(){
        Result<Void> success = Result.success();
        this.code = success.getCode();
        this.msg = success.getMsg();
    }
}
