package duong.tieu.vdmproject.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import duong.tieu.vdmproject.R;
import duong.tieu.vdmproject.models.MessEle;

/**
 * Created by duong on 05/04/2016.
 */
public class MessAdapter  extends ArrayAdapter<MessEle>{
    Context context;
    int res;
    List<MessEle> objects;

    public MessAdapter(Context context, int resource, List<MessEle> objects) {
        super(context, resource, objects);

        this.context = context;
        this.res = resource;
        this.objects = objects;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //change layout
        View view = null;

        int layout1 = R.layout.row_item_send_mess_left;
        int layout2 = R.layout.row_item_send_mess_right;
        if (position == 2) {
            view = View.inflate(context, layout1, null);
        } else
            view = View.inflate(context, layout2, null);

        ImageView img_user_ava = (ImageView) view.findViewById(R.id.img_mess_user_ava);
        TextView tv_mess_user_name = (TextView) view.findViewById(R.id.tv_mess_user);
        TextView tv_mess_latest_mes = (TextView) view.findViewById(R.id.tv_last_mess);

        MessEle item = objects.get(position);
        tv_mess_latest_mes.setText(item.getUserText());
        tv_mess_user_name.setText(item.getUserName());
//        img_user_ava.setImageResource(item.getAva());

        return view;

    }
}
