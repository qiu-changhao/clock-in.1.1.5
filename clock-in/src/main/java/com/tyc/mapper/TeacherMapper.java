package com.tyc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tyc.domain.Teacher;
import com.tyc.domain.vo.ClockInStudentVo;
import com.tyc.domain.vo.StatisticsStudentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {

    /**
     * 根据老师ID统计学生
     *
     * @param tId 老师ID
     * @return 列表
     */
    @Select("SELECT cis.id 'cId',MAX(cis.title) 'title',MAX(t.name) 'className',COUNT(1) 'clockInCount',MAX(cis.start_time) 'startTime',MAX(cis.end_time) 'endTime',(SELECT COUNT(1) FROM t_student_team_relation WHERE team_id = cis.team_id) 'studentCount' " +
            "FROM t_clock_in_set cis," +
            "t_clock_in_record cir," +
            "t_team t " +
            "WHERE t.id = cis.team_id AND cis.t_id = #{tId} AND cis.id = cir.c_id GROUP BY cis.id")
    List<StatisticsStudentVo> statisticsStudentListByTeacherId(@Param("tId") Long tId);

    /**
     * 获取打卡详情
     *
     * @param cId 打卡ID
     * @return 打卡详情
     */
    @Select("select s.username,cir.create_time 'createTime' from (select s.id,s.username from t_student s,t_student_team_relation str WHERE s.id = str.s_id and " +
            "str.team_id = (select team_id from t_clock_in_set where id = #{cId})) s left join t_clock_in_record cir on s.id = cir.s_id and cir.c_id = #{cId}")
    List<ClockInStudentVo> getClockInDetailList(@Param("cId") Long cId);
}
