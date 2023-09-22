package com.tyc.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tyc.common.base.Result;
import com.tyc.common.exception.ServiceException;
import com.tyc.common.utils.TokenUtil;
import com.tyc.domain.Student;
import com.tyc.domain.Teacher;
import com.tyc.domain.dto.LoginOrRegisterDto;
import com.tyc.domain.vo.TokenVo;
import com.tyc.service.StudentService;
import com.tyc.service.TeacherService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author 唐溢聪
 */
@RestController
@RequestMapping
public class IndexController {

    @Resource
    private StudentService studentService;

    @Resource
    private TeacherService teacherService;

    /**
     * 登录
     */
    @PostMapping("login")
    public Result<TokenVo> login(@RequestBody LoginOrRegisterDto loginOrRegisterDto) {
        Byte type = loginOrRegisterDto.getType();
        String password = SecureUtil.md5(loginOrRegisterDto.getPassword());
        Object o;
        if (type == 0) {
            LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Student::getUsername, loginOrRegisterDto.getUsername());
            o = studentService.getOne(queryWrapper);
        } else {
            LambdaQueryWrapper<Teacher> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Teacher::getUsername, loginOrRegisterDto.getUsername());
            o = teacherService.getOne(queryWrapper);
        }
        if (ObjectUtil.isEmpty(o)) {
            throw new ServiceException("用户名错误");
        }
        Student student = BeanUtil.copyProperties(o, Student.class);

        if (!student.getPassword().equals(password)) {
            throw new ServiceException("密码错误，请重新输入！");
        }
        return Result.success(new TokenVo() {{
            setToken(TokenUtil.createToken(new HashMap<String, Object>(2) {{
                put("id", student.getId());
                put("type", type);
            }}));
            setType(type);
        }});
    }

    /**
     * 注册
     */
    @PostMapping("register")
    public Result<Void> register(@RequestBody LoginOrRegisterDto loginOrRegisterDto) {
        Byte type = loginOrRegisterDto.getType();
        String password = SecureUtil.md5(loginOrRegisterDto.getPassword());
        loginOrRegisterDto.setPassword(password);
        if (type == 0) {
            LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Student::getUsername, loginOrRegisterDto.getUsername());
            Student student = studentService.getOne(queryWrapper);
            if (ObjectUtil.isNotEmpty(student)) {
                throw new ServiceException("该用户已经存在，请勿重新注册");
            }
            studentService.save(BeanUtil.copyProperties(loginOrRegisterDto, Student.class));
        } else {
            LambdaQueryWrapper<Teacher> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Teacher::getUsername, loginOrRegisterDto.getUsername());
            Teacher teacher = teacherService.getOne(queryWrapper);
            if (ObjectUtil.isNotEmpty(teacher)) {
                throw new ServiceException("该用户已经存在，请勿重新注册");
            }
            teacherService.save(BeanUtil.copyProperties(loginOrRegisterDto, Teacher.class));
        }
        return Result.success("注册成功");
    }
}
