package me.codeminions.service;

import me.codeminions.bean.api.CommentModel;
import me.codeminions.bean.api.MarkModel;
import me.codeminions.bean.api.SnackListModel;
import me.codeminions.bean.api.base.ResponseModel;
import me.codeminions.bean.db.*;
import me.codeminions.bean.mapper.*;
import me.codeminions.util.MyBatisUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 访问路径 localhost:8080/isnack/api/snack/
 */
@Path("/snack")
public class SnackService {
    private SqlSession sqlSession = MyBatisUtil.getSqlSession();

    private Logger logger = Logger.getLogger(SnackService.class.getName());

    @GET
    @Path("/test")
    public String testG() {
        return "connect sucessful!";
    }

    @GET
    @Path("/getSnack/{name}")
    public ResponseModel<Snack> getSnack(@PathParam("name") @DefaultValue("") String name) {
        if (name.isEmpty()) {
            return ResponseModel.buildParameterError();
        }

        SnackMapper snackMapper = sqlSession.getMapper(SnackMapper.class);
        Snack snack = snackMapper.getSnackByName(name);

        return new ResponseModel<>(snack);
    }

    /**
     * 获取所有零食基本信息
     * 后期可进行分页
     */
    @GET
    @Path("/all")
    public ResponseModel<List<Snack>> getAllSnack() {
        SnackMapper snackMapper = sqlSession.getMapper(SnackMapper.class);
        List<Snack> snacks = snackMapper.getAllSnack();
        return ResponseModel.buildOk(snacks);
    }

    @GET
    @Path("/getSnackInfo")
    public ResponseModel<SnackInfo> getSnackInfo(@QueryParam("id") @DefaultValue("0") int id) {
        if (id == 0) {
            return ResponseModel.buildParameterError();
        }

        SnackInfoMapper snackInfoMapper = sqlSession.getMapper(SnackInfoMapper.class);

        SnackInfo snackInfo = snackInfoMapper.getSnackInfoById(id);

        return ResponseModel.buildOk(snackInfo);
    }

    @GET
    @Path("/getCommentBySnack")
    public ResponseModel<List<Comment>> getCommentBySnack(@QueryParam("id") @DefaultValue("0") int id) {
        if (id == 0) {
            return ResponseModel.buildParameterError();
        }

        CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
        List<Comment> comments = commentMapper.getCommentBySnack(id);

        return ResponseModel.buildOk(comments);
    }

    @GET
    @Path("/getCommentByUser")
    public ResponseModel<List<Comment>> getCommentByUser(@QueryParam("id") @DefaultValue("0") int id) {
        if (id == 0) {
            return ResponseModel.buildParameterError();
        }

        CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
        List<Comment> comments = commentMapper.getCommentByUser(id);

        return ResponseModel.buildOk(comments);
    }

    @POST
    @Path("/setComment")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<Comment> setComment(CommentModel model) {
        logger.info(model.toString());
        if (!CommentModel.check(model))    //参数异常
        {
            return ResponseModel.buildParameterError();
        }

        //setComment
        CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
        Comment comment = new Comment(model.getSend_id(), model.getSnack_id(), model.getContent(), model.getStar());
        commentMapper.setComment(comment);
        sqlSession.commit();

        //setMark
        MarkMapper markMapper = sqlSession.getMapper(MarkMapper.class);
        SnackMark mark = new SnackMark(model.getSend_id(),model.getSnack_id(),model.getStar());
        markMapper.setMark(mark);
        sqlSession.commit();

        return ResponseModel.buildOk(comment);
    }

    @GET
    @Path("/allRecommend")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<List<Comment>> getAllComment() {
        CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
        List<Comment> list = commentMapper.getAllComment();
        return ResponseModel.buildOk(list);
    }

    @GET
    @Path("/updateLike/{id}")
    public ResponseModel updateLike(@PathParam("id") @DefaultValue("0") int id)
    {
        if (id == 0)
        {
            return ResponseModel.buildParameterError();
        }

        CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
        commentMapper.updateLike(id);
        sqlSession.commit();

        return ResponseModel.buildOk();
    }

    @GET
    @Path("/getMark/{id}")
    public ResponseModel<Map> getMark(@PathParam("id") @DefaultValue("0") int id) {
        if (id == 0) {
            return ResponseModel.buildParameterError();
        }

        MarkMapper markMapper = sqlSession.getMapper(MarkMapper.class);
        ArrayList<SnackMark> marks = markMapper.getSnackMark(id);
        float aver = 0;
        int[] markcount = new int[10];
        for( SnackMark mark: marks)
        {
            aver += mark.getMark();
            switch (mark.getMark())
            {
                case 0:markcount[0] += 1;break;
                case 1:markcount[1] += 1;break;
                case 2:markcount[2] += 1;break;
                case 3:markcount[3] += 1;break;
                case 4:markcount[4] += 1;break;
                case 5:markcount[5] += 1;break;
                case 6:markcount[6] += 1;break;
                case 7:markcount[7] += 1;break;
                case 8:markcount[8] += 1;break;
                case 9:markcount[9] += 1;break;
            }
        }
        aver /= marks.size();

        HashMap markRecord = new HashMap();
        for(int i=0 ; i<10 ; i++)                   //前十位做记录，最后一位为平均值
        {
            markRecord.put(i,markcount[i]);
        }
        markRecord.put("aver",aver);

        //将平均分存入t_snack表
        SnackMapper snackMapper = sqlSession.getMapper(SnackMapper.class);
        snackMapper.updateSnackMark(aver,id);
        sqlSession.commit();

        return new ResponseModel<>(markRecord);
    }

    @GET
    @Path("/recommend")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<List<Snack>> Recommend() {
        SnackMapper snackMapper = sqlSession.getMapper(SnackMapper.class);
        List<Snack> snacks = snackMapper.recommend();

        return ResponseModel.buildOk(snacks);
    }

    @GET
    @Path("/getSnackList")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<List<SnackListModel>> getSnackListByUser(@QueryParam("id")int userId) {
        SnackListMapper snackListMapper = sqlSession.getMapper(SnackListMapper.class);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        SnackMapper snackMapper = sqlSession.getMapper(SnackMapper.class);
        List<SnackList> oList = snackListMapper.getSnackListByUser(userId);

        List<SnackListModel> result = new ArrayList<>();
        SnackListModel model;

        String title = oList.get(0).getTitle();
        String content = oList.get(0).getContent();
        String time = oList.get(0).getTime();
        User user = userMapper.getUserById(oList.get(0).getUser_id());
        List<Snack> snacks = new ArrayList<>();

        int currentList = oList.get(0).getList_id();
        for(SnackList list: oList) {
            if (currentList != list.getList_id()) {
                // 当前数据段与上一数据段不是同一清单，提交当前数据到结果集中
                model = new SnackListModel( user, title, snacks, content, time);
                result.add(model);
                // 标记当前清单id
                currentList = list.getList_id();
                // 初始化基本字段
                title = list.getTitle();
                content = list.getContent();
                time = list.getTime();
                user = userMapper.getUserById(list.getUser_id());
                snacks = new ArrayList<>();

                // 放入当前数据段值
                Snack snack = snackMapper.getSnackById(list.getList_id());
                snacks.add(snack);
            }else {
                // 当前数据段与上一数据段仍在同一清单，仅存放当前数据段
                Snack snack = snackMapper.getSnackById(list.getList_id());
                snacks.add(snack);
            }
        }
        return ResponseModel.buildOk(result);
    }
}
