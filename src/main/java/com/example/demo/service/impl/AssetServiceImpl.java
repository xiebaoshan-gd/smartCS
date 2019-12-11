package com.example.demo.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Asset;
import com.example.demo.mapper.AssetMapper;
import com.example.demo.service.IAssetService;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;



@Service
@Transactional(readOnly=true)
public class AssetServiceImpl extends BaseServiceImpl<Asset> implements IAssetService {
	@Autowired
	private AssetMapper assetMapper;
	
	@Override
	public Mapper<Asset> getMapper() {
		return this.assetMapper;
	}

	@Override
	public boolean checkNumAndRfid(String num, String rfid) {
		Example example = new Example(Asset.class);
		Criteria c = example.createCriteria();
		c.orEqualTo("num", num);
		c.orEqualTo("rfid", rfid);
		List<Asset> list = assetMapper.selectByExample(example);
		return list.size() == 0; 
	}
	
	@Override
	public boolean checkNumAndRfid(int id, String num, String rfid) {
		Example example = new Example(Asset.class);
		Criteria c = example.createCriteria();
		Criteria c2 = example.createCriteria();
		c.andNotEqualTo("id", id);
		
		c2.orEqualTo("num", num);
		c2.orEqualTo("rfid", rfid);
		
		example.and(c2);
		
		List<Asset> list = assetMapper.selectByExample(example);
		return list.size() == 0; 
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteBatch(String[] ids) {
		for (String id : ids) {
			delete(Integer.parseInt(id));
		}
	}

}
