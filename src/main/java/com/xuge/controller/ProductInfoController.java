package com.xuge.controller;

import com.github.pagehelper.PageInfo;
import com.xuge.pojo.ProductInfo;
import com.xuge.pojo.vo.ProductInfoVo;
import com.xuge.service.ProductInfoService;
import com.xuge.utils.FileNameUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * author: yjx
 * Date :2022/3/1114:55
 **/
@Controller
@RequestMapping("/prod")
public class ProductInfoController {
  public static final int PAGE_SIZE = 5;
  String fileName = "";
  @Autowired
  private ProductInfoService productInfoService;

  //查询全部商品
  @RequestMapping("/getAll.action")
  public String getAll(Model model) {
    List<ProductInfo> list = productInfoService.getAll();
    model.addAttribute("list", list);
    return "product";
  }

  //显示第一页的五个商品
  @RequestMapping("/split.action")
  public String split(Model model, HttpServletRequest req) {
    PageInfo info = null;
    ProductInfoVo provo = (ProductInfoVo) req.getSession().getAttribute("provo");
    if (provo != null) {//带条件的查询
      info = productInfoService.splitPageVo((ProductInfoVo) provo, PAGE_SIZE);
      req.getSession().removeAttribute("provo");
    } else {//不带条件的
      info = productInfoService.splitPage(1, PAGE_SIZE);
    }
    model.addAttribute("info", info);
    return "product";
  }

  @ResponseBody
  @RequestMapping("/ajaxsplit")
  //ajax翻页处理
  public void ajaxSplit(ProductInfoVo vo, HttpSession session) {
    //取得当前配置参数页面的数据
    PageInfo info = productInfoService.splitPageVo(vo, PAGE_SIZE);
    session.setAttribute("info", info);
  }

  //ajaxImg.action 文件异步上传
  @ResponseBody
  @RequestMapping("/ajaxImg.action")
  public Object ajaxImg(MultipartFile pimage, HttpServletRequest request) {
    //1.提取生成文件名 UUID+上传图片的后缀 .jpg
    fileName = FileNameUtil.getUUIDFileName() + FileNameUtil.getFileType(pimage.getOriginalFilename());

    //2.得到项目中图片存储的路径
    String path = request.getServletContext().getRealPath("/image_big");
    //注意，此为target/目录下的资源
    //转存-->path: D:\Java架构师资料\Study\项目\MMSM\image_big  File.separator: \  fileName:  fehuwhjf.jpg
    try {
      pimage.transferTo(new File(path + File.separator + fileName));
    } catch (IOException e) {
      e.printStackTrace();
    }
    //返回客户端json对象，封装图片的路经，为在页面中立即回显
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("imgurl", fileName);
    return jsonObject.toString();
  }

  //新增商品
  @RequestMapping("/save.action")
  public String save(ProductInfo productInfo, HttpServletRequest request) {
    productInfo.setpImage(fileName);
    productInfo.setpDate(new Date());
    //info对象中有表单中的五个数据，有异步上来的图片名称和数据，有上传时间和数据
    int num = -1;
    try {
      num = productInfoService.save(productInfo);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    if (num > 0) {
      request.setAttribute("msg", "添加商品成功!");
    } else {
      request.setAttribute("msg", "添加商品失败!");
    }
    //清空fileName变量的热熔，为了下次增加或修改的异步ajax处理
    fileName = "";
    //增加成功后要重新返回数据库，重定向到分页请求上
    return "forward:/prod/split.action";
  }

  @RequestMapping("/getOne.action")
  public String getOnePro(Integer pid, Integer page, Model model, ProductInfoVo vo, HttpSession session) {
    //1.根据pid号查询出当前商品
    ProductInfo productInfo = productInfoService.getProductById(pid);
    //2.放入model
    model.addAttribute("prod", productInfo);
    //3.便于数据的回显
    session.setAttribute("provo", vo);
    return "update";
  }

  @RequestMapping("/update.action")
  public String update(ProductInfo productInfo, Model model) {
    //1.由于ajax的异步上传，则fileName是有值得，则可以把新值注入实体类，
    // 如果没有使用过，则fileName为空，用隐藏域传过来的pimage就好
    if (!fileName.equals("")) {//有过异步上传
      //2.设置图片名
      productInfo.setpImage(fileName);
    }
    //设置时间
    productInfo.setpDate(new Date());
    //3.完成更新处理
    int num = -1;
    try {
      num = productInfoService.update(productInfo);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    if (num > 0) {//更新成功
      model.addAttribute("msg", "更新成功!");
    } else {//更新失败
      model.addAttribute("msg", "更新失败!");
    }
    //处理完更新后，fileName可能有数据，而下一次又把这个变量作为判断的依据，需要2置空处理
    fileName = "";
    return "forward:/prod/split.action";
  }

  //单个删除实现
  @RequestMapping("/delete.action")
  public String delete(Integer pid, Model model, ProductInfoVo vo, HttpServletRequest req) {
    int num = -1;
    try {
      num = productInfoService.delete(pid);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    if (num > 0) {
      model.addAttribute("msg", "删除成功");
      req.getSession().setAttribute("deleteProvo", vo);
    } else {
      model.addAttribute("msg", "删除失败");
    }
    //删除成功后跳到分页显示
    return "forward:/prod/deleteAjaxSplit.action";

  }

  @RequestMapping(value = "/deleteAjaxSplit.action", produces = "text/html;charset=UTF-8")
  @ResponseBody
  public Object deleteAjaxSplit(HttpServletRequest req) {
    PageInfo info = null;
    ProductInfoVo provo = (ProductInfoVo) req.getSession().getAttribute("deleteProvo");
    if (provo != null) {//带条件的查询
      info = productInfoService.splitPageVo( provo, PAGE_SIZE);
      req.getSession().removeAttribute("deleteProvo");
    } else {//不带条件的
      info = productInfoService.splitPage(1, PAGE_SIZE);
    }
    //1.得到第一页数据
    req.getSession().setAttribute("info", info);
    return req.getAttribute("msg");
  }

  //批量删除商品功能实现
  @RequestMapping("/deleteBatch.action")
  //pids="1,4,5"
  public String deleteBatch(String ids, Model model) {
    //将上传的字符串转为数组
    String[] pids = ids.split(",");
    int num = -1;
    try {
      num = productInfoService.deleteBatch(pids);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    if (num > 0) {//批处理删除成功
      model.addAttribute("msg", "批量删除成功!");
    } else {//批处理删除失败
      model.addAttribute("msg", "批量删除失败!");

    }
    return "forward:/prod/deleteAjaxSplit.action";
  }

  //多条件查询功能实现
  @ResponseBody//用户接受异步ajax请求
  @RequestMapping("/condition.action")
  public void condition(ProductInfoVo vo, HttpSession session) {
    List<ProductInfo> list = productInfoService.selectCondition(vo);
    session.setAttribute("list", list);
  }

}
