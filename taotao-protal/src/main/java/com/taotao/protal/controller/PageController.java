package com.taotao.protal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zsq
 * @date 2018/12/12 - 22:12
 */
@Controller
public class PageController {

    @RequestMapping("/index")
    public String showIndex(){
        return "index";
    }

}
