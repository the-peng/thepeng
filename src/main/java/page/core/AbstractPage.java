package page.core;

import java.util.List;
import page.Method.Method;
import page.exception.PageException;

public abstract class AbstractPage<T> implements Pager<T>{
  /**
   * 查询方法
   */
  protected Method<T> queryMethod;

  /**
   * 当前页码
   */
  protected long curPageNo;

  /**
   * 记录总数
   */
  protected long count;

  /**
   * 总页数
   */
  protected long totalPage;

  /**
   * 每页数据量
   */
  protected long perPage;

  /**
   * 每页数据的集合
   */
  protected List<T> perPageItems;




  /**
   * 由总记录数count和每页记录数perpage算出
   * @return 总页数
   */
  public long getTotalPage(){
    this.totalPage = (count%perPage == 0) ? count/perPage : count/perPage + 1;
    return  this.totalPage;
  }

  /**
   * 页面跳转，根据传输进来的页面要跳转的页码跳转
   * @return 跳转页面后的页面内容
   */
  public abstract List<T> page(long toPageNo);

  /**
   * 判断跳转能不能成功
   */
  public void canTurn(long toPageNo) {
    if(curPageNo == totalPage && toPageNo > totalPage) throw new PageException("已经是最后一页了");
    else if(curPageNo == 1 && toPageNo <1) throw new PageException("已经是第一页了");
    else if(toPageNo < 1) throw new PageException("超出页数范围-");
    else if(toPageNo > totalPage) throw new PageException("超出页数范围+");
  }

  /**
   * 获取分页查询后的结果 用来返回给前端
   */
  public Pager.PageInfo getPageInfo(){
    return new PageInfo();
  }




  public void setQueryMethod(Method<T> queryMethod){
    this.queryMethod = queryMethod;
  }

  public Method<T> getQueryMethod(){
    return this.queryMethod;
  }

  public long getCurPageNo() {
    return curPageNo;
  }

  public void setCurPageNo(long curPageNo) {
    this.curPageNo = curPageNo;
  }

  public long getCount() {
    return count;
  }

  public void setCount(long count) {
    this.count = count;
  }

  public long getPerPage() {
    return perPage;
  }

  public void setPerPage(long perPage) {
    this.perPage = perPage;
  }

  public List<T> getPerPageItems() {
    return perPageItems;
  }

  public void setPerPageItems(List<T> perPageItems) {
    this.perPageItems = perPageItems;
  }

  /**
   * 内部类
   * 用来给前端返回所需要的信息
   */
  public class PageInfo<T> implements Pager.PageInfo<T>{
    @Override
    public long getCurPageNo() {
      return curPageNo;
    }

    @Override
    public List getPerPageItems() {
      return perPageItems;
    }

    @Override
    public long getCount() {
      return count;
    }

    @Override
    public long getTotalPage() {
      return totalPage;
    }
  }
}
