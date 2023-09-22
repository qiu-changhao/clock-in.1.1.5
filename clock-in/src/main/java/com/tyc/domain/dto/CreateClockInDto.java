package com.tyc.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 唐溢聪
 */
@Data
public class CreateClockInDto {

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
     * 班级ID
     */
    private Long teamId;
}
