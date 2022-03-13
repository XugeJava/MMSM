package com.xuge.listener;

import com.xuge.pojo.ProductType;
import com.xuge.service.Impl.ProductTypeServiceImpl;
import com.xuge.service.ProductTypeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * author: yjx
 * Date :2022/3/1119:03
 **/
@WebListener
public class ProductTypeListener implements ServletContextListener {
  @Override
  public void contextInitialized(ServletContextEvent servletContextEvent) {
    //手动从Spring容器中取出productTypeServiceImpl对象
    ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext_*.xml");
    ProductTypeService productTypeService= context.getBean("ProductTypeServiceImpl", ProductTypeService.class);
    List<ProductType> typeList = productTypeService.getAll();
    //放入全局应用域中,供新增页面，修改页面，前台的查询功能提供全部商品类集合
    servletContextEvent.getServletContext().setAttribute("ptlist", typeList);

  }

  @Override
  public void contextDestroyed(ServletContextEvent servletContextEvent) {

  }
}
