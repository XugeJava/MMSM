package com.xuge.service;

import com.xuge.pojo.Admin;

/**
 * author: yjx
 * Date :2022/3/1113:51
 **/
public interface AdminService {
  //完成登录判断
  Admin login(String name,String pwd);

}
