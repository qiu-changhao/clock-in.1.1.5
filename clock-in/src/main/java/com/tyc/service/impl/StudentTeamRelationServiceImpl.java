package com.tyc.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tyc.domain.StudentTeamRelation;
import com.tyc.mapper.StudentTeamRelationMapper;
import com.tyc.service.StudentTeamRelationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 唐溢聪
 */
@Service
public class StudentTeamRelationServiceImpl extends ServiceImpl<StudentTeamRelationMapper, StudentTeamRelation> implements StudentTeamRelationService {

    @Override
    public List<Long> getTeamIdListByStudentId(Long sId) {
        LambdaQueryWrapper<StudentTeamRelation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StudentTeamRelation::getSId, sId);
        return CollUtil.getFieldValues(list(queryWrapper), "teamId", Long.class);
    }
}
