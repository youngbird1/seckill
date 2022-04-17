package com.github.lyrric.common.web;

import com.github.lyrric.common.ErrCodeConstant;
import lombok.Data;

@Data
public class Response<T> {

    private Integer errCode;

    private String errMsg;

    private T data;

    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<>();
        response.setErrCode(ErrCodeConstant.SUCCESS.getErrCode());
        response.setErrMsg(ErrCodeConstant.SUCCESS.getErrMsg());
        response.setData(data);
        return response;
    }

    public static <T> Response<T> fail() {
        Response<T> response = new Response<>();
        response.setErrCode(ErrCodeConstant.FAIL.getErrCode());
        response.setErrMsg(ErrCodeConstant.FAIL.getErrMsg());
        response.setData(null);
        return response;
    }
}
