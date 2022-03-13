package com.xuge.service;

import com.xuge.pojo.ProductType;

import java.util.List;

/**
 * author: yjx
 * Date :2022/3/1116:59
 **/
public interface ProductTypeService {
  //查询全部商品类别
  List<ProductType> getAll();
}
