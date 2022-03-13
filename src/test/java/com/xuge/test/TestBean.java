package com.xuge.test;

import com.xuge.controller.AdminController;
import com.xuge.controller.ProductInfoController;
import com.xuge.mapper.ProductInfoMapper;
import com.xuge.pojo.ProductInfo;
import com.xuge.pojo.vo.ProductInfoVo;
import com.xuge.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * author: yjx
 * Date :2022/3/1115:22
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext_dao.xml","classpath:applicationContext_service.xml"})
public class TestBean {
  @Autowired
  private ProductInfoMapper mapper;
  @Test
  public void testCondition(){
    ProductInfoVo productInfoVo = new ProductInfoVo();
    productInfoVo.setpName("4");
    productInfoVo.setTypeId(1);
    productInfoVo.setMinPrice(1000);
    productInfoVo.setMaxPrice(4000);
    List<ProductInfo> list = mapper.selectCondition(productInfoVo );
    list.forEach(productInfo -> System.out.println(productInfo));
  }
}
