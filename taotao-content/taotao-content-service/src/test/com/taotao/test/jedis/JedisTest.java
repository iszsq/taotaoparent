package com.taotao.test.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author zsq
 * @date 2018/12/19 - 1:24
 */
public class JedisTest {

    //测试
    @Test
    public void testJedis(){
        //创建Jedis对象
        Jedis jedis = new Jedis("192.168.6.131",6379);
        //直接操作redis
        jedis.set("zsq","周是啊");
        System.out.println(jedis.get("zsq"));
        //关闭jedis
        jedis.close();
    }

    /**
     * 使用连接池
     */
    @Test
    public void testJedisPool(){
        //创建jedispool对象
        JedisPool pool = new JedisPool("192.168.6.131",6379);
        Jedis jedis = pool.getResource();
        System.out.println(jedis.get("zsq"));
        //关闭redis连接（释放连接回到池子中）
        jedis.close();
        //关闭连接池，应用系统关闭后
        pool.close();
    }

}
