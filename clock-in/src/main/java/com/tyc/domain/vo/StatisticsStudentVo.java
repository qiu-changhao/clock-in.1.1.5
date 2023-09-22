package com.tyc.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author 唐溢聪
 */
@Data
public class StatisticsStudentVo {

    private Long cId;

    private String title;

    private String className;

    private Integer clockInCount;

    private Integer studentCount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date endTime;
}
