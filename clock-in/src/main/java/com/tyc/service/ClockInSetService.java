package com.tyc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tyc.domain.ClockInSet;
import com.tyc.domain.vo.ClockInSetVo;

import java.util.List;

/**
 * @author 唐溢聪
 */
public interface ClockInSetService extends IService<ClockInSet> {

    /**
     * 根据当前用户获取打卡设置列表
     *
     * @return 打卡设置列表
     */
    List<ClockInSetVo> getClockInSetListByCurrent();

    /**
     * 获取当前用户打卡设置
     *
     * @return 打卡设置
     */
    ClockInSetVo getClockIn();
}
