package test;

import java.util.ArrayList;
import java.util.List;
import page.core.Pager;
import page.Factory.SelectLimitFactory;
import page.core.Pager.PageInfo;

public class PagePluginTest {

  public static void main(String[] args) {
    List<Integer> list = new ArrayList<>();
    for(int i = 0;i<10000;i++){
      list.add(i);
    }

    SelectLimitFactory<Integer> selectLimitFactory = new SelectLimitFactory<>();
    /*Pager<Integer> pager = selectLimitFactory.build(list.size(),
        200,
        ((offset, limit) -> list.subList((int)offset,(int)(offset+limit))));

    List<Integer> limitlist = pager.page(23424);*/
    Pager<Integer> pager = selectLimitFactory.build(list.size(),200,true, 1000,
        ((offset, limit) -> list.subList((int)offset,(int)(offset+limit))));
    pager.page(55);
    PageInfo<Integer> pageInfo = pager.getPageInfo();

    System.out.println("==============pageInfo===============");

    System.out.println(pageInfo.getCount());
    System.out.println(pageInfo.getCurPageNo());
    System.out.println(pageInfo.getTotalPage());

    System.out.println("==============pageInfo.items===============");

    for(Integer i : pageInfo.getPerPageItems()){
      System.out.println(i);
    }
  }
}
