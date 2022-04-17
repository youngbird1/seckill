package com.github.lyrric.common;

public interface ErrCodeConstant {
    // 前端响应错误码
    ErrorCode SUCCESS = new ErrorCode(10000, "成功");
    ErrorCode FAIL = new ErrorCode(20000, "失败");

}
