package me.codeminions.service;

import me.codeminions.bean.api.CommentModel;
import me.codeminions.bean.api.MarkModel;
import me.codeminions.bean.api.base.ResponseModel;
import me.codeminions.bean.db.Comment;
import me.codeminions.bean.db.Snack;
import me.codeminions.bean.db.SnackInfo;
import me.codeminions.bean.db.SnackMark;
import me.codeminions.bean.mapper.CommentMapper;
import me.codeminions.bean.mapper.MarkMapper;
import me.codeminions.bean.mapper.SnackInfoMapper;
import me.codeminions.bean.mapper.SnackMapper;
import me.codeminions.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
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


    @GET
    @Path("/getSnackInfo/{id}")
    public ResponseModel<SnackInfo> getSnackInfo(@PathParam("id") @DefaultValue("0") int id) {
        if (id == 0) {
            return ResponseModel.buildParameterError();
        }

        SnackInfoMapper snackInfoMapper = sqlSession.getMapper(SnackInfoMapper.class);

        SnackInfo snackInfo = snackInfoMapper.getSnackInfoById(id);

        return new ResponseModel<>(snackInfo);
    }


    @GET
    @Path("/getComment/{id}")
    public ResponseModel<List<Comment>> getComment(@PathParam("id") @DefaultValue("0") int id) {
        if (id == 0) {
            return ResponseModel.buildParameterError();
        }

        CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
        List<Comment> comments = commentMapper.getComment(id);

        return new ResponseModel<>(comments);
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

        CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
//        Comment comment = commentMapper.setComment(model.getContent(),model.getSend_id(),model.getSnack_id(),model.getStar());

        Comment comment = new Comment(model.getContent(), model.getSend_id(), model.getSnack_id(), model.getStar());
        commentMapper.setComment(comment);

        return new ResponseModel<>(comment);
    }

    //TODO  点赞功能！！！！

    @POST
    @Path("/setMark")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<SnackMark> setMark(MarkModel model) {
        logger.info(model.toString());
        if (!MarkModel.check(model)) {
            return ResponseModel.buildParameterError();
        }

        MarkMapper markMapper = sqlSession.getMapper(MarkMapper.class);
//        SnackMark mark = markMapper.setMark(model.getUser_id(), model.getSnack_id(), model.getMark());
        SnackMark mark = new SnackMark(model.getUser_id(),model.getSnack_id(),model.getMark());
        markMapper.setMark(mark);


        return new ResponseModel<>(mark);
    }


    @GET
    @Path("/getMark/{id}")
    public ResponseModel<ArrayList<Float>> getMark(@PathParam("id") @DefaultValue("0") int id) {
        if (id == 0) {
            return ResponseModel.buildParameterError();
        }

        MarkMapper markMapper = sqlSession.getMapper(MarkMapper.class);
        ArrayList<SnackMark> marks = markMapper.getSnackMark(id);
        float mark = 0;
        int markNumber[] = new int[6];
        for (SnackMark m : marks) {
            mark += m.getMark();
            switch ((int) m.getMark()) {
                case 0:
                    markNumber[0] += 1;
                    break;
                case 1:
                    markNumber[1] += 1;
                    break;
                case 2:
                    markNumber[2] += 1;
                    break;
                case 3:
                    markNumber[3] += 1;
                    break;
                case 4:
                    markNumber[4] += 1;
                    break;
                case 5:
                    markNumber[5] += 1;
                    break;
            }
        }
        mark /= marks.size();

        ArrayList<Float> markRecord = new ArrayList<>(7);

        for (int i = 0; i < 6; i++) {
            markRecord.add(i, (float) markNumber[i]);
        }

        markRecord.add(6, mark);

        return new ResponseModel<>(markRecord);
    }

}
