package duong.tieu.vdmproject.models;

import java.util.List;

/**
 * Created by Nhahv on 4/10/2016.
 */
public class MSupport {
    private String total_row;
    private List<DSupport> data;

    public MSupport() {
    }

    public String getTotal_row() {
        return total_row;
    }

    public void setTotal_row(String total_row) {
        this.total_row = total_row;
    }

    public List<DSupport> getData() {
        return data;
    }

    public void setData(List<DSupport> data) {
        this.data = data;
    }
}
