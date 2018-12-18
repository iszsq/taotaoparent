package com.taotao.content.service.impl;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    @Override
    public TaotaoResult createContentCategory(Long parentId, String name) {
        //构建一个对象，补全其他属性
        TbContentCategory category = new TbContentCategory();
        category.setCreated(new Date());
        category.setIsParent(false);//新增的节点都是页节点
        category.setName(name);
        category.setParentId(parentId);
        category.setSortOrder(1);
        category.setUpdated(category.getCreated());
        //插入表数据
        mapper.insertSelective(category);
        //返回taotaoresult 包含内容分类的id  需要主键返回

        //如果parentId是叶节点，那么更新为父节点
        TbContentCategory parent = mapper.selectByPrimaryKey(parentId);
        if(!parent.getIsParent()){
            //如果不是父节点，更新
            parent.setIsParent(true);
            mapper.updateByPrimaryKeySelective(parent);
        }
        return TaotaoResult.ok(category);
    }
}
