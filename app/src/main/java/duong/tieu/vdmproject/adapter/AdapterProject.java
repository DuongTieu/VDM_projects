package duong.tieu.vdmproject.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import duong.tieu.vdmproject.R;
import duong.tieu.vdmproject.activities.LoginActivity;
import duong.tieu.vdmproject.models.DGetProject;
import duong.tieu.vdmproject.models.MCareProject;
import duong.tieu.vdmproject.models.Models;
import duong.tieu.vdmproject.services.MyServices;

/**
 * Created by duong on 06/04/2016.
 */
public class AdapterProject extends ArrayAdapter<DGetProject> {
    private Context mContext;
    private int mResID;
    private List<DGetProject> mObject;

    public static final String URL_CARE =
            "http://vsi.vietitech.com/api/project_api.php?publicKey=5628acfce494c53189505f337bfa6870&action=careProject&";
    private static final String URL_CANCEL_CARE = "http://vsi.vietitech.com/api/project_api.php?publicKey=5628acfce494c53189505f337bfa6870&action=cancelCareProject&";



    public AdapterProject(Context context, int resource, List<DGetProject> objects) {
        super(context, resource, objects);

        this.mContext = context;
        this.mResID = resource;
        this.mObject = objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(mContext, mResID, null);

        TextView tv_user_name = (TextView) view.findViewById(R.id.tv_opp_user_name);
        TextView tv_content = (TextView) view.findViewById(R.id.tv_opp_content);
        TextView tv_date = (TextView) view.findViewById(R.id.tv_opp_minute);
        ImageView img_ava = (ImageView) view.findViewById(R.id.img_opp_ava);
        //button by Linear
        final LinearLayout btn_Care = (LinearLayout) view.findViewById(R.id.btn_opp_care);
        LinearLayout btn_Cancel = (LinearLayout) view.findViewById(R.id.btn_opp_cancel);
        final TextView tv_btn_quantam = (TextView) view.findViewById(R.id.tv_btn_quantam);
        final TextView tv_btn_huy = (TextView) view.findViewById(R.id.tv_btn_huy);

        final DGetProject item = mObject.get(position);
        tv_user_name.setText(item.getUsername());
        tv_content.setText(item.getContent());
        tv_date.setText(item.getCdate());
        //img_ava.setImageResource(item.getAva());

        //set onClick
        btn_Care.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // set sự kiện quan tâm
                tv_btn_quantam.setTextColor(Color.BLUE);
                new CareProject().execute(LoginActivity.mUsername, item.getId());
                tv_btn_huy.setTextColor(Color.BLACK);
            }
        });

        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               tv_btn_huy.setTextColor(Color.GREEN);
                new CancelCareProject().execute(LoginActivity.mUsername, item.getId());
                tv_btn_quantam.setTextColor(Color.BLACK);
            }
        });
        return view;
    }

    private class CareProject extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            String urlTemp =URL_CARE + Models.USER_NAME + "=" + params[0]
                    + "&" + Models.PROJECT_ID + "=" + params[1];
            MyServices myServices = new MyServices();
            String string = myServices.get(urlTemp, null);
            publishProgress();
            return string;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            s = s.substring(s.indexOf("{"));
            MCareProject mCareProject = new Gson().fromJson(s, MCareProject.class);
            Log.i("Tag", mCareProject.getStatus() + "");
        }
    }
    private class CancelCareProject extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            String urlTemp =URL_CANCEL_CARE + Models.USER_NAME + "=" + params[0]
                    + "&" + Models.PROJECT_ID + "=" + params[1];
            MyServices myServices = new MyServices();
            String string = myServices.get(urlTemp, null);
            publishProgress();
            return string;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            s = s.substring(s.indexOf("{"));
            MCareProject mCareProject = new Gson().fromJson(s, MCareProject.class);
            Log.i("Tag", mCareProject.getStatus() + "");
        }
    }

}
