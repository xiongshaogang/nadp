package cn.com.netease.nadp.monitor.resources.host;

import cn.com.netease.nadp.monitor.annotations.NadpResource;
import cn.com.netease.nadp.monitor.common.Constant;
import cn.com.netease.nadp.monitor.model.PaginationModel;
import cn.com.netease.nadp.monitor.model.ResultModel;
import cn.com.netease.nadp.monitor.service.env.EnvService;
import cn.com.netease.nadp.monitor.service.host.HostService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bjbianlanzhou on 2016/8/11.
 * Description
 */
@NadpResource
@Path("/host")
public class HostResource {
    @Autowired
    private HostService service;

    @POST
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("/list")
    public ResultModel list(Map<String,String> map){
        ResultModel model = new ResultModel();
        try {
            PaginationModel paginationModel = new PaginationModel();
            paginationModel.setiTotalRecords(Constant.PAGINATION_MAX_COUNT);
            paginationModel.setAaData(service.getData(map.get("host"),Constant.STATUS_USEFUL,map.get("iDisplayStart")==null?0:Integer.valueOf(map.get("iDisplayStart")),Constant.PAGINATION_MAX_COUNT));
            paginationModel.setiTotalDisplayRecords(service.getDataCount(map.get("host"),Constant.STATUS_USEFUL));
            paginationModel.setsEcho(map.get("sEcho"));
            Map<String,Object> data = new HashMap<String, Object>();
            model.setInfo(paginationModel);
            model.setDefault(Constant.ResultCode.SUCCESS);
        }catch (Exception ex){
            ex.printStackTrace();
            model.setDefault(Constant.ResultCode.FAIL);
        }
        return model;
    }

    @PUT
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("/insert")
    public ResultModel insert(Map<String,Object> map){
        ResultModel model = new ResultModel();
        try {
            service.insert(map);
            model.setDefault(Constant.ResultCode.SUCCESS);
        }catch (Exception ex){
            ex.printStackTrace();
            model.setDefault(Constant.ResultCode.FAIL);
        }
        return model;
    }

    @POST
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("/update/getRel")
    public ResultModel getRel(Map<String,String> map) {
        ResultModel model = new ResultModel();
        try {
            model.setInfo(service.getRel(map));
            model.setDefault(Constant.ResultCode.SUCCESS);
        } catch (Exception ex) {
            ex.printStackTrace();
            model.setDefault(Constant.ResultCode.FAIL);
        }
        return model;
    }

    @PUT
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("/update")
    public ResultModel update(Map<String,Object> map) {
        ResultModel model = new ResultModel();
        try {
            service.update(map);
            model.setDefault(Constant.ResultCode.SUCCESS);
        } catch (Exception ex) {
            ex.printStackTrace();
            model.setDefault(Constant.ResultCode.FAIL);
        }
        return model;
    }

    @DELETE
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("/delete")
    public ResultModel delete(Map<String,String> map) {
        ResultModel model = new ResultModel();
        try {
            service.delete(map);
            model.setDefault(Constant.ResultCode.SUCCESS);
        } catch (Exception ex) {
            ex.printStackTrace();
            model.setDefault(Constant.ResultCode.FAIL);
        }
        return model;
    }
}
