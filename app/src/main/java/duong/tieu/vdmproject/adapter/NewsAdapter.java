package duong.tieu.vdmproject.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import duong.tieu.vdmproject.R;
import duong.tieu.vdmproject.models.NewVSI;

/**
 * Created by Elitebook on 4/9/2016.
 */
public class NewsAdapter extends ArrayAdapter<NewVSI> {
    private Context mContext;
    private int mResID;
    private List<NewVSI> mObject;

    public NewsAdapter(Context context, int resource, List<NewVSI> objects) {
        super(context, resource, objects);

        this.mContext = context;
        this.mObject = objects;
        this.mResID = resource;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(mContext, mResID, null);

        TextView tv_title = (TextView) view.findViewById(R.id.tv_item_row_title);
        TextView tv_intro = (TextView) view.findViewById(R.id.tv_row_item_intro);
        TextView tv_content = (TextView) view.findViewById(R.id.tv_item_row_content);
        TextView tv_count_like = (TextView) view.findViewById(R.id.tv_item_row_count_like);
        TextView tv_count_comment = (TextView) view.findViewById(R.id.tv_item_row_count_comment);

        ImageView img_picture = (ImageView) view.findViewById(R.id.img_item_row_picture);

        LinearLayout bnt_like = (LinearLayout) view.findViewById(R.id.btn_item_row_like);
        LinearLayout bnt_comment = (LinearLayout) view.findViewById(R.id.btn_item_row_comment);
        LinearLayout bnt_more = (LinearLayout) view.findViewById(R.id.btn_item_row_more);

        NewVSI item = mObject.get(position);
        tv_title.setText(item.getTitle());
        tv_intro.setText(item.getIntro());
        tv_content.setText(item.getFulltext());


        return view;
    }
}
