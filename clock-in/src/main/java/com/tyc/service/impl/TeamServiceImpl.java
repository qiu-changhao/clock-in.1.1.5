package com.tyc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tyc.common.utils.CurrentAccountUtil;
import com.tyc.domain.Team;
import com.tyc.mapper.TeamMapper;
import com.tyc.service.StudentTeamRelationService;
import com.tyc.service.TeamService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 唐溢聪
 */
@Service
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team> implements TeamService {
}
