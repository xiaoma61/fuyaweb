package com.fuya.fuyasolr.Solr.serviceImpl;

import com.fuya.fuyadao.entity.COMPANYYUESAO;
import com.fuya.fuyasolr.Solr.dao.COMPANYYUESAOSearchdao;
import com.fuya.fuyasolr.Solr.service.COMPANYYUESAOSolrService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class COMPANYYUESAOSolrServiceImpl implements COMPANYYUESAOSolrService {

    @Autowired
    COMPANYYUESAOSearchdao companyyuesaoSearchdao;
    @Override
    public void addCOMPANYYUESAO(int id) throws IOException, SolrServerException {
        companyyuesaoSearchdao.addCOMPANYYUESAO(id);

    }

    @Override
    public void delete(int id) throws IOException, SolrServerException {
        SolrQuery solrQuery=new SolrQuery();
        //查询语句
        solrQuery.set("q","YUESAOID:"+id);

        List<String> ids=companyyuesaoSearchdao.findid(solrQuery);
        if (ids!=null&&ids.size()>0){
            if (ids!=null&&ids.size()>0) {
                for (String mid : ids) {
                    companyyuesaoSearchdao.delete(mid);
                }
            }
        }

    }
}
