package com.fuya.fuyasolr.Solr.serviceImpl;

import com.fuya.fuyadao.entity.YUESOBASICINFO;
import com.fuya.fuyasolr.SearchResult.SearchResult;
import com.fuya.fuyasolr.Solr.dao.YUESOBASICINFOSearchdao;
import com.fuya.fuyasolr.Solr.service.YUESOBASICINFOSolrservice;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class YUESOBASICINFOSolrImpl implements YUESOBASICINFOSolrservice {
    @Autowired
    private  YUESOBASICINFOSearchdao yuesobasicinfoSearchdao;
    //查找名字的关键词
    @Override
    public SearchResult search(String keyword) throws IOException, SolrServerException {
        //查找前五个
        SolrQuery solrQuery=new SolrQuery();
        solrQuery.set("q","NAME:"+"*"+keyword+"*");
        solrQuery.setStart(0);
        solrQuery.setRows(5);

        SearchResult result=yuesobasicinfoSearchdao.searchResult(solrQuery,false);


        return result;
    }

    @Override
    public SearchResult search(String keyword,String workarea,String type,String minwages,String maxwages,String nativeplace,String age, int page, int rows) throws IOException, SolrServerException {
        //创建查询对象
        SolrQuery solrQuery=new SolrQuery();
        StringBuffer stringBuffer=new StringBuffer();
        if (!workarea.equals("null")){
            stringBuffer.append("WORKAREA:"+workarea);
        }
        if (!type.equals("null")){
            stringBuffer.append("AND yusaoTYPE:"+type);
        }
        if (!minwages.equals("null")&&maxwages.equals("null")){
            stringBuffer.append("AND yusaoWAGES:[ "+minwages +"TO " +maxwages+"]");//工资
        }
        if (!nativeplace.equals("null")){
            stringBuffer.append("AND　NATIVEPLACE:"+nativeplace);

        }

        solrQuery.setQuery(stringBuffer.toString());
        if (page<=0){
            page=1;
        }
        //分页
        solrQuery.setStart((page-1)*rows);
        solrQuery.setRows(rows);
        solrQuery.set("df","NAME");
        solrQuery.setHighlight(true);
        solrQuery.setHighlightSimplePre("<em style='color:red'>");
        solrQuery.setHighlightSimplePost("</em>");

        //执行dao查询
        SearchResult result=yuesobasicinfoSearchdao.searchResult(solrQuery,true);
        long recordCount=result.getResultCount();
        int totalPage= (int) (recordCount/rows);
        if (recordCount%rows>0)totalPage++;
        result.setTotalPage(totalPage);
        return result;
    }

    @Override
    public void addYUESOBASICINFO(int id) throws IOException, SolrServerException {
        yuesobasicinfoSearchdao.addYUESOBASICINFO(id);
    }

    @Override
    public YUESOBASICINFO searchbyid(int id) throws IOException, SolrServerException {
        SolrQuery solrQuery=new SolrQuery();
        solrQuery.set("YUESOBASICINFOID:"+id);
        solrQuery.setStart(0);
        solrQuery.setRows(1);
        YUESOBASICINFO yuesobasicinfo=yuesobasicinfoSearchdao.searchbyid(solrQuery);
        return yuesobasicinfo;
    }

    @Override
    public void delete(int id) throws IOException, SolrServerException {
        SolrQuery solrQuery=new SolrQuery();
        //查询语句
        solrQuery.set("q","YUESOBASICINFOID:"+id);
        String ids=yuesobasicinfoSearchdao.findid(solrQuery);
        if (ids!=null){
            yuesobasicinfoSearchdao.delete(ids);
        }

    }
}
