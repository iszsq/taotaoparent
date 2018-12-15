package com.taotao.content.service.impl;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.content.service.ContentCategoryService;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zsq
 * @date 2018/12/15 - 21:24
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
    @Autowired
    private TbContentCategoryMapper mapper;

    @Override
    public List<EasyUITreeNode> getContentcategoryList(Long id) {
        //注入mapper
        //创建example
        TbContentCategoryExample example = new TbContentCategoryExample();
        //设置条件
        Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(id);    //select * from tb.. where parent_id = ?
        //执行查询
        List<TbContentCategory> list = mapper.selectByExample(example);
        //转
        List<EasyUITreeNode> treeList = new ArrayList<>();
        for(TbContentCategory t : list){
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(t.getId());
            node.setText(t.getName());  //分类名称
            node.setState(t.getIsParent()?"closed":"open");
            treeList.add(node);
        }
        return treeList;
    }
}
