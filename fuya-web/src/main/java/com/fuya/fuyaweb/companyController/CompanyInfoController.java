package com.fuya.fuyaweb.companyController;

import com.fuya.ActiveMQ.service.ProductService;
import com.fuya.fuyadao.entity.COMPANYBASICINFO;
import com.fuya.fuyadao.entity.COMPANYINFO;
import com.fuya.fuyadao.entity.YUESAOOTHERPROVE;
import com.fuya.fuyadao.entity.YUESOBASICINFO;
import com.fuya.fuyadao.model.CompanysInfosModle;
import com.fuya.fuyaservice.COMPANYBASICINFOService;
import com.fuya.fuyaservice.COMPANYINFOService;
import com.fuya.fuyaservice.YUESAOOTHERPROVEService;
import com.fuya.fuyasolr.SearchResult.SearchResult;
import com.fuya.fuyasolr.Solr.service.COMPANYBASICINFOSolrService;
import com.fuya.fuyasolr.Solr.service.COMPANYINFOSolrService;
import com.fuya.fuyasolr.Solr.service.YUESAOOTHERPROVESolrService;
import com.fuya.fuyautil.TotalPages;
import com.fuya.fuyautil.uuidUtil;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.Queue;
import javax.jms.Topic;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin
public class CompanyInfoController {
    @Autowired
    COMPANYBASICINFOSolrService companybasicinfoSolrService;
    @Autowired
    COMPANYINFOSolrService companyinfoSolrService;
    @Autowired
    COMPANYINFOService companyinfoService;
    @Autowired
    COMPANYBASICINFOService companybasicinfoService;
    @Autowired
    YUESAOOTHERPROVEService yuesaootherproveService;
    @Autowired
    YUESAOOTHERPROVESolrService yuesaootherproveSolrService;
    @Autowired
    private Queue queue;
    @Autowired
    private Topic topic;
    @Autowired
    private ProductService productService;

    //基本信息显示
    @RequiresRoles("company")
    @RequestMapping("/companys/Infos")
    @ResponseBody
    public Map<String,Object> companysInfos(HttpServletRequest request) throws IOException, SolrServerException {
        HttpSession session=request.getSession();
        int id= (int) session.getAttribute("id");
        Map<String,Object> msg=new HashMap<>();
        System.out.println("id:"+id);
       /* COMPANYBASICINFO companybasicinfo=companybasicinfoSolrService.search(id);
        COMPANYINFO companyinfo=companyinfoSolrService.findCOMPANYINFObyuserid(id);

        if (companybasicinfo==null){

            msg.put("msg","error");
            return JSONObject.fromObject(msg);
        }*/
        CompanysInfosModle companysInfosModle=companybasicinfoService.findCompanysInfosModleByUSERID(id);
       /* companysInfosModle.setCompanybasicinfo(companybasicinfo);
        companysInfosModle.setCompanyinfo(companyinfo);*/
        msg.put("msg",companysInfosModle);

        return msg;

    }

    @RequiresRoles("company")
    @RequestMapping("/companys/Infos/update")
    @ResponseBody
    public JSONObject companysInfosupdate(@RequestParam(name="name",defaultValue = "优先有限公司") String name, @RequestParam(name = "phone",defaultValue = "1314333") String phone,
                                          @RequestParam(name = "corporatename",defaultValue = "corporatename")String corporatename, @RequestParam(name = " email",defaultValue = " email")String email,
                                          @RequestParam(name = "licene",defaultValue = "licene")String licene,
                                          @RequestParam(name = "contactname",defaultValue = "contactname") String contactname, @RequestParam(name = "contantphone",defaultValue = "131422")String contantphone,
                                          @RequestParam(name = "liceneno",defaultValue = "1314")String liceneno, @RequestParam(name = "idcard",defaultValue = "1314")String idcard,
                                          @RequestParam(name = "address",defaultValue = "1314")String address,//公司地址
                                          @RequestParam(name = "area",defaultValue = "服务地区")String area,//服务地区
                                          @RequestParam(name = "idcardfile",defaultValue = "1314")String idcardfile,
                                          @RequestParam(name = "companyinfoid",defaultValue = "1314")int companyinfoid,
                                          HttpServletRequest request) throws IOException, SolrServerException {

        HttpSession session=request.getSession();
        int id= (int) session.getAttribute("id");
        System.out.println("id:"+id);

        /*COMPANYBASICINFO companybasicinfo=companybasicinfoSolrService.search(id);
        COMPANYINFO companyinfo=companyinfoSolrService.findCOMPANYINFObyuserid(id);
        int companybasicinfoid=companybasicinfo.getCOMPANYBASICINFOID();
        int companyinfoid=companyinfo.getCOMPANYINFOID();*/

        /*companybasicinfoSolrService.update(companybasicinfoid);
        companyinfoSolrService.update(companyinfoid);*/


        companybasicinfoService.updateCOMPANYBASICINFObyid(area,corporatename,id);
        companyinfoService.updateCOMPANYINFObyid(address,contactname,contantphone,email,idcard,idcardfile,licene,liceneno,id);

        //solr修改


        Map<String,Object> msg=new HashMap<>();
        msg.put("msg","success");
        return JSONObject.fromObject(msg);

    }

    //添加证明
    @RequiresRoles("company")
    @RequestMapping("/companys/proves/add")
    @ResponseBody
    public JSONObject provesupdate(@RequestParam(name="title",defaultValue = "证明") String title,@RequestParam(name="file",defaultValue = "位置") String file,HttpServletRequest request){
        HttpSession session=request.getSession();
        int id= (int) session.getAttribute("id");

        YUESAOOTHERPROVE yuesaootherprove=new YUESAOOTHERPROVE();
        yuesaootherprove.setTITLE(title);
        yuesaootherprove.setFILEAREA(file);
        yuesaootherprove.setUSERID(id);
        yuesaootherprove.setId(uuidUtil.getuuidUtil());
        yuesaootherproveService.save(yuesaootherprove);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",yuesaootherprove);
        return JSONObject.fromObject(msg);

    }

    //证明删除
    @RequiresRoles("company")
    @RequestMapping("/companys/proves/delete")
    @ResponseBody
    public JSONObject provesdelete(@RequestParam(name="YUESAOOTHERPROVEID") int YUESAOOTHERPROVEID,HttpServletRequest request) throws IOException, SolrServerException {
        HttpSession session=request.getSession();
        int id= (int) session.getAttribute("id");
        yuesaootherproveService.deleteByYUESAOOTHERPROVEID(YUESAOOTHERPROVEID);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg","success");
        return JSONObject.fromObject(msg);

    }

    @RequiresRoles("company")
    @RequestMapping("/companys/proves")
    @ResponseBody
    public JSONObject proves(HttpServletRequest request,@RequestParam(name = "start",defaultValue = "0")int start,@RequestParam(name = "rows",defaultValue = "10")int rows) throws IOException, SolrServerException {
        HttpSession session=request.getSession();
        int id= (int) session.getAttribute("id");
        Pageable pageable = PageRequest.of(start, rows);
        Page<YUESAOOTHERPROVE> page =yuesaootherproveService.findByUSERID(id,pageable);
        return JSONObject.fromObject(page);

    }

}
