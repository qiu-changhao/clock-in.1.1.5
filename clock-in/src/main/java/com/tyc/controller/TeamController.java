package com.tyc.controller;

import com.tyc.common.base.Result;
import com.tyc.domain.Team;
import com.tyc.service.TeamService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 唐溢聪
 */
@RestController
@RequestMapping("team")
public class TeamController {

    @Resource
    private TeamService teamService;

    @GetMapping("list")
    public Result<List<Team>> list() {
        return Result.success(teamService.list());
    }

    @GetMapping("get/{id}")
    public Result<Team> get(@PathVariable("id") Long id) {
        return Result.success(teamService.getById(id));
    }

    @PostMapping("create")
    public Result<Void> create(@RequestBody Team team) {
        teamService.save(team);
        return Result.success("添加成功");
    }

    @DeleteMapping("del/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        teamService.removeById(id);
        return Result.success("删除成功");
    }

    @PostMapping("update")
    public Result<Void> update(@RequestBody Team team) {
        teamService.updateById(team);
        return Result.success("修改成功");
    }
}
