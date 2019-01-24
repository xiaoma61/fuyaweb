package com.fuya.fuyasolr.Solr.serviceImpl;

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
    @Override
    public String search(String keyword) {

        return null;
    }

    @Override
    public SearchResult search(String keyword, int page, int rows) throws IOException, SolrServerException {
        //创建查询对象
        SolrQuery solrQuery=new SolrQuery();
        solrQuery.setQuery(keyword);
        if (page<=0){
            page=1;
        }
        //分页
        solrQuery.setStart((page-1)*rows);
        solrQuery.setRows(rows);
        solrQuery.set("df","yuesaoNAME");
        solrQuery.setHighlight(true);
        solrQuery.setHighlightSimplePre("<em style='color:red'>");
        solrQuery.setHighlightSimplePost("</em>");

        //执行dao查询
        SearchResult result=yuesobasicinfoSearchdao.searchResult(solrQuery);
        long recordCount=result.getResultCount();
        int totalPage= (int) (recordCount/rows);
        if (recordCount%rows>0)totalPage++;
        result.setTotalPage(totalPage);
        return result;
    }
}
