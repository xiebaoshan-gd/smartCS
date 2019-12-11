package com.example.demo.mapper;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Asset;
import tk.mybatis.mapper.common.Mapper;

/*
相当于三层架构的DAO
*/
@Repository
public interface AssetMapper extends Mapper<Asset> {

}