package com.ping.Dao.Impl;

import com.ping.Dao.ProvinceDao;
import com.ping.Utils.JDBCUtils;
import com.ping.domain.Province;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ProvinceDaoImpl implements ProvinceDao {
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Province> findAll() {
        String sql="select * from province";
       List<Province> provinces= jdbcTemplate.query(sql,new BeanPropertyRowMapper<Province>(Province.class));
        return provinces;
    }
}
