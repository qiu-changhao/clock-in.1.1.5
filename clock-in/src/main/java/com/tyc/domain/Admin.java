package com.tyc.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 唐溢聪
 */
@Data
@TableName("t_admin")
public class Admin {
    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;
}
