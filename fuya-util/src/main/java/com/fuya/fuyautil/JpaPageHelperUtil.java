package com.fuya.fuyautil;

import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;

public class JpaPageHelperUtil {
    public static List<PageInfo> SetStartPage(List<?> list, int pageNow, int Size){
        boolean isFristPage=false;
        boolean isLastPage=false;
        boolean haveNexPage=false;
        boolean havePerPage=false;
        int pageSize=0;
        int fromIndex=(pageNow-1)*Size;
        int toIndex=pageNow*Size;
        if(fromIndex==0) {
            isFristPage=true;
        }else if (!isFristPage) {
            havePerPage=true;
        }
        if(toIndex>=list.size()) {
            toIndex=list.size();
            isLastPage=true;
        }else if (!isLastPage) {
            haveNexPage=true;
        }
        if(list.size()%Size==0) {
            pageSize=list.size()/Size;
        }else {
            pageSize=list.size()/Size+1;
        }
        List<PageInfo> pageInfos=new ArrayList<>();
        PageInfo pageInfo=new PageInfo();
        pageInfo.setStartRow(pageNow);
        pageInfo.setPageSize(pageSize);
        pageInfo.setTotal(Size);
        pageInfo.setIsFirstPage(isFristPage);
        pageInfo.setIsLastPage(isLastPage);
        pageInfo.setHasNextPage(haveNexPage);
        pageInfo.setHasPreviousPage(havePerPage);
        pageInfo.setList(list.subList(fromIndex, toIndex));
        pageInfos.add(pageInfo);
        return pageInfos;
    }

}
