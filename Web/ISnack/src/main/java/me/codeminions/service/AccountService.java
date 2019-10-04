package me.codeminions.service;


import me.codeminions.bean.api.base.LoginModel;
import me.codeminions.bean.api.base.RegisterModel;
import me.codeminions.bean.api.base.ResponseModel;
import me.codeminions.bean.db.Account;
import me.codeminions.bean.db.User;
import me.codeminions.bean.mapper.AccountMapper;

import me.codeminions.bean.mapper.UserMapper;
import me.codeminions.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

/**
 * 访问路径 127.0.0.1/isnack//api/account
 */
@Path("/account")
public class AccountService {

    private SqlSession sqlSession = MyBatisUtil.getSqlSession();
    private UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
    private AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
    private Logger logger = Logger.getLogger(AccountService.class.getName());

    @GET
    @Path("/test")
    public String testG() {
        return "connect successful!";
    }

    @POST
    @Path("/test")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<User> testP() {
        User user = new User(1,"aw","aw","男","1990.2.7","asfdgfdh");
        return ResponseModel.buildOk(user);
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<User> register(RegisterModel model) {

        logger.info(model.toString());

        if (!RegisterModel.check(model)) {
            // 参数异常
            return ResponseModel.buildParameterError();
        }

        User user = userMapper.getUserById(model.getUserID());
        if (user != null) {
            // 已有账户id
            return ResponseModel.buildHaveAccountError();
        }

        // 开始注册
        user = new User(model.getUserID(), model.getName(),model.getPwd(), model.getSex(), model.getBirth(),model.getPortrait() );

        userMapper.addUser(user);
        accountMapper.addAccount(user.getAccount());

        sqlSession.commit();

        logger.info("成功返回");
        return ResponseModel.buildOk(user);
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<User> login(LoginModel model) {

        logger.info(model.toString());

        if (!LoginModel.check(model)) {
            // 参数异常
            return ResponseModel.buildParameterError();
        }

        Account account = accountMapper.getAccountByIAP(model.getUserName(), model.getPwd());
        // 存在该用户及其对应密码
        if (account != null) {
            User user = (User) userMapper.getUserByName(model.getUserName());

            logger.info("成功返回");
            return ResponseModel.buildOk(user);
        } else {
            return ResponseModel.buildLoginError();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        sqlSession.close();
    }
}
