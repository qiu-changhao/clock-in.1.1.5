package com.tyc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tyc.domain.ClockInRecord;
import com.tyc.domain.Student;
import com.tyc.domain.Team;
import com.tyc.domain.vo.ClockInRecordVo;

import java.util.List;

/**
 * @author 唐溢聪
 */
public interface StudentService extends IService<Student> {

    /**
     * 打卡
     *
     * @param cId 打卡设置ID
     */
    void clockIn(Long cId);

    /**
     * 绑定班级
     *
     * @param teamId 班级ID
     */
    void bindingTeam(Long teamId);

    /**
     * 获取班级列表
     *
     * @return 班级列表
     */
    List<Team> getTeamList();

    /**
     * 获取记录
     *
     * @return 记录列表
     */
    List<ClockInRecordVo> getClockInRecordList();
}
