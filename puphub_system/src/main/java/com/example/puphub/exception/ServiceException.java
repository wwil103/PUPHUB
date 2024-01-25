package com.example.puphub.exception;

import lombok.Getter;
@Getter
public class ServiceException extends RuntimeException{
    private String code;
    /**
     * 自定义错误类型
     * @param code 自定义的错误码
     * @param msg 自定义的错误提示
     */
    public ServiceException(String code, String msg){
        super(msg);
        this.code = code;
    }
}
