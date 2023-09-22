package com.tyc.domain.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tyc.common.utils.CurrentAccountUtil;
import com.tyc.domain.ClockInRecord;
import com.tyc.domain.ClockInSet;
import com.tyc.domain.Team;
import com.tyc.service.ClockInRecordService;
import com.tyc.service.TeamService;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 唐溢聪
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ClockInSetVo extends ClockInSet implements Serializable {

    /**
     * 是否过期
     */
    private boolean isExpired;

    /**
     * 是否打卡
     */
    private boolean isClockIn;

    /**
     * 班级名称
     */
    private String className;

    public static List<ClockInSetVo> init(List<ClockInSet> clockInSetList) {
        Date now = new Date();
        return clockInSetList.stream().map(e->{
            ClockInSetVo clockInSetVo = BeanUtil.copyProperties(e, ClockInSetVo.class);
            clockInSetVo.setExpired(DateUtil.between(e.getStartTime(), now, DateUnit.MINUTE, false) > 0 && DateUtil.between(now, e.getEndTime(), DateUnit.MINUTE, false) > 0);
            return clockInSetVo;
        }).collect(Collectors.toList());
    }

    public static List<ClockInSetVo> init(List<ClockInSet> clockInSetList, TeamService teamService){
        List<ClockInSetVo> clockInSetVoList = init(clockInSetList);
        clockInSetVoList.forEach(c->{
            Long teamId = c.getTeamId();
            Team team = teamService.getById(teamId);
            if (ObjectUtil.isNotEmpty(team)){
                c.setClassName(team.getName());
            }
        });
        return clockInSetVoList;
    }

    public static List<ClockInSetVo> init(List<ClockInSet> clockInSetList, TeamService teamService, ClockInRecordService clockInRecordService){
        List<ClockInSetVo> clockInSetVoList = init(clockInSetList,teamService);
        if (ObjectUtil.isNotEmpty(clockInRecordService)){
            clockInSetVoList.forEach(c->{
                Long cId = c.getId();
                Long sId = CurrentAccountUtil.getId();
                LambdaQueryWrapper<ClockInRecord> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(ClockInRecord::getCId,cId);
                queryWrapper.eq(ClockInRecord::getSId,sId);
                c.setClockIn(clockInRecordService.count(queryWrapper) == 0);
            });
        }
        return clockInSetVoList;
    }
}
