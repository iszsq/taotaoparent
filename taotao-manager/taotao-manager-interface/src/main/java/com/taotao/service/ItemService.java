package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;

/**
 * 商品相关的处理方法
 * @author zsq
 * @date 2018/12/12 - 12:00
 */
public interface ItemService {

    public EasyUIDataGridResult getItemList(Integer page, Integer rows);

}
