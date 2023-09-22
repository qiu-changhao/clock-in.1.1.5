package com.tyc.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tyc.common.base.Result;
import com.tyc.common.base.ResultPage;
import com.tyc.common.utils.CurrentAccountUtil;
import com.tyc.domain.ClockInRecord;
import com.tyc.domain.Student;
import com.tyc.domain.StudentTeamRelation;
import com.tyc.domain.Team;
import com.tyc.domain.dto.QueryStudentDto;
import com.tyc.domain.vo.ClockInRecordVo;
import com.tyc.service.StudentService;
import com.tyc.service.StudentTeamRelationService;
import com.tyc.service.TeamService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author 唐溢聪
 */
@RestController
@RequestMapping("student")
public class StudentController {

    @Resource
    private StudentService studentService;
    @Resource
    private StudentTeamRelationService studentTeamRelationService;

    @GetMapping("bindingTeam/{teamId}")
    public Result<Void> bindingTeam(@PathVariable Long teamId) {
        studentService.bindingTeam(teamId);
        return Result.success("绑定班级成功");
    }

    @GetMapping("getTeamList")
    public Result<List<Team>> getTeamList() {
        return Result.success(studentService.getTeamList());
    }

    @GetMapping("clockIn/{cId}")
    public Result<Void> clockIn(@PathVariable Long cId) {
        studentService.clockIn(cId);
        return Result.success("打卡成功");
    }

    @GetMapping("getClockInRecordList")
    public Result<List<ClockInRecordVo>> getClockInRecordList() {
        return Result.success(studentService.getClockInRecordList());
    }

    @DeleteMapping("delTeam/{teamId}")
    public Result<Void> delTeam(@PathVariable Long teamId) {
        studentTeamRelationService.remove(new LambdaQueryWrapper<StudentTeamRelation>()
                .eq(StudentTeamRelation::getTeamId, teamId)
                .eq(StudentTeamRelation::getSId, CurrentAccountUtil.getId())
        );
        return Result.success();
    }

    /**
     * 分页查看
     */
    @GetMapping("page")
    public ResultPage<Student> page(QueryStudentDto queryStudentDto) {
        IPage<Student> page = studentService.page(queryStudentDto.page());
        return new ResultPage<>(page.getRecords(), page.getTotal());
    }

    /**
     * 获取
     */
    @GetMapping("getStudent/{id}")
    public Result<Student> getStudent(@PathVariable Long id) {
        return Result.success(studentService.getById(id));
    }

    /**
     * 添加
     */
    @PostMapping("add")
    public Result<Void> add(@RequestBody Student student) {
        studentService.save(student);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("update")
    public Result<Void> edit(@RequestBody Student student) {
        studentService.updateById(student);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("delete/{ids}")
    public Result<Void> delete(@PathVariable String[] ids) {
        studentService.removeByIds(Arrays.asList(ids));
        return Result.success();
    }
}
