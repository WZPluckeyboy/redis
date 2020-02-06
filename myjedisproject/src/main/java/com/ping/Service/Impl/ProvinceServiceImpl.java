package com.ping.Service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ping.Dao.Impl.ProvinceDaoImpl;
import com.ping.Dao.ProvinceDao;
import com.ping.Service.ProvinceService;
import com.ping.Utils.JedispoolUtils;
import com.ping.domain.Province;
import redis.clients.jedis.Jedis;

import java.util.List;

public class ProvinceServiceImpl implements ProvinceService {
    private ProvinceDao provinceDao=new ProvinceDaoImpl();
    @Override
    public List<Province> findAll() {

        return provinceDao.findAll();
    }

    @Override
    public String findAllJson() {
     Jedis jedis=   JedispoolUtils.getJedis();
      String procice=jedis.get("provice");
      if(procice==null || procice.length()==0){
          System.out.println("查询数据库");
       List<Province>  ps= provinceDao.findAll();
          ObjectMapper mapper=new ObjectMapper();
          try {
           procice= mapper.writeValueAsString(ps);
          } catch (JsonProcessingException e) {
              e.printStackTrace();
          }
        jedis.set("provice",procice);
          jedis.close();
      }else{
          System.out.println("查询缓存");
          return procice;
      }
        return procice;
    }
}
