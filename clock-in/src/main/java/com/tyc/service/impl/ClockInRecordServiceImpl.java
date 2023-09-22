package com.tyc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tyc.domain.ClockInRecord;
import com.tyc.mapper.ClockInRecordMapper;
import com.tyc.service.ClockInRecordService;
import org.springframework.stereotype.Service;

/**
 * @author 唐溢聪
 */
@Service
public class ClockInRecordServiceImpl extends ServiceImpl<ClockInRecordMapper, ClockInRecord> implements ClockInRecordService {
}
