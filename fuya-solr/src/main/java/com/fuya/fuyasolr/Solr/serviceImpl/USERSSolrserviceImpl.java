package com.fuya.fuyasolr.Solr.serviceImpl;

import com.fuya.fuyadao.entity.USERS;
import com.fuya.fuyasolr.Solr.dao.USERSearchdao;
import com.fuya.fuyasolr.Solr.service.USERSSolrservice;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class USERSSolrserviceImpl implements USERSSolrservice {
    @Autowired
    USERSearchdao searchdao;
    @Override
    public List<USERS> search(String keyword) {
        if (keyword==null||keyword.equals("")){
            return null;
        }
        SolrQuery solrQuery=new SolrQuery();
        //查询语句
        solrQuery.set("q","usersNAME:"+"*"+keyword+"*");
        solrQuery.setStart(0);
        solrQuery.setRows(5);
        List<USERS>usersList=null;
        try {
            usersList=searchdao.searchUSER(solrQuery);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    @Override
    public void addUSER(int id) throws IOException, SolrServerException {
        searchdao.addUSER(id);
    }
}
