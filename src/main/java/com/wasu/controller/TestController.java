package com.wasu.controller;

import com.wasu.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: cs
 * @Date: 2019/6/13 10:09
 */
@Api(value = "/test",tags = "test",description = "埋点日志")
@RestController
@RequestMapping("test")
public class TestController {

    @ApiOperation(value = "测试")
    @GetMapping(value = "rest")
    public R rest(){
        //目前不做任何处理，仅测试
        return R.ok();
    }
}
