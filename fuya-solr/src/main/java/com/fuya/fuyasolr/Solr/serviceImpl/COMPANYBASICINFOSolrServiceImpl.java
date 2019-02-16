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

@Service
public class COMPANYBASICINFOSolrServiceImpl implements COMPANYBASICINFOSolrService {
    //实现查找页面
    @Autowired
    COMPANYBASICINFOSearchdao companybasicinfoSearchdao;
    @Override
    public COMPANYBASICINFO search(int id) throws IOException, SolrServerException {
        SolrQuery query=new SolrQuery();
        query.set("q","USERID:"+id);
        COMPANYBASICINFO searchResult =companybasicinfoSearchdao.Search(query);
        return searchResult;
    }

    //排序
    @Override
    public SearchResult findall(String keyword, int start, int rows) throws IOException, SolrServerException {
        SolrQuery query=new SolrQuery();
        query.setStart(start);
        query.setRows(rows);
        query.setSort("LEVELS", SolrQuery.ORDER.desc);
        if (keyword!=null&&!keyword.equals("")){
            query.set("q","CORPORATENAME:","*"+keyword+"*");//相似的查找
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

    @Override
    public String findName(int id) throws IOException, SolrServerException {
        SolrQuery query=new SolrQuery();
        query.set("q","COMPANYBASICINFOID:"+id);
        String name=companybasicinfoSearchdao.SearchNAME(query);
        return name;
    }

    @Override
    public void update(int COMPANYBASICINFOID) throws IOException, SolrServerException {

        SolrQuery query=new SolrQuery();
        query.set("q","COMPANYBASICINFOID:"+COMPANYBASICINFOID);
        String id=companybasicinfoSearchdao.Searchid(query);
        delete(id);
        addCOMPANYBASICINFO(COMPANYBASICINFOID);

    }

    @Override
    public void delete(String id) throws IOException, SolrServerException {

    }
}
