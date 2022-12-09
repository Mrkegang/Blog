package com.kgstudy.exception;

import com.kgstudy.enums.AppHttpCodeEnum;

/**
 * @author kg
 * @version 1.0
 * @description
 * @date 2022/12/9 19:01
 */
public class SystemException extends RuntimeException {

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }
}
