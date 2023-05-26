package com.atguigu.common.exception;

import lombok.Data;

@Data
public class GuiGuException extends RuntimeException{

    private Integer code;
    private String msg;

    public GuiGuException(Integer code,String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "GuiGuException{" +
               "code=" + code +
               ", msg='" + msg + '\'' +
               '}';
    }
}
