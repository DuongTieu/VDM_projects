package duong.tieu.vdmproject.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import duong.tieu.vdmproject.R;
import duong.tieu.vdmproject.models.DGetProject;

/**
 * Created by duong on 06/04/2016.
 */
public class AdapterProject extends ArrayAdapter<DGetProject> {
    private Context mContext;
    private int mResID;
    private List<DGetProject> mObject;

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
        LinearLayout btn_Care = (LinearLayout) view.findViewById(R.id.btn_opp_care);
        LinearLayout btn_Cancel = (LinearLayout) view.findViewById(R.id.btn_opp_cancel);


        DGetProject item = mObject.get(position);
        tv_user_name.setText(item.getUsername());
        tv_content.setText(item.getContent());
        tv_date.setText(item.getCdate());
        //img_ava.setImageResource(item.getAva());

        //set onClick
        btn_Care.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "You clicked Care button", Toast.LENGTH_SHORT).show();
            }
        });

        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "You clicked Cancel button", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
