package me.codeminions.service;

import me.codeminions.bean.api.base.ResponseModel;
import me.codeminions.bean.db.Snack;
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
 * 访问路径 localhost:8080/isnack/api/know
 */

@Path("/know")
public class PicKnowService {

    // 因为我们的工程目录不一样所以每次都要修改一下这个路径，目标是保存到工程下的/web/www文件夹
    private final static String DIR_PATH_LWX = "/media/codeminions/File/CodeMoky/AndroidStudioProjects/MyProject/ISnack/Web/ISnack/web/www/";
    private final static String DIR_PATH_AW = ".../ISnack/Web/ISnack";
    private final static String DIR_PATH_SQM = ".../ISnack/Web/ISnack";

    private Logger logger = Logger.getLogger(PicKnowService.class.getName());

    @POST
    @Path("/pic")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<Snack> getPicKnowResult(@FormDataParam("file") InputStream inputStream,
                                                 @FormDataParam("file") FormDataContentDisposition disposition,
                                                 @FormDataParam("id") int userId,
                                                 @FormDataParam("type")int type) {
        logger.info(userId + " type:  " + type);
        String imageName = Calendar.getInstance().getTimeInMillis()
                + disposition.getFileName();

        // 保存到磁盘
        saveFile(inputStream, imageName);

        //TODO: 2019/10/1  调用python代码使用机器学习模型得到识别内容

        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        // TODO: 2019/10/1  使用识别内容在数据库中查找对应数据
        Snack snack = new Snack();
        MyBatisUtil.closeSqlSession();

        if(snack == null) {
            return ResponseModel.buildParameterError();
        }
        return new ResponseModel<>(snack);
    }

    // 保存文件信息到磁盘
    private void saveFile(InputStream uploadedInputStream, String name) {
        logger.info("------saveFile-----");

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
}
