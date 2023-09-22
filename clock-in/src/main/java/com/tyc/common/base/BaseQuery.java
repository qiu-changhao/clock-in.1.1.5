package com.tyc.common.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.io.Serializable;

/**
 * 基础查询
 *
 * @author 唐溢聪
 */
@Data
public class BaseQuery<T> implements Serializable {

    /**
     * 当前页数
     */
    private Long pageNum;

    /**
     * 当前条数
     */
    private Long pageSize;

    public Page<T> page() {
        if (pageNum == null) {
            pageNum = 1L;
        }
        if (pageSize == null) {
            pageSize = 10L;
        }
        return Page.of(pageNum, pageSize);
    }
}
