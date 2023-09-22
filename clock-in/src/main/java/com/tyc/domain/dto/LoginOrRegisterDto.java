package com.tyc.domain.dto;

import lombok.Data;

/**
 * @author 唐溢聪
 */
@Data
public class LoginOrRegisterDto {

    private String username;

    private String password;

    private Byte type;
}
