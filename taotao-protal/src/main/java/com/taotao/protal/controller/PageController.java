package com.taotao.protal.controller;

import com.taotao.common.utils.JsonUtils;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;
import com.taotao.protal.pojo.Ad1Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zsq
 * @date 2018/12/12 - 22:12
 */
@Controller
public class PageController {

    @Autowired
    private ContentService service;
    //大广告id
    @Value("${AD1_CATEGORY_ID}")
    private Long AD1_CATEGORY_ID;

    @Value("${AD1_HEIGHT_B}")
    private String AD1_HEIGHT_B;
    @Value("${AD1_HEIGHT_B}")
    private String AD1_HEIGHT;
    @Value("${AD1_WIDTH}")
    private String AD1_WIDTH;
    @Value("${AD1_WIDTH_B}")
    private String AD1_WIDTH_B;

    @RequestMapping("/index")
    public String showIndex(Model model){
        //查询列表，转换成自定义pojo
        List<TbContent> list = service.getContentListByCatId(AD1_CATEGORY_ID);
        List<Ad1Node> nodes = new ArrayList<>();
        for(TbContent t : list){
            Ad1Node node = new Ad1Node();
            node.setAlt(t.getSubTitle());
            node.setHeight(AD1_HEIGHT);
            node.setHeightB(AD1_HEIGHT_B);
            node.setHref(t.getUrl());
            node.setSrc(t.getPic());
            node.setSrcB(t.getPic2());
            node.setWidth(AD1_WIDTH);
            node.setWidthB(AD1_WIDTH_B);
            nodes.add(node);
        }
        model.addAttribute("ad1", JsonUtils.objectToJson(nodes));
        return "index";
    }

}
