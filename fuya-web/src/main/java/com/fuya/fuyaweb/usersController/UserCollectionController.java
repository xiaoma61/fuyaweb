package com.fuya.fuyaweb.usersController;

import com.fuya.fuyadao.entity.YUESOBASICINFO;
import com.fuya.fuyadao.model.YuesaobasicInfo;
import com.fuya.fuyaservice.COLLECTIONSService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserCollectionController {
    @Autowired
    COLLECTIONSService collectionsService;
    @RequiresRoles("users")
    @RequestMapping("/users/OrderCollection/")
    @ResponseBody
    JSONArray UsersOrdersRCollection(HttpServletRequest request,@RequestParam(value = "start", defaultValue = "0")int start,@RequestParam(name = "rows",defaultValue = "10")int rows){
        HttpSession session=request.getSession();
        int id= (int) session.getAttribute("id");
        List<YUESOBASICINFO>  yuesobasicinfoList=collectionsService.findByFROMID(id);
        List<YuesaobasicInfo> yuesaobasicInfoList=new ArrayList<>();

        for (YUESOBASICINFO yuesobasicinfo:yuesobasicinfoList){
            YuesaobasicInfo yuesaobasicInfo=new YuesaobasicInfo();
            yuesaobasicInfo.setId(yuesobasicinfo.getUSERSID());
            yuesaobasicInfo.setImage(yuesobasicinfo.getPHOTO());
            yuesaobasicInfo.setName(yuesobasicinfo.getNAME());
            yuesaobasicInfo.setLevels(yuesobasicinfo.getLEVELS());
            yuesaobasicInfoList.add(yuesaobasicInfo);
        }
        PageHelper.startPage(start,rows);
        PageInfo<YuesaobasicInfo> pageInfo=new PageInfo<>(yuesaobasicInfoList);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",pageInfo);
        return JSONArray.fromObject(msg);

    }
}
