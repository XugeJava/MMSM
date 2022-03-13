package com.xuge.mapper;

import com.xuge.pojo.ProductInfo;
import com.xuge.pojo.ProductInfoExample;
import java.util.List;

import com.xuge.pojo.vo.ProductInfoVo;
import org.apache.ibatis.annotations.Param;

public interface ProductInfoMapper {
    int countByExample(ProductInfoExample example);

    int deleteByExample(ProductInfoExample example);

    int deleteByPrimaryKey(Integer pId);

    int insert(ProductInfo record);

    int insertSelective(ProductInfo record);

    List<ProductInfo> selectByExample(ProductInfoExample example);

    ProductInfo selectByPrimaryKey(Integer pId);

    int updateByExampleSelective(@Param("record") ProductInfo record, @Param("example") ProductInfoExample example);

    int updateByExample(@Param("record") ProductInfo record, @Param("example") ProductInfoExample example);

    int updateByPrimaryKeySelective(ProductInfo record);

    int updateByPrimaryKey(ProductInfo record);


    //自定义方法
    //1.批量删除商品的功能
    int deleteBatch(String []ids);


    //多条件查询开发
    List<ProductInfo>  selectCondition(ProductInfoVo vo);


}