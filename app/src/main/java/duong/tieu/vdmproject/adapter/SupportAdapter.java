package duong.tieu.vdmproject.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import duong.tieu.vdmproject.R;
import duong.tieu.vdmproject.models.DSupport;

/**
 * Created by Elitebook on 4/10/2016.
 */
public class SupportAdapter extends ArrayAdapter<DSupport> {

    private Context mContext;
    private int mResID;
    private List<DSupport> mObject;

    public SupportAdapter(Context context, int resource, List<DSupport> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResID = resource;
        this.mObject = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(mContext, mResID, null);

        TextView tv_title = (TextView) view.findViewById(R.id.tv_item_row_title);
        TextView tv_Intro = (TextView) view.findViewById(R.id.tv_row_item_intro);
        TextView tv_content = (TextView) view.findViewById(R.id.tv_item_row_content);
        TextView tv_count_like = (TextView) view.findViewById(R.id.tv_item_row_count_like);
        TextView tv_count_comment = (TextView) view.findViewById(R.id.tv_item_row_count_comment);

        DSupport item = mObject.get(position);
        tv_title.setText(item.getTitle());
        tv_Intro.setText(item.getIntro());
        tv_content.setText(item.getContent());
        tv_count_comment.setText(item.getC_comment());
        tv_count_like.setText(item.getLike());

        return view;
    }
}
