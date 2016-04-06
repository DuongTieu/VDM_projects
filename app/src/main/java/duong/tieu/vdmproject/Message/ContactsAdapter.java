package duong.tieu.vdmproject.Message;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import duong.tieu.vdmproject.R;

/**
 * Created by duong on 06/04/2016.
 */
public class ContactsAdapter extends ArrayAdapter<ContactsEle> {
    private Context context;
    private int res;
    private List<ContactsEle> objects;

    public ContactsAdapter(Context context, int resource, List<ContactsEle> objects) {
        super(context, resource, objects);
        this.context = context;
        this.res = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, res, null);
        TextView tv_contacts_user_name = (TextView) view.findViewById(R.id.tv_contexts_user_name);

        ContactsEle item = objects.get(position);
        tv_contacts_user_name.setText(item.getUserName());
        return view;
    }
}
