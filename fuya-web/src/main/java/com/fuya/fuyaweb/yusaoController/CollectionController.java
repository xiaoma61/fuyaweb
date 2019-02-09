package com.fuya.fuyaweb.yusaoController;

import com.fuya.ActiveMQ.service.ProductService;
import com.fuya.fuyadao.entity.COLLECTIONS;
import com.fuya.fuyaservice.COLLECTIONSService;
import com.fuya.fuyasolr.Solr.service.COLLECTIONSSolrService;
import net.sf.json.JSONObject;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.Queue;
import javax.jms.Topic;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CollectionController {
    @Autowired
    COLLECTIONSSolrService solrService;
    @Autowired
    COLLECTIONSService collectionsService;
    //实现收藏功能
    @Autowired
    private Queue queue;
    @Autowired
    private Topic topic;
    @Autowired
    private ProductService productService;

    @RequestMapping("/fuyayusao/collection")
    @ResponseBody
    public Map<String,String> collection(@RequestParam(name = "fromid") int fromid , @RequestParam(name = "toid") int toid,
                                         @RequestParam(name = "type")int type) throws IOException, SolrServerException {
        //1的话删除
        List<String> collectionList=solrService.Searchbyfromidandtoid(fromid,toid);
        Map<String,String>map=new HashMap<>();
        map.put("msg","success");
        if (type==1){
            collectionsService.deleteByFROMIDAndTOID(fromid,toid);

            if (collectionList.size()>0){
                for (String collections:collectionList)
                productService.sendMessage(this.topic,"collections-delete:"+collections);
            }
            return map;

            //修改solr
        }else {
            if (collectionList.size()>0){
                System.out.println(collectionList);
                return map;
            }
            COLLECTIONS collections=new COLLECTIONS();
            collections.setFROMID(fromid);
            collections.setTOID(toid);
            collectionsService.save(collections);
            //添加
            productService.sendMessage(this.topic,"collections:"+collections.getID());

            return map;
        }


    }

}
