package com.tyc.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 唐溢聪
 */
@Data
@TableName("t_clock_in_set")
public class ClockInSet {

    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 老师ID
     */
    private Long tId;

    /**
     * 班级ID
     */
    private Long teamId;

    /**
     * 标题
     */
    private String title;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date startTime;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date endTime;

    /**
     * 经度
     */
    private BigDecimal latitude;

    /**
     * 维度
     */
    private BigDecimal longitude;

    /**
     * 地址
     */
    private String address;

    /**
     * 范围
     */
    private BigDecimal addressRange;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
