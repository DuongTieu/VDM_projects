package duong.tieu.vdmproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import duong.tieu.vdmproject.R;
import duong.tieu.vdmproject.models.Messages;
import duong.tieu.vdmproject.models.Models;

/**
 * Created by Nhahv on 3/11/2016.
 */
public class MessagesAdapter extends BaseAdapter {

    private List<Messages> mListMessages;
    private Context mContext;
    private LayoutInflater mInflater;

    public MessagesAdapter(Context context, List<Messages> objects) {
        this.mContext = context;
        this.mListMessages = objects;
        this.mInflater = LayoutInflater.from(this.mContext);
    }

    @Override
    public int getCount() {
        return mListMessages.size();
    }

    @Override
    public Object getItem(int position) {
        return mListMessages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Messages message = mListMessages.get(position);

        if (message.getIntEmailSend() == Models.MESSAGES_SEND) {

            convertView = mInflater.inflate(R.layout.item_message_right, null);
        } else {
            convertView = mInflater.inflate(R.layout.item_message_left, null);
        }

        TextView txtMessage = (TextView) convertView.findViewById(R.id.tvMessages);
        txtMessage.setText(message.getContent());
        return convertView;
    }
}
