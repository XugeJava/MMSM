package com.xuge.pojo.vo;

/**
 * author: yjx
 * Date :2022/3/1212:28
 **/
public class ProductInfoVo {
  //商品名称
  private String pName;
  //商品类型
  private Integer typeId;
  //最低价格
  private Integer minPrice;
  //最高价格
  private Integer maxPrice;
  //页码
  private Integer pageNum=1;

  public ProductInfoVo() {
  }

  public ProductInfoVo(String pName, Integer typeId, Integer minPrice, Integer maxPrice, Integer pageNum) {
    this.pName = pName;
    this.typeId = typeId;
    this.minPrice = minPrice;
    this.maxPrice = maxPrice;
    this.pageNum = pageNum;
  }

  public String getpName() {
    return pName;
  }

  public void setpName(String pName) {
    this.pName = pName;
  }

  public Integer getTypeId() {
    return typeId;
  }

  public void setTypeId(Integer typeId) {
    this.typeId = typeId;
  }

  public Integer getMinPrice() {
    return minPrice;
  }

  public void setMinPrice(Integer minPrice) {
    this.minPrice = minPrice;
  }

  public Integer getMaxPrice() {
    return maxPrice;
  }

  public void setMaxPrice(Integer maxPrice) {
    this.maxPrice = maxPrice;
  }

  public Integer getPageNum() {
    return pageNum;
  }

  public void setPageNum(Integer pageNum) {
    this.pageNum = pageNum;
  }
}
