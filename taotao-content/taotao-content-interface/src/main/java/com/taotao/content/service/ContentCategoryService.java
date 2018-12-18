package com.taotao.content.service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;

import java.util.List;

/**
 * @author zsq
 * @date 2018/12/15 - 21:20
 */
public interface ContentCategoryService {
    //通过节点的id查询该节点的列表
    List<EasyUITreeNode>  getContentcategoryList(Long id);
    //添加内容分类
    /**
     *
     * @param parentId 父节点id
     * @param name 新增节点名称
     * @return
     */
    TaotaoResult createContentCategory(Long parentId, String name);
}
