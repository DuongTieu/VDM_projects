package duong.tieu.vdmproject.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

import duong.tieu.vdmproject.R;
import duong.tieu.vdmproject.activities.NewsActivity;
import duong.tieu.vdmproject.adapter.AdapterUser;
import duong.tieu.vdmproject.models.DGetUser;
import duong.tieu.vdmproject.models.MGetUser;
import duong.tieu.vdmproject.notifications.Notifications;
import duong.tieu.vdmproject.services.MyServices;

/**
 * Created by duong on 06/04/2016.
 */
public class ContactsFragment extends Fragment {

    private final String URL_GET_USER =
            "http://vsi.vietitech.com/api/member/mem_api.php?publicKey=5628acfce494c53189505f337bfa6870&action=getUser";

    private ArrayList<DGetUser> mListUser = new ArrayList<>();
    private AdapterUser mAdapter;
    private ListView mLvListUser;

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
        new GetAllUser().execute(URL_GET_USER);
        mLvListUser = (ListView) view.findViewById(R.id.lvContact);
        mAdapter = new AdapterUser(getActivity(), R.layout.item_user, mListUser);
        mLvListUser.setAdapter(mAdapter);


        mLvListUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new Notifications(getActivity().getBaseContext(),
                        NewsActivity.class, "Ban moi nan vao 1 item(change):  ", " vi tri la " + position).addNotification();
            }
        });
        return view;
    }


    private class GetAllUser extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            String urlTemp = params[0];
            String result = new MyServices().get(urlTemp, null);
            publishProgress(result);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            s = s.substring(s.indexOf("{"));
            MGetUser mGetUser = new Gson().fromJson(s, MGetUser.class);
            mListUser.addAll(mGetUser.getData());
            mAdapter.notifyDataSetChanged();
            Log.i("Tag", mGetUser.getData().size() + "");
        }
    }
}
