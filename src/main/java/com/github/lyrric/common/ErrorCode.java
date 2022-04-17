package com.github.lyrric.common;

public class ErrorCode {

    private final Integer errCode;

    private final String errMsg;

    public ErrorCode(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public Integer getErrCode() {
        return this.errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public static boolean isSuccessFully(int errCode) {
        return errCode >= 10000 && errCode < 20000;
    }

}
