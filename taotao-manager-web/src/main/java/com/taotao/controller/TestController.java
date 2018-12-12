package com.taotao.controller;

import com.taotao.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**测试使用的controller，查询当前时间
 * @author zsq
 * @date 2018/12/11 - 22:32
 */
@Controller
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/test/queryNow")
    @ResponseBody
    public String queryNow(){
        //引入服务
        //注入服务
        //调用服务方法
        return testService.queryNow();
    }

}
