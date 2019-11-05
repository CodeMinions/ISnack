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
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.logging.Logger;

/**
 * 访问路径 localhost:8080/isnack/api/know
 */

@Path("/know")
public class PicKnowService {

    // 因为我们的工程目录不一样所以每次都要修改一下这个路径，目标是保存到工程下的/web/www文件夹
    private final static String DIR_PATH_NOW = "/media/codeminions/File/CodeMoky/AndroidStudioProjects/MyProject/ISnack/Web/ISnack/web/www/";
    private final static String DIR_PATH_LWX = "/home/sipc/Desktop/ISnack/Web/ISnack/web/www/";
    private final static String DIR_PATH_AW = ".../ISnack/Web/ISnack/web/www/";
    private final static String DIR_PATH_SQM = ".../ISnack/Web/ISnack/web/www/";

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

        String result = sendMessage((DIR_PATH_LWX + imageName).getBytes());

        //TODO: 2019/10/1  调用python代码使用机器学习模型得到识别内容

//        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        // TODO: 2019/10/1  使用识别内容在数据库中查找对应数据
//        Snack snack = new Snack("士力架");
        Snack snack = new Snack(result);
//        MyBatisUtil.closeSqlSession();

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

    private String sendMessage(byte[] data) {
        String ip = "localhost";        // 设置发送地址和端口号
        int port = 9999;
        Socket socket = null;
        try {
            // 连接服务器
            socket = new Socket(ip, port);
            // 获取输入流
            InputStream in = socket.getInputStream();   //读取数据
            // 获取输出流
            OutputStream out = socket.getOutputStream(); // 发送数据
            // 包装输入流，输出流，包装一下可以直接传输字符串，不包装的话直接使用InputStream和OutputStream只能直接传输byte[]类型数据
            BufferedReader inRead = new BufferedReader(new InputStreamReader(in));
//			PrintWriter outWriter = new PrintWriter(out);

            // 发送数据
//            out.write(header);
            out.write(data);

            // 接受应答
            String result = inRead.readLine();  // 使用了包装后的输入流方便读取消息

            logger.info("RESULT IS " + result);
            return result;
        } catch(UnknownHostException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return "defeat";
    }
}
