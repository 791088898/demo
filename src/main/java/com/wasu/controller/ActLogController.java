package com.wasu.controller;

import com.wasu.entity.ActLogPo;
import com.wasu.service.ActLogService;
import com.wasu.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: cs
 * @Date: 2019/6/13 22:15
 */
@Api(tags = "埋点接口",description = "ActLogController")
@RestController
@RequestMapping("actlog")
public class ActLogController {

    @Autowired
    ActLogService actLogService;

    @ApiOperation(value = "查询接口",notes = "查询埋点信息",response = ActLogPo.class)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200,message = "成功"),
                    @ApiResponse(code = 400,message = "拒接请求"),
                    @ApiResponse(code = 401,message = "HTTP 错误 401.1 - 未经授权：访问由于凭据无效被拒绝"),
                    @ApiResponse(code = 403,message = "HTTP 错误 401.3 - 未经授权：访问由于 ACL 对所请求资源的设置被拒绝。"),
                    @ApiResponse(code = 404,message = "请求的网页不存在"
            )
    })
    @GetMapping(value = "selectAll")
    public R selectAll(){
        List<ActLogPo> list = actLogService.selectAll();
        return R.ok().put("list",list);
    }

}
