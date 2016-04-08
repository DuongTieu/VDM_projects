package duong.tieu.vdmproject.models;

import java.util.ArrayList;

/**
 * Created by Nhahv on 4/6/2016.
 */
public class MGetUser {

    private boolean status;
    private ArrayList<DGetUser> data;

    public MGetUser() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ArrayList<DGetUser> getData() {
        return data;
    }

    public void setData(ArrayList<DGetUser> data) {
        this.data = data;
    }
}
