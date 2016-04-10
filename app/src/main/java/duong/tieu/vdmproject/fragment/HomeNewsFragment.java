package duong.tieu.vdmproject.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

import duong.tieu.vdmproject.R;
import duong.tieu.vdmproject.adapter.NewsAdapter;
import duong.tieu.vdmproject.models.INewVSI;
import duong.tieu.vdmproject.models.Models;
import duong.tieu.vdmproject.models.NewVSI;
import duong.tieu.vdmproject.services.MyServices;

/**
 * Created by Elitebook on 4/9/2016.
 */
public class HomeNewsFragment extends Fragment {


    private ArrayList<NewVSI> mArrayList = new ArrayList<>();
    NewsAdapter mNewsAdapte;
    private ListView mListView;

    public HomeNewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_new_fragment, container, false);

        mListView = (ListView) view.findViewById(R.id.lv_home_new_fragment);
        mNewsAdapte = new NewsAdapter(getContext(), R.layout.row_item_news, mArrayList);
        mListView.setAdapter(mNewsAdapte);

        new GetNewVSI().execute(Models.URL_NEW_VSI, "admin");

        return view;
    }

    private class GetNewVSI extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String urlTemp = params[0] + params[1];
            String result = new MyServices().post(urlTemp, null);
            publishProgress();
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            s = s.substring(s.indexOf("{"));
            INewVSI iNewVSI = new Gson().fromJson(s, INewVSI.class);
            mNewsAdapte.clear();
            mNewsAdapte.addAll(iNewVSI.getData());
            mNewsAdapte.notifyDataSetChanged();
            Log.i("Tag get id", iNewVSI.getData().get(12).getId() + "");
            Log.i("Tag", s);
        }
    }


}
