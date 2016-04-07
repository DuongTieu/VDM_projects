package duong.tieu.vdmproject.Message;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import duong.tieu.vdmproject.Core.Notifications;
import duong.tieu.vdmproject.R;

/**
 * Created by duong on 06/04/2016.
 */
public class Contacts_Fragment extends Fragment {

     ListView lv_contacts;

    String [] userName = {"a", "b","c","d","e","f","g","h","k"};
    public Contacts_Fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contacts_fragment, container, false);

        List<ContactsEle> mListContacts = new ArrayList<ContactsEle>();

        for(int i = 0; i < userName.length; i++){
            ContactsEle item = new ContactsEle();
            item.setUserName(userName[i]);

            mListContacts.add(item);
        }

        lv_contacts = (ListView) view.findViewById(R.id.lv_contacts);
        ContactsAdapter adapter = new ContactsAdapter(getContext(), R.layout.contacts_row_item, mListContacts);
        lv_contacts.setAdapter(adapter);


        lv_contacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new Notifications(getActivity().getBaseContext(), "Ban moi nan vao 1 item", " vi tri la " + position).addNotification();

            }
        });
        return view;

    }
}
