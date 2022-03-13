package com.xuge.controller;

import com.xuge.pojo.Admin;
import com.xuge.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * author: yjx
 * Date :2022/3/1114:12
 **/
@Controller
@RequestMapping("/admin")
public class AdminController {
  //界面层中，一定有业务逻辑层的对象
  @Autowired
  private AdminService adminService;
  //1.实现登录判断，并完成相应的跳转
  @RequestMapping("/login.action")
  public String login(Model model,String name, String pwd){
    Admin admin = adminService.login(name, pwd);
    if(admin!=null){
      //2.添加数据
      model.addAttribute("admin",admin);
      return "main";

    }else{
      //登录失败
      model.addAttribute("errmsg","用户名和密码不正确");
      return "login";
    }


  }

}
