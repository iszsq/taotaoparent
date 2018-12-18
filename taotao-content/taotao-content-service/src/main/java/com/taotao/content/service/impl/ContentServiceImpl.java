package com.taotao.content.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.content.jedis.JedisClient;
import com.taotao.content.service.ContentService;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zsq
 * @date 2018/12/18 - 22:09
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper mapper;
    @Autowired
    private JedisClient jedisClient;
    //hset分类key
    private static String CONTENT_KEY = "CONTENT_KEY";
    private static Logger logger = Logger.getLogger(ContentServiceImpl.class);

    @Override
    public TaotaoResult saveContent(TbContent content) {
        content.setCreated(new Date());
        content.setUpdated(content.getCreated());
        mapper.insertSelective(content);

        try {
            //添加时清除此分类下的缓存
            jedisClient.hdel(CONTENT_KEY, content.getCategoryId()+"");
            logger.debug("清空缓存");
        }catch (Exception e){
            e.printStackTrace();
        }

        return TaotaoResult.ok();
    }

    @Override
    public List<TbContent> getContentListByCatId(Long categoryId) {
        try {
            //添加缓存不能影响正常的业务逻辑
            //判断redis中是否有数据
            String listJson = jedisClient.hget(CONTENT_KEY, categoryId + "");
            if(StringUtils.isNotBlank(listJson)){
                //这里有缓存
                logger.debug("有缓存，不用取数据库取。");
                return JsonUtils.jsonToList(listJson, TbContent.class);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        TbContentExample example = new TbContentExample();
        example.createCriteria().andCategoryIdEqualTo(categoryId);//select * from tbcontent where categoryId = ?
        List<TbContent> list = mapper.selectByExample(example);

        try {
            //将list添加到redis数据库中
            jedisClient.hset(CONTENT_KEY,categoryId+"", JsonUtils.objectToJson(list));
        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }
}
