package duong.tieu.vdmproject.getproject;

import java.util.List;

/**
 * Created by Nhahv on 4/6/2016.
 */
public class MGetProject {

    private boolean status;
    private String total_row;
    private List<DGetProject> data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTotal_row() {
        return total_row;
    }

    public void setTotal_row(String total_row) {
        this.total_row = total_row;
    }

    public List<DGetProject> getData() {
        return data;
    }

    public void setData(List<DGetProject> data) {
        this.data = data;
    }
}
