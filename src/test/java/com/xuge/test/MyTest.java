package com.xuge.test;

import com.xuge.utils.MD5Util;
import org.junit.Test;

/**
 * author: yjx
 * Date :2022/3/1113:49
 **/
public class MyTest {
  @Test
  public void testMD5(){
    String mo= MD5Util.getMD5("123");
    System.out.println(mo);
  }
}
