package com.tyc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tyc.domain.Team;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface TeamMapper extends BaseMapper<Team> {
}
