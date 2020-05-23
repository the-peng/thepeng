package page;

import java.util.List;
import page.core.AbstractPage;
import page.exception.PageException;

public class SelectLimit<T> extends AbstractPage<T> {
  /******************************
   * 关于偏移量问题
   * 1.如果orderby一个非索引字段是无法进行优化的
   * 2.如果是pk或者uk可以通过子查询id在翻页来优化
   * 如果在1情况下还是要跳转到很后面的页面就采取阈值保护，
   * 会产生数据错误，超过阈值的数据都会自动跳转到阈值之前的数据
   ******************************/


  /**
   * 偏移量
   */
  private long offset;

  /**
   * 偏移量保护 偏移量过大会导致sql变慢 默认false;
   */
  private boolean offsetProtected = false;



  /**
   * 偏移量保护阈值 默认1000
   */
  private long offsetThreshold = 1000;

  /**
   * 页面跳转
   * @param toPageNo 要跳转到的页面号
   * @return 跳转后查询到的结果
   */
  public List<T> page(long toPageNo) {
    //异常情况处理
    try{
      canTurn(toPageNo);
    }catch (PageException p){
      if("超出页数范围-".equals(p.getMessage())) {
        toPageNo = 1;
      } else if("超出页数范围+".equals(p.getMessage())){
        toPageNo = totalPage;
      } else if("已经是最后一页了".equals(p.getMessage())) {
        toPageNo = totalPage;
      } else if("已经是第一页了".equals(p.getMessage())) {
        toPageNo = 1;
      }
    }
    //设置偏移量
    setOffset((toPageNo-1)*perPage);
    curPageNo = toPageNo;
    perPageItems = queryMethod.queryMethod(offset, perPage);
    return perPageItems;
  }

  public long getOffset() {
    return offset;
  }

  public boolean isOffsetProtected() {
    return offsetProtected;
  }

  public void setOffsetProtected(boolean offsetProtected) {
    this.offsetProtected = offsetProtected;
  }

  public void setOffsetThreshold(long offsetThreshold) {
    this.offsetThreshold = offsetThreshold;
  }

  public long getOffsetThreshold() {
    return offsetThreshold;
  }

  private void setOffset(long offset) {
    if(offsetProtected){
      if(isOutofThreshold(offset)) offset = offsetThreshold;
    }
    this.offset = offset;
  }

  /**
   * 判断是否超出阈值
   * @param offset
   * @return
   */
  private boolean isOutofThreshold(long offset){
    return offset > offsetThreshold;
  }

  @Override
  public List<T> first() {
    curPageNo = 1;
    return page(curPageNo);
  }

  @Override
  public List<T> last() {
    curPageNo = totalPage;
    return page(curPageNo);
  }
}
