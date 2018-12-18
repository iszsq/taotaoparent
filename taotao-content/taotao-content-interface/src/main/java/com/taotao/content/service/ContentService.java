package com.taotao.content.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

import java.util.List;

/**
 * 内容处理的接口
 * @author zsq
 * @date 2018/12/18 - 22:07
 */
public interface ContentService {

    /**
     * 插入内容表
     * @param content
     * @return
     */
    TaotaoResult saveContent(TbContent content);

    /**
     * 根据内容分类的id查询旗下的列表
     * @param categoryId
     * @return
     */
    List<TbContent> getContentListByCatId(Long categoryId);
}
