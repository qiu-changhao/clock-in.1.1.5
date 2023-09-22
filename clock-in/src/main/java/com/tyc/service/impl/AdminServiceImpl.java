package com.tyc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tyc.domain.Admin;
import com.tyc.mapper.AdminMapper;
import com.tyc.service.AdminService;
import org.springframework.stereotype.Service;

/**
 * @author 唐溢聪
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
}
