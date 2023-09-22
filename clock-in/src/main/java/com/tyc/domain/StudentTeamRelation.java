package com.tyc.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 唐溢聪
 */
@Data
@TableName("t_student_team_relation")
public class StudentTeamRelation {

    /**
     * 学生ID
     */
    private Long sId;

    /**
     * 班级ID
     */
    private Long teamId;
}
