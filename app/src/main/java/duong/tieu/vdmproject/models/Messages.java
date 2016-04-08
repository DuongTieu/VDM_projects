package duong.tieu.vdmproject.models;

import java.io.Serializable;

/**
 * Created by Nhahv on 3/11/2016.
 */
public class Messages implements Serializable {

    private String content;
    private String date;
    private int intEmailSend;
    private int intEmailReceive;

    public Messages() {
    }

    public Messages(String content, String date, int intEmailSend, int intEmailReceive) {
        this.content = content;
        this.date = date;
        this.intEmailSend = intEmailSend;
        this.intEmailReceive = intEmailReceive;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIntEmailSend() {
        return intEmailSend;
    }

    public void setIntEmailSend(int intEmailSend) {
        this.intEmailSend = intEmailSend;
    }

    public int getIntEmailReceive() {
        return intEmailReceive;
    }

    public void setIntEmailReceive(int intEmailReceive) {
        this.intEmailReceive = intEmailReceive;
    }
}
