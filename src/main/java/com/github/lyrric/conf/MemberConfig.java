package com.github.lyrric.conf;

import lombok.Data;

@Data

public class MemberConfig {

    private String tk = "";

    /**
     * 接种成员ID
     */
    private Integer memberId;
    /**
     * 接种成员身份证号码
     */
    private String idCard;
    /**
     * 接种成员姓名
     */
    private String memberName;
    /**
     * 疫苗id
     */
    private Integer vaccineId;
    /**
     * 开始时间
     */
    private long startDate;
    /**
     * 抢购是否成功
     * false表示疫苗已抢光
     */
    private Boolean success = null;
    /**
     * 加密参数st
     */
    private String st;
}
