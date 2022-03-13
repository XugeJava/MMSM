package com.xuge.service.Impl;

import com.xuge.mapper.ProductTypeMapper;
import com.xuge.pojo.ProductType;
import com.xuge.service.ProductInfoService;
import com.xuge.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author: yjx
 * Date :2022/3/1117:01
 **/
@Service("ProductTypeServiceImpl")
public class ProductTypeServiceImpl implements ProductTypeService {
  @Autowired
  private ProductTypeMapper productTypeMapper;
  @Override
  public List<ProductType> getAll() {
    return productTypeMapper.selectByExample(null);
  }
}
