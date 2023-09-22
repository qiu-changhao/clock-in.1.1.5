package com.tyc.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tyc.common.base.Result;
import com.tyc.common.exception.ServiceException;
import com.tyc.common.utils.CurrentAccountUtil;
import com.tyc.common.utils.TokenUtil;
import com.tyc.domain.Admin;
import com.tyc.domain.dto.AdminLoginParamDto;
import com.tyc.domain.vo.AdminLoginVo;
import com.tyc.service.AdminService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 唐溢聪
 */
@RestController
@RequestMapping("admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    /**
     * 登录
     */
    @PostMapping("login")
    public Result<AdminLoginVo> login(@RequestBody @Validated AdminLoginParamDto adminLoginParamDto) {
        String username = adminLoginParamDto.getUsername();
        String password = SecureUtil.md5(adminLoginParamDto.getPassword());
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getUsername, username);
        Admin admin = adminService.getOne(queryWrapper);
        if (ObjectUtil.isEmpty(admin)) {
            throw new ServiceException("用户不存在");
        }
        if (!password.equals(admin.getPassword())) {
            throw new ServiceException("密码不正确");
        }
        HashMap<String, Object> returnMap = new HashMap<>(2);
        returnMap.put("id", admin.getId());
        return Result.success(new AdminLoginVo() {{
            setToken(TokenUtil.createToken(returnMap));
        }});
    }

    /**
     * 查看用户信息
     */
    @GetMapping("getInfo")
    public Result<?> getInfo() {
        Long id = CurrentAccountUtil.getAdminId();
        return Result.success(adminService.getById(id));
    }

    /**
     * 更新密码
     */
    @PostMapping("updatePassword")
    public Result<Void> updatePassword(@RequestBody Map<String, String> params) {
        String oldPassword = SecureUtil.md5(params.get("oldPassword"));
        String newPassword = SecureUtil.md5(params.get("newPassword"));
        Result<?> info = getInfo();
        Object data = info.getData();
        String password = BeanUtil.getFieldValue(data, "password") + "";
        BeanUtil.setFieldValue(data, "password", newPassword);
        if (!password.equals(oldPassword)) {
            throw new ServiceException("旧密码输入错误");
        }
        adminService.updateById(BeanUtil.copyProperties(data, Admin.class));
        return Result.success();
    }

    /**
     * 更新用户
     */
    @PostMapping("updateUser")
    public Result<Void> updateUser(@RequestBody Map<String, Object> params) {
        adminService.updateById(BeanUtil.copyProperties(params, Admin.class));
        return Result.success();
    }
}
