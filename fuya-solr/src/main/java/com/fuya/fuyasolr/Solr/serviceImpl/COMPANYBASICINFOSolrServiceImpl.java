package com.fuya.fuyasolr.Solr.serviceImpl;

import com.fuya.fuyadao.entity.COMPANYBASICINFO;
import com.fuya.fuyasolr.SearchResult.SearchResult;
import com.fuya.fuyasolr.Solr.dao.COMPANYBASICINFOSearchdao;
import com.fuya.fuyasolr.Solr.service.COMPANYBASICINFOSolrService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
public class COMPANYBASICINFOSolrServiceImpl implements COMPANYBASICINFOSolrService {
    //实现查找页面
    @Autowired
    COMPANYBASICINFOSearchdao companybasicinfoSearchdao;
    @Override
    public SearchResult search(int id) throws IOException, SolrServerException {
        SolrQuery query=new SolrQuery();
        query.setStart(0);
        query.setRows(1);
        query.set("companybasicinfoID"+id);
        SearchResult searchResult =companybasicinfoSearchdao.SearchCOMPANYBASICINFO(query,null);
        return searchResult;
    }

    //排序
    @Override
    public SearchResult findall(String keyword, int start, int rows) throws IOException, SolrServerException {
        SolrQuery query=new SolrQuery();
        query.setStart(start);
        query.setRows(rows);
        query.setSort("companybasicinfoLEVELS", SolrQuery.ORDER.desc);
        if (keyword!=null&&!keyword.equals("")){
            query.set("q","companybasicinfoCORPORATENAME:","*"+keyword+"*");//相似的查找
            query.setHighlight(true);
            query.setHighlightSimplePre("<em style='color:red'>");
            query.setHighlightSimplePost("</em>");

        }
        SearchResult searchResult=companybasicinfoSearchdao.SearchCOMPANYBASICINFO(query,keyword);
        //searchResult.setResultCount();
        long recordCount=searchResult.getResultCount();
        int totalPage= (int) (recordCount/rows);
        if (recordCount%rows>0)totalPage++;
        searchResult.setTotalPage(totalPage);

        return  searchResult;
    }


    @Override
    public void addCOMPANYBASICINFO(int id) throws IOException, SolrServerException {
        companybasicinfoSearchdao.addCOMPANYBASICINFO(id);

    }
}
