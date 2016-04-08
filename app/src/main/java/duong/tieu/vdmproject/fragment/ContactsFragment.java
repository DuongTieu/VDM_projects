package duong.tieu.vdmproject.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import duong.tieu.vdmproject.R;
import duong.tieu.vdmproject.activities.NewsActivity;
import duong.tieu.vdmproject.adapter.AdapterUser;
import duong.tieu.vdmproject.adapter.ContactsAdapter;
import duong.tieu.vdmproject.models.ContactsEle;
import duong.tieu.vdmproject.models.DGetUser;
import duong.tieu.vdmproject.notifications.Notifications;

/**
 * Created by duong on 06/04/2016.
 */
public class ContactsFragment extends Fragment {

    private final String URL_GET_USER =
            "http://vsi.vietitech.com/api/member/mem_api.php?publicKey=5628acfce494c53189505f337bfa6870&action=getUser";
     ListView lv_contacts;
    String [] userName = {"a", "b","c","d","e","f","g","h","k"};

    private ArrayList<DGetUser> mListUser = new ArrayList<>();
    private AdapterUser mAdapter;

    public ContactsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        List<ContactsEle> mListContacts = new ArrayList<ContactsEle>();

        new GetAllUser().execute(URL_GET_USER);

//        for(int i = 0; i < userName.length; i++){
//            ContactsEle item = new ContactsEle();
//            item.setUserName(userName[i]);
//
//            mListContacts.add(item);
//        }

        lv_contacts = (ListView) view.findViewById(R.id.lv_contacts);
        ContactsAdapter adapter = new ContactsAdapter(getContext(), R.layout.row_item_contacts, mListContacts);
        lv_contacts.setAdapter(adapter);


        lv_contacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new Notifications(getActivity().getBaseContext(), NewsActivity.class,  "Ban moi nan vao 1 item(change):  ", " vi tri la " + position).addNotification();

            }
        });
        return view;

    }
}
