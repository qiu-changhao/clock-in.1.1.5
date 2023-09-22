package com.tyc.domain.dto;

import com.tyc.common.base.BaseQuery;
import com.tyc.domain.Teacher;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 唐溢聪
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryTeacherDto extends BaseQuery<Teacher> {
}
