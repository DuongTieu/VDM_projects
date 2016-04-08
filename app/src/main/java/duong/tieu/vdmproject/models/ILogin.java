package duong.tieu.vdmproject.models;

/**
 * Created by Nhahv on 4/7/2016.
 */
public class ILogin {
    private String status;
    private DLogin data;

    public ILogin() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DLogin getData() {
        return data;
    }

    public void setData(DLogin data) {
        this.data = data;
    }
}
