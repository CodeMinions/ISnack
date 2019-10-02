package me.codeminions.bean.db;

public class Message {      //对应表t_message

    private int messageID;
    private String content;
    private int send_id;
    private int receive_id;
    private int isLook;
    private String time;


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
