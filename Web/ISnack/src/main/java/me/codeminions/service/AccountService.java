package me.codeminions.service;


import me.codeminions.bean.api.LoginModel;
import me.codeminions.bean.api.RegisterModel;
import me.codeminions.bean.api.base.ResponseModel;
import me.codeminions.bean.db.Account;
import me.codeminions.bean.db.User;
import me.codeminions.bean.mapper.AccountMapper;

import me.codeminions.bean.mapper.UserMapper;
import me.codeminions.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.util.Calendar;
import java.util.logging.Logger;

/**
 * 访问路径 127.0.0.1/isnack/api/account
 */
@Path("/account")
public class AccountService {
    // 因为我们的工程目录不一样所以每次都要修改一下这个路径，目标是保存到工程下的/web/www文件夹
    private final static String DIR_PATH_LWX = "/media/codeminions/File/CodeMoky/AndroidStudioProjects/MyProject/ISnack/Web/ISnack/web/www/portrait/";
    private final static String DIR_PATH_AW = ".../ISnack/Web/ISnack/web/www/portrait/";
    private final static String DIR_PATH_SQM = ".../ISnack/Web/ISnack/web/www/portrait/";

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

//        User user = userMapper.getUserById(model.getUserID());
        User user = userMapper.getUserByName(model.getName());
        if (user != null) {
            // 已有账户id
            return ResponseModel.buildHaveAccountError();
        }

        // 开始注册
        user = new User( model.getName(),model.getPwd(), model.getSex(), model.getBirth() );

        userMapper.addUser(user);

        // 其实没有account表所以不需要在添加一次
//        accountMapper.addAccount(user.getAccount());
        sqlSession.commit();

        // id自增，注册时没有id，这里重新获取一下（这样操作好像有点蠢可能设计上出问题）
        user = userMapper.getUserByName(user.getName());

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
            User user = userMapper.getUserByName(model.getUserName());

            logger.info("成功返回");
            return ResponseModel.buildOk(user);
        } else {
            return ResponseModel.buildLoginError();
        }
    }

    @POST
    @Path("/port")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<String> postPortrait(@FormDataParam("file") InputStream inputStream,
                                                 @FormDataParam("file") FormDataContentDisposition disposition,
                                                 @FormDataParam("id") int userId) {
        logger.info(userId +"");
        String imageName = Calendar.getInstance().getTimeInMillis()
                + disposition.getFileName();
        // 保存到磁盘
        saveFile(inputStream, imageName);

        // 将图片路径信息存储到数据库
        userMapper.setPortraitById(userId, imageName);
        sqlSession.commit();
        return ResponseModel.buildOk(imageName);
    }

    // 保存文件信息到磁盘
    private void saveFile(InputStream uploadedInputStream, String name) {
        logger.info("------saveFile------");

        File file = new File(DIR_PATH_LWX + name);
        logger.info(file.getAbsolutePath());

        if(!file.getParentFile().exists())
            if(!file.getParentFile().mkdirs()) {
                logger.info("文件夹创建失败！");
                return;
            }
        try {
            logger.info("开始保存文件！");
            OutputStream outputStream = new FileOutputStream(file);
            int read;
            byte[] bytes = new byte[1024];
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        sqlSession.close();
    }
}
