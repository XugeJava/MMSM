package com.xuge.service.Impl;

import com.xuge.mapper.AdminMapper;
import com.xuge.pojo.Admin;
import com.xuge.pojo.AdminExample;
import com.xuge.service.AdminService;
import com.xuge.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author: yjx
 * Date :2022/3/1113:52
 **/
@Service
public class AdminServiceImpl implements AdminService {
  //在业务逻辑层中，一定有数据访问层的对象
  @Autowired
  private AdminMapper adminMapper;
  @Override
  public Admin login(String name, String pwd) {
    //1.根据传入的用户名到db中查询相应用户对象
    //1.1如果有条件一定要创建AdminExample对象,用来分封装条件
    //2.如果查询到用户对象，再进行密码的匹配
    AdminExample example = new AdminExample();
    /**
     *1.2如何添加条件
     * select * from admin where a_name=?
     */
    example.createCriteria().andANameEqualTo(name);

    List<Admin> admins = adminMapper.selectByExample(example);
    if(admins.size()>0){//查到了
      Admin admin = admins.get(0);
      //如果查询到用户对象，进行密码的匹配，注意密码是密文
      /**
       * admin.getApass==?c984aed014aec7623a54f0591da07a85fd4b762d
       * pwd--000000
       *再匹配时要先将密码加密，再匹配
       */
      if(MD5Util.getMD5(pwd).equals(admin.getaPass())){//如果相等，表示密码一致，登录成功
        return admin;
      }
    }
    return null;
  }
}
