package duong.tieu.vdmproject.models;

/**
 * Created by Nhahv on 4/7/2016.
 */
public class DMessages {

    private String id;
    private String from;
    private String to;
    private String content;
    private String cdate;
    private String isrun;

    public DMessages() {
    }

    public DMessages(String id, String from,
                     String to, String content,
                     String cdate, String isrun) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.content = content;
        this.cdate = cdate;
        this.isrun = isrun;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getIsrun() {
        return isrun;
    }

    public void setIsrun(String isrun) {
        this.isrun = isrun;
    }
}
