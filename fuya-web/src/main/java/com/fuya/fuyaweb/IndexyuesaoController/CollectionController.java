package com.fuya.fuyaweb.IndexyuesaoController;

import com.fuya.ActiveMQ.service.ProductService;
import com.fuya.fuyadao.entity.COLLECTIONS;
import com.fuya.fuyaservice.COLLECTIONSService;
import com.fuya.fuyasolr.Solr.service.COLLECTIONSSolrService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.Queue;
import javax.jms.Topic;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 完成
 */
@Controller
public class CollectionController {
 /*   @Autowired
    COLLECTIONSSolrService solrService;*/
    @Autowired
    COLLECTIONSService collectionsService;
    //实现收藏功能
  /*  @Autowired
    private Queue queue;
    @Autowired
    private Topic topic;
    @Autowired
    private ProductService productService;*/

    /**
     *
     *
     * @param toid   月嫂id
     * @param type   1为收藏2为删除
     * @param request
     * @return
     * @throws IOException
     * @throws SolrServerException
     */
    @RequiresRoles("users")
    @RequestMapping("/fuyayusao/collection")
    @ResponseBody
    public Map<String,String> collection(@RequestParam(name = "toid") int toid,
                                         @RequestParam(name = "type")int type, HttpServletRequest request)  {

        HttpSession httpSession=request.getSession();
        int fromid= (int) httpSession.getAttribute("id");

        if (type==1){
            COLLECTIONS collections=new COLLECTIONS();
            collections.setFROMID(fromid);
            collections.setTOID(toid);
            collectionsService.save(collections);
        }else {
            collectionsService.deleteByFROMIDAndTOID(fromid,toid);
        }




        Map<String,String>map=new HashMap<>();
        map.put("msg","success");
            return map;
        }




}
