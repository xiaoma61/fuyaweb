package com.fuya.fuyasolr.Solr.serviceImpl;

import com.fuya.fuyadao.entity.COLLECTIONS;
import com.fuya.fuyasolr.Solr.dao.COLLECTIONSSearchdao;
import com.fuya.fuyasolr.Solr.service.COLLECTIONSSolrService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
//层是业务逻辑层注解，这个注解只是标注该类处于业务逻辑层。
public class COLLECTIONSSolrServiceImpl implements COLLECTIONSSolrService {
    @Autowired
    COLLECTIONSSearchdao collectionsSearchdao;
    @Override
    public void addCOLLECTIONS(int id) throws IOException, SolrServerException {
        collectionsSearchdao.addCOLLECTIONS(id);

    }

    @Override
    public List<String> Searchbyfromidandtoid(int fromid, int toid) throws IOException, SolrServerException {
        SolrQuery solrQuery=new SolrQuery();
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("FROMID:"+fromid);
        stringBuffer.append(" AND TOID:"+toid);
//        solrQuery.set("FROMID:",fromid);
//        solrQuery.set("TOID:",toid);
        solrQuery.setQuery(stringBuffer.toString());
//        solrQuery.setStart(0);
//        solrQuery.setRows(1);
        List<String> collections=collectionsSearchdao.Search(solrQuery);

        return collections;


    }

    @Override
    public void delete(String id) throws IOException, SolrServerException {


      //  System.out.println("dddd"+id);
      //  String query="id:"+id;
       collectionsSearchdao.delete(id);

      //  return collections;
    }

    @Override
    public void delete(int fromid, int toid) throws IOException, SolrServerException {
        String query="FROMID:"+fromid+" AND TOID:"+toid;

        collectionsSearchdao.delete(query);
    }
}
