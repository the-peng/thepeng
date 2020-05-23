package page.Factory;

import page.Method.Method;
import page.SelectLimit;

public class SelectLimitFactory<T> {
  private SelectLimit<T> selectLimit;

  /**
   * 创建SelectLimit对象
   */
  public SelectLimitFactory(){
    this.selectLimit = new SelectLimit<T>();
  }

  /**
   * 开启阈值保护的方法
   * @param count 数据总量
   * @param perPage 每页数据量
   * @param offsetProtected 是否开启保护
   * @param offsetThreshold 保护阈值
   * @param queryMethod 查询方法
   * @return 分页插件对象
   */
  public SelectLimit<T> build(
      long count,
      long perPage,
      boolean offsetProtected,
      long offsetThreshold,
      Method<T> queryMethod){
    this.selectLimit.setCurPageNo(1);
    this.selectLimit.setCount(count);
    this.selectLimit.setPerPage(perPage);
    this.selectLimit.setQueryMethod(queryMethod);
    this.selectLimit.getTotalPage();
    this.selectLimit.setOffsetProtected(offsetProtected);
    this.selectLimit.setOffsetThreshold(offsetThreshold);
    return this.selectLimit;
  }

  /**
   * 未开启阈值保护
   * @param count
   * @param perPage
   * @param queryMethod
   * @return
   */
  public SelectLimit<T> build(long count, long perPage, Method<T> queryMethod){
    return build(count,perPage,false,1000,queryMethod);
  }

}
