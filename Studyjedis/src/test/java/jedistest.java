import com.ping.Utile.JedispoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;
import java.util.Set;

public class jedistest {
    //字符串数据
    @Test
    public void test1(){
        //1.获取连接
        Jedis jedis=new Jedis("localhost",6379);
        //2.操作
        jedis.set("username","zhnagsan");
        System.out.println(jedis.get("username"));
        jedis.setex("active",20,"hehe");//存入后20自动删除
        //3.关闭连接
        jedis.close();

    }
    //哈希
    @Test
    public void test2(){
        //1.获取连接
        Jedis jedis=new Jedis("localhost",6379);
        //2.操作
       jedis.hset("myhash","username","lisi");
       jedis.hset("myhash","age","23");
       String s= jedis.hget("myhash","username");
        System.out.println(s);
      Map<String,String> map=jedis.hgetAll("myhash");
      //遍历
        Set<String> set=map.keySet();
        for(String key:set){
         String value=  map.get(key);
            System.out.println(key+" "+value);
        }
        //3.关闭连接
        jedis.close();

    }
    // list
    @Test
    public void test3(){
        //1.获取连接
        Jedis jedis=new Jedis("localhost",6379);
        //2.操作
       jedis.lpush("mylist","a");
       jedis.lpush("mylist","b");
        jedis.rpush("mylist","a");
        jedis.rpush("mylist","b");
        jedis.rpop("mylist");
        jedis.lpop("mylist");
        //3.关闭连接
        jedis.close();

    }
    //set
    @Test
    public void test4(){
        //1. 获取连接
        Jedis jedis = new Jedis();//如果使用空参构造，默认值 "localhost",6379端口 /
        // /2. 操作
        //set 存储
        jedis.sadd("myset","java","php","c++");
        // set 获取
        Set<String> myset = jedis.smembers("myset");
        System.out.println(myset);
        //3.关闭连接
        jedis.close();
    }
    //sorteSet
    @Test
    public void test5(){
        //1. 获取连接
        Jedis jedis = new Jedis();//如果使用空参构造，默认值 "localhost",6379端口
        // 2. 操作
        // sortedset 存储
        jedis.zadd("mysortedset",3,"亚瑟");
        jedis.zadd("mysortedset",30,"后裔");
        jedis.zadd("mysortedset",55,"孙悟空");
        // sortedset 获取
        Set<String> mysortedset = jedis.zrange("mysortedset", 0, -1);
        System.out.println(mysortedset);
        // 3. 关闭连接
        jedis.close();

    }
    //连接池的使用
    @Test
    public void test6(){
        //1. 创建连接池对象
        JedisPool jedisPool = new JedisPool();//如果使用空参构造，默认值 "localhost",6379端口
        // 2. 获取连接
        Jedis jedis= jedisPool.getResource();
        //3.使用
        jedis.zadd("mysortedset",3,"亚瑟");
        jedis.zadd("mysortedset",30,"后裔");
        jedis.zadd("mysortedset",55,"孙悟空");
        // sortedset 获取
        Set<String> mysortedset = jedis.zrange("mysortedset", 0, -1);
        System.out.println(mysortedset);
        // 4. 关闭连接
        jedis.close();

    }
    //连接池工具类使用
    @Test
    public void test7(){
    Jedis jedis=JedispoolUtils.getJedis();
        //3.使用
        jedis.zadd("mysortedset",3,"亚瑟");
        jedis.zadd("mysortedset",30,"后裔");
        jedis.zadd("mysortedset",55,"孙悟空");
        // sortedset 获取
        Set<String> mysortedset = jedis.zrange("mysortedset", 0, -1);
        System.out.println(mysortedset);
        // 4. 关闭连接
        jedis.close();
    }

}

