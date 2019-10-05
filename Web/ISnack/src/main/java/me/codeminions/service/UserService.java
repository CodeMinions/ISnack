package me.codeminions.service;

import me.codeminions.bean.api.ChangeModel;
import me.codeminions.bean.api.base.ResponseModel;
import me.codeminions.bean.db.User;
import me.codeminions.bean.mapper.UserMapper;
import me.codeminions.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

/**
 * 访问路径 localhost:8080/isnack/api/user
 */
@Path("/user")
public class UserService {
    private SqlSession sqlSession= MyBatisUtil.getSqlSession();

    private Logger logger = Logger.getLogger(UserService.class.getName());

    @POST
    @Path("/change")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel doChange(ChangeModel model){

        logger.info(model.toString());

        if(!ChangeModel.check(model))
            return ResponseModel.buildParameterError();

        // 开始修改数据
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.setNameById(model.getUserID(), model.getName());
        sqlSession.commit();

        logger.info("修改成功..");

        return ResponseModel.buildOk();
    }

    @GET
    @Path("/search/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<List<User>> searchUser(@PathParam("name") @DefaultValue("") String name){
        if(name.isEmpty())
            return ResponseModel.buildParameterError();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = mapper.findUserByName(name);

        return new ResponseModel<>(list);
    }


}
