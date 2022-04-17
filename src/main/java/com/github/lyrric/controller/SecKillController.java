package com.github.lyrric.controller;

import com.github.lyrric.common.web.Response;
import com.github.lyrric.conf.Config;
import com.github.lyrric.model.Area;
import com.github.lyrric.model.VaccineList;
import com.github.lyrric.model.dto.CookieReq;
import com.github.lyrric.service.SecKillService;
import com.github.lyrric.util.ParseUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/secKill")
public class SecKillController {

    @Resource
    private SecKillService secKillService;

    // 设置cookie
    @PostMapping("/setCookie")
    public Response<String> setCookie(@RequestBody CookieReq cookieReq) {
        Config.setTk(cookieReq.getTk());
        Config.setCookie(cookieReq.getCookie());
        return Response.success(null);
    }

    // 获取地区代码
    @GetMapping("/getAreas")
    public Response<List<Area>> getAreas() {
        return Response.success(ParseUtil.getAreas());
    }

    // 刷新疫苗列表
    @GetMapping("/getVaccines")
    public Response<List<VaccineList>> getVaccines(@RequestParam String regionCode) throws IOException {
        return Response.success(secKillService.getVaccines(regionCode));
    }

    // 选择成员
    // 提交秒杀
}
