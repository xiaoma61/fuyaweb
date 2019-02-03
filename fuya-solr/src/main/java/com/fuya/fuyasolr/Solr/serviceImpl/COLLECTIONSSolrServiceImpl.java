package com.fuya.fuyasolr.Solr.serviceImpl;

import com.fuya.fuyadao.entity.COLLECTIONS;
import com.fuya.fuyasolr.Solr.dao.COLLECTIONSSearchdao;
import com.fuya.fuyasolr.Solr.service.COLLECTIONSSolrService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
    public COLLECTIONS Searchbyfromidandtoid(int fromid, int toid) throws IOException, SolrServerException {
        SolrQuery solrQuery=new SolrQuery();
        solrQuery.set("collectionsFROMID:",fromid);
        solrQuery.set("collectionsTOID:",toid);
        COLLECTIONS collections=collectionsSearchdao.Search(solrQuery);

        return collections;


    }

    @Override
    public void delete(int id) throws IOException, SolrServerException {
        String query="collectionsID:"+id;
        collectionsSearchdao.delete(query);
    }
}
