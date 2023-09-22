package com.tyc.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tyc.common.base.Result;
import com.tyc.common.base.ResultPage;
import com.tyc.common.utils.CurrentAccountUtil;
import com.tyc.domain.ClockInSet;
import com.tyc.domain.Teacher;
import com.tyc.domain.dto.CreateClockInDto;
import com.tyc.domain.dto.QueryTeacherDto;
import com.tyc.domain.vo.ClockInStudentVo;
import com.tyc.domain.vo.StatisticsStudentVo;
import com.tyc.service.ClockInSetService;
import com.tyc.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author 唐溢聪
 */
@RestController
@RequestMapping("teacher")
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    @Resource
    private ClockInSetService clockInSetService;

    @PostMapping("setClockIn")
    public Result<Void> setClockIn(@RequestBody CreateClockInDto createClockInDto) {
        ClockInSet clockInSet = BeanUtil.copyProperties(createClockInDto, ClockInSet.class);
        clockInSet.setTId(CurrentAccountUtil.getId());
        clockInSetService.save(clockInSet);
        return Result.success("创建成功");
    }

    @GetMapping("getStatisticsStudentList")
    public Result<List<StatisticsStudentVo>> getStatisticsStudentList() {
        return Result.success(teacherService.statisticsStudentListByTeacherId(CurrentAccountUtil.getId()));
    }

    @GetMapping("getClockInDetailList/{cId}")
    public Result<List<ClockInStudentVo>> getClockInDetailList(@PathVariable Long cId) {
        return Result.success(teacherService.getClockInDetailList(cId));
    }

    /**
     * 分页查看
     */
    @GetMapping("page")
    public ResultPage<Teacher> page(QueryTeacherDto queryTeacherDto) {
        IPage<Teacher> page = teacherService.page(queryTeacherDto.page());
        return new ResultPage<>(page.getRecords(), page.getTotal());
    }

    /**
     * 获取
     */
    @GetMapping("getTeacher/{id}")
    public Result<Teacher> getTeacher(@PathVariable Long id) {
        return Result.success(teacherService.getById(id));
    }

    /**
     * 添加
     */
    @PostMapping("add")
    public Result<Void> add(@RequestBody Teacher teacher) {
        teacherService.save(teacher);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("update")
    public Result<Void> edit(@RequestBody Teacher teacher) {
        teacherService.updateById(teacher);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("delete/{ids}")
    public Result<Void> delete(@PathVariable String[] ids) {
        teacherService.removeByIds(Arrays.asList(ids));
        return Result.success();
    }
}
