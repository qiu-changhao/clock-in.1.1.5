package com.tyc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tyc.domain.Teacher;
import com.tyc.domain.vo.ClockInStudentVo;
import com.tyc.domain.vo.StatisticsStudentVo;

import java.util.List;

/**
 * @author 唐溢聪
 */
public interface TeacherService extends IService<Teacher> {

    /**
     * 根据老师ID统计学生
     *
     * @param tId 老师ID
     * @return 列表
     */
    List<StatisticsStudentVo> statisticsStudentListByTeacherId(Long tId);

    /**
     * 获取打卡详情
     *
     * @param cId 打卡ID
     * @return 打卡详情
     */
    List<ClockInStudentVo> getClockInDetailList(Long cId);
}
