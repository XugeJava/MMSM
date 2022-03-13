package com.xuge.service;

import com.github.pagehelper.PageInfo;
import com.xuge.pojo.ProductInfo;
import com.xuge.pojo.vo.ProductInfoVo;

import java.util.List;

/**
 * author: yjx
 * Date :2022/3/1114:51
 **/
public interface ProductInfoService {
  //1.显示全部商品不分页
  List<ProductInfo> getAll();

  //2.分页功能
  PageInfo splitPage(Integer pageNum,Integer pageSize);

  //3.添加商品
  int save(ProductInfo productInfo);

  //4.按主键查询商品
  ProductInfo getProductById(Integer id);

  //5.更新商品--按主键更新
  int update(ProductInfo productInfo);

  //6.删除商品--通过主键
  int delete(Integer id);

  //7.批量删除
  int deleteBatch(String []ids);

  //8.根据多条件查询
  List<ProductInfo> selectCondition(ProductInfoVo vo);

  //9.多条件查询分页
  PageInfo splitPageVo(ProductInfoVo vo,Integer pageSize);
}
