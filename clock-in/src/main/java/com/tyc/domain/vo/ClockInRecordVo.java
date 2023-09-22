package com.tyc.domain.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.tyc.domain.ClockInRecord;
import com.tyc.domain.ClockInSet;
import com.tyc.service.ClockInSetService;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 唐溢聪
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ClockInRecordVo extends ClockInRecord implements Serializable {

    /**
     * 打卡标题
     */
    private String clockInTitle;

    public static List<ClockInRecordVo> init(List<ClockInRecord> clockInRecordList, ClockInSetService clockInSetService) {
        return clockInRecordList.stream().map(e -> {
            ClockInRecordVo clockInRecordVo = BeanUtil.copyProperties(e, ClockInRecordVo.class);
            Long cId = e.getCId();
            ClockInSet clockInSet = clockInSetService.getById(cId);
            if (ObjectUtil.isNotEmpty(clockInSet)){
                clockInRecordVo.setClockInTitle(clockInSet.getTitle());
            }
            return clockInRecordVo;
        }).collect(Collectors.toList());
    }
}
