package com.ping.Service;

import com.ping.domain.Province;

import java.util.List;

public interface ProvinceService {
    public List<Province> findAll();
    public  String findAllJson();
}
