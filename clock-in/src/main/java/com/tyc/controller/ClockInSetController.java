package com.tyc.controller;

import com.tyc.common.base.Result;
import com.tyc.domain.vo.ClockInSetVo;
import com.tyc.service.ClockInSetService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 唐溢聪
 */
@RestController
@RequestMapping("clockInSet")
public class ClockInSetController {

    @Resource
    private ClockInSetService clockInSetService;

    @GetMapping("getClockInList")
    public Result<List<ClockInSetVo>> getClockInList() {
        return Result.success(clockInSetService.getClockInSetListByCurrent());
    }

    @GetMapping("getClockIn")
    public Result<ClockInSetVo> getClockIn() {
        return Result.success(clockInSetService.getClockIn());
    }

    @DeleteMapping("del/{id}")
    public Result<Void> del(@PathVariable Long id) {
        clockInSetService.removeById(id);
        return Result.success("删除成功");
    }

}
