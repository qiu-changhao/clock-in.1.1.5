package com.tyc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tyc.domain.Teacher;
import com.tyc.domain.vo.ClockInStudentVo;
import com.tyc.domain.vo.StatisticsStudentVo;
import com.tyc.mapper.TeacherMapper;
import com.tyc.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 唐溢聪
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Override
    public List<StatisticsStudentVo> statisticsStudentListByTeacherId(Long tId) {
        return baseMapper.statisticsStudentListByTeacherId(tId);
    }

    @Override
    public List<ClockInStudentVo> getClockInDetailList(Long cId) {
        return baseMapper.getClockInDetailList(cId);
    }
}
