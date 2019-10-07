package me.codeminions.service;

import me.codeminions.bean.api.ChangeModel;
import me.codeminions.bean.api.base.ResponseModel;
import me.codeminions.bean.db.Comment;
import me.codeminions.bean.db.Message;
import me.codeminions.bean.db.User;
import me.codeminions.bean.mapper.MessageMapper;
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

    @GET
    @Path("/getMessageById/{id}")
    public ResponseModel<List<Message>> getMessageById(@PathParam("id") @DefaultValue("0") int id) {
        if (id == 0) {
            return ResponseModel.buildParameterError();
        }

        MessageMapper messageMapper = sqlSession.getMapper(MessageMapper.class);
        List<Message> messages = messageMapper.getMessageById(id);

        return new ResponseModel<>(messages);
    }

    @GET
    @Path("/getMessageByUnlook/{isLook}")
    public ResponseModel<List<Message>> getMessageByUnlook(@PathParam("isLook") @DefaultValue("0") int isLook) {
        if (isLook != 0 && isLook != 1) {
            return ResponseModel.buildParameterError();
        }
        if (isLook == 1){
            return null;
        }
        MessageMapper messageMapper = sqlSession.getMapper(MessageMapper.class);
        List<Message> messages = messageMapper.getMessageByUnlook(isLook);

        return new ResponseModel<>(messages);
    }

    @GET
    @Path("/updateIsLook/{id}")
    public ResponseModel updateIsLook(@PathParam("id") @DefaultValue("0") int id)
    {
        if (id == 0)
        {
            return ResponseModel.buildParameterError();
        }

        MessageMapper messageMapper = sqlSession.getMapper(MessageMapper.class);
        messageMapper.updateIsLook(id);
        sqlSession.commit();

        return ResponseModel.buildOk();
    }
}
