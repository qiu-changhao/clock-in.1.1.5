package com.tyc.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tyc.common.exception.ServiceException;
import com.tyc.common.utils.CurrentAccountUtil;
import com.tyc.domain.ClockInSet;
import com.tyc.domain.vo.ClockInSetVo;
import com.tyc.mapper.ClockInSetMapper;
import com.tyc.service.ClockInRecordService;
import com.tyc.service.ClockInSetService;
import com.tyc.service.StudentTeamRelationService;
import com.tyc.service.TeamService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 唐溢聪
 */
@Service
public class ClockInSetServiceImpl extends ServiceImpl<ClockInSetMapper, ClockInSet> implements ClockInSetService {

    @Resource
    private TeamService teamService;
    @Resource
    private StudentTeamRelationService studentTeamRelationService;
    @Resource
    private ClockInRecordService clockInRecordService;

    @Override
    public List<ClockInSetVo> getClockInSetListByCurrent() {
        Byte type = CurrentAccountUtil.getType();
        LambdaQueryWrapper<ClockInSet> queryWrapper = new LambdaQueryWrapper<>();
        if (type == 0) {
            List<Long> teamIdList = studentTeamRelationService.getTeamIdListByStudentId(CurrentAccountUtil.getId());
            if (ObjectUtil.isEmpty(teamIdList)) {
                throw new ServiceException("当前学生没有报名，请先去报名");
            }
            queryWrapper.in(ClockInSet::getTeamId, teamIdList);
        } else {
            queryWrapper.eq(ClockInSet::getTId, CurrentAccountUtil.getId());
        }
        return ClockInSetVo.init(list(queryWrapper), teamService, type == 1 ? null : clockInRecordService);
    }

    @Override
    public ClockInSetVo getClockIn() {
        List<ClockInSetVo> clockInSetList = getClockInSetListByCurrent();
        clockInSetList = clockInSetList.stream().filter(ClockInSetVo::isExpired).filter(ClockInSetVo::isClockIn).collect(Collectors.toList());
        if (ObjectUtil.isEmpty(clockInSetList)) {
            return null;
        }
        return clockInSetList.get(0);
    }
}
