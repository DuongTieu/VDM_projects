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
import duong.tieu.vdmproject.contents.OppEle;

/**
 * Created by duong on 06/04/2016.
 */
public class OppAdapter extends ArrayAdapter<OppEle> {
    private Context context;
    private int resID;
    private List<OppEle> object;

    public OppAdapter(Context context, int resource, List<OppEle> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resID =  resource;
        this.object = objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, resID, null);

        TextView tv_user_name = (TextView) view.findViewById(R.id.tv_opp_user_name);
        TextView tv_content = (TextView) view.findViewById(R.id.tv_opp_content);
        TextView tv_date = (TextView) view.findViewById(R.id.tv_opp_minute);
        ImageView img_ava = (ImageView) view.findViewById(R.id.img_opp_ava);
        //button by Linear
        LinearLayout btn_Care = (LinearLayout) view.findViewById(R.id.btn_opp_care);
        LinearLayout btn_Cancel  = (LinearLayout) view.findViewById(R.id.btn_opp_cancel);


        OppEle item = object.get(position);
        tv_user_name.setText(item.getName());
        tv_content.setText(item.getContents());
        tv_date.setText(item.getMinute());
        img_ava.setImageResource(item.getAva());

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
