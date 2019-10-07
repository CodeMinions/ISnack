package me.codeminions.bean.db;

import com.google.gson.annotations.Expose;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {      //对应表t_message

    @Expose
    private int messageID;

    @Expose
    private String content;

    @Expose
    private int send_id;

    @Expose
    private int receive_id;

    @Expose
    private int isLook;

    @Expose
    private String time;


    public Message(int messageID,String content,int send_id,int receive_id){
        this.messageID=messageID;
        this.content=content;
        this.send_id=send_id;
        this.receive_id=receive_id;
        this.isLook=0;
    }

    public Message(){
        this.isLook= 0;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.time = df.format(new Date());
    }


    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageId) {
        this.messageID = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSend_id() {
        return send_id;
    }

    public void setSend_id(int send_id) {
        this.send_id = send_id;
    }

    public int getReceive_id() {
        return receive_id;
    }

    public void setReceive_id(int receive_id) {
        this.receive_id = receive_id;
    }

    public int getIsLook() {
        return isLook;
    }

    public void setIsLook(int isLook) {
        this.isLook = isLook;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
