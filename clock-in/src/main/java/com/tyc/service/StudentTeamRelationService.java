package com.tyc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tyc.domain.StudentTeamRelation;

import java.util.List;

/**
 * @author 唐溢聪
 */
public interface StudentTeamRelationService extends IService<StudentTeamRelation> {

    /**
     * 根据学生ID获取班级ID列表
     *
     * @param sId 学生ID
     * @return 班级ID列表
     */
    List<Long> getTeamIdListByStudentId(Long sId);
}
