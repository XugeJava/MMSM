package com.xuge.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xuge.mapper.ProductInfoMapper;
import com.xuge.pojo.ProductInfo;
import com.xuge.pojo.ProductInfoExample;
import com.xuge.pojo.vo.ProductInfoVo;
import com.xuge.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author: yjx
 * Date :2022/3/1114:52
 **/
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
  @Autowired
  private ProductInfoMapper productInfoMapper;

  @Override
  public List<ProductInfo> getAll() {
   return productInfoMapper.selectByExample(null);
  }

  //select * from product_info limit 10,5(起始记录数=(当前页数-1)*size,每页取的条数)
  @Override
  public PageInfo splitPage(Integer pageNum, Integer pageSize) {
    //1.分页插件使用PageHelper设置
    PageHelper.startPage(pageNum, pageSize);
    //2.进行pageInfo的数据封装
    //2.1创建条件对象
    ProductInfoExample example = new ProductInfoExample();
    //2.2追加条件
    //2.2.1设置排序，按降序排序
    example.setOrderByClause("p_id desc");
    //2.2.2设置完排序后，取集合，切记，一定要在分页之前取  PageHelper.startPage(pageNum, pageSize);
    List<ProductInfo> list = productInfoMapper.selectByExample(example);
    //3.封装PageInfo对象
    PageInfo<ProductInfo> pageInfo = new PageInfo<>(list);
    return pageInfo;
  }

  @Override
  public  int  save(ProductInfo productInfo) {
    return productInfoMapper.insert(productInfo);
  }

  @Override
  public ProductInfo getProductById(Integer id) {
    return productInfoMapper.selectByPrimaryKey(id);
  }

  @Override
  public int update(ProductInfo productInfo) {
    return productInfoMapper.updateByPrimaryKey(productInfo);
  }

  @Override
  public int delete(Integer id) {
    return productInfoMapper.deleteByPrimaryKey(id);
  }

  @Override
  public int deleteBatch(String []ids) {
    return productInfoMapper.deleteBatch(ids);
  }

  @Override
  public List<ProductInfo> selectCondition(ProductInfoVo vo) {
    return productInfoMapper.selectCondition(vo);
  }

  @Override
  public PageInfo<ProductInfo> splitPageVo(ProductInfoVo vo, Integer pageSize) {
    //1.分页插件使用PageHelper设置
    PageHelper.startPage(vo.getPageNum(), pageSize);
    //2.通过条件查询
    List<ProductInfo> list = productInfoMapper.selectCondition(vo);
    return new PageInfo<>(list);
  }


}
