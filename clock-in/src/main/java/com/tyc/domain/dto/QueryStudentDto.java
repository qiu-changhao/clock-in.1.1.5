package com.tyc.domain.dto;

import com.tyc.common.base.BaseQuery;
import com.tyc.domain.Student;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 唐溢聪
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryStudentDto extends BaseQuery<Student> {
}
