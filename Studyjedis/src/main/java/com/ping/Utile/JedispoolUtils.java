package com.ping.Utile;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//工具类
public class JedispoolUtils {
    private  static JedisPool jedisPool;
    static {
        //1.读取配置文件1
      InputStream is= JedispoolUtils.class.getClassLoader().getResourceAsStream("jedis.properties");
      //2.创建Properties对象
        Properties p=new Properties();
        try {
            p.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JedisPoolConfig config=new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(p.getProperty("maxTotal")));
        config.setMaxIdle(Integer.parseInt(p.getProperty("maxIdle")));
        jedisPool=new JedisPool(config,p.getProperty("host"),Integer.parseInt(p.getProperty("port")));

    }
    //获取连接方法
    public  static Jedis getJedis(){
        return  jedisPool.getResource();
    }
}
