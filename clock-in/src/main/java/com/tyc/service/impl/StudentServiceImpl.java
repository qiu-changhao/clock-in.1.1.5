package com.tyc.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tyc.common.utils.CurrentAccountUtil;
import com.tyc.domain.*;
import com.tyc.domain.vo.ClockInRecordVo;
import com.tyc.mapper.StudentMapper;
import com.tyc.service.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 唐溢聪
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Resource
    private ClockInRecordService clockInRecordService;
    @Resource
    private ClockInSetService clockInSetService;
    @Resource
    private StudentTeamRelationService studentTeamRelationService;
    @Resource
    private TeamService teamService;

    @Override
    public void clockIn(Long cId) {
        Long sId = CurrentAccountUtil.getId();
        Date now = new Date();
        ClockInRecord clockInRecord = new ClockInRecord();
        clockInRecord.setSId(sId);
        clockInRecord.setCId(cId);
        clockInRecordService.save(clockInRecord);
    }

    @Override
    public void bindingTeam(Long teamId) {
        studentTeamRelationService.save(new StudentTeamRelation(){{
            setSId(CurrentAccountUtil.getId());
            setTeamId(teamId);
        }});
    }

    @Override
    public List<Team> getTeamList() {
        List<Long> teamIdList = studentTeamRelationService.getTeamIdListByStudentId(CurrentAccountUtil.getId());
        if (ObjectUtil.isEmpty(teamIdList)){
            return new ArrayList<>();
        }
        return teamService.listByIds(teamIdList);
    }

    @Override
    public List<ClockInRecordVo> getClockInRecordList() {
        LambdaQueryWrapper<ClockInRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ClockInRecord::getSId,CurrentAccountUtil.getId());
        return ClockInRecordVo.init(clockInRecordService.list(queryWrapper),clockInSetService);
    }
}
