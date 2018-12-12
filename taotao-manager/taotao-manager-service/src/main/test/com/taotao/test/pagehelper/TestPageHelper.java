package com.taotao.test.pagehelper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author zsq
 * @date 2018/12/12 - 11:11
 */
public class TestPageHelper {

    @Test
    public void testhelper(){

        //2.初始化spring容器
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
        //3.获取mapper的代理对象
        TbItemMapper itemMapper = context.getBean(TbItemMapper.class);
        //1.设置分页信息
        PageHelper.startPage(1,3);//紧跟着的第一个查询才被分页
        TbItemExample example = new TbItemExample();//设置查询条件用
        //4.调用mapper的方法查询数据
        List<TbItem> list = itemMapper.selectByExample(example);//select * from tb_item;
//取分页信息
        PageInfo<TbItem> info = new PageInfo<>(list);
        for (TbItem b : list) {
            System.out.println(b);
        }
        System.out.println(info);
    }

}
