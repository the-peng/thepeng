package page.core;

import java.util.List;

public interface Pager<T> {

  /**
   * 跳转到首页
   * @return
   */
  public List<T> first();

  /**
   * 跳转到尾页
   * @return
   */
  public List<T> last();

  /**
   * 跳转到任意一页
   * @param toPageNo 跳转页码
   * @return
   */
  public List<T> page(long toPageNo);

  /**
   * 获取PageInfo
   * @return
   */
  public PageInfo getPageInfo();
  /**
   * 返回给前端的页面信息封装
   */
  interface PageInfo<T>{
    long getCurPageNo();
    List<T> getPerPageItems();
    long getCount();
    long getTotalPage();
  }
}
