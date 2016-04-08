package duong.tieu.vdmproject.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import duong.tieu.vdmproject.R;
import duong.tieu.vdmproject.adapter.AdapterProject;
import duong.tieu.vdmproject.getproject.DGetProject;
import duong.tieu.vdmproject.getproject.MGetProject;
import duong.tieu.vdmproject.models.Models;
import duong.tieu.vdmproject.services.MyServices;

/**
 * Created by duong on 06/04/2016.
 */
public class MyProjectFragment extends Fragment {

    AdapterProject mAdapterProject;
    private ListView mLv_myProject;
    private ArrayList<DGetProject> mListProject = new ArrayList<>();
    private ArrayList<DGetProject> mListProjectTemp = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_opp, container, false);
        mLv_myProject = (ListView) view.findViewById(R.id.lv_opp_fragment);

        mAdapterProject = new AdapterProject(getContext(), R.layout.row_item_opp, mListProject);
        mLv_myProject.setAdapter(mAdapterProject);

        mLv_myProject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "postion is: " + position, Toast.LENGTH_SHORT).show();
            }
        });

        new GetProjectItem().execute(Models.URL_GET_PROJECT_ITEM, "admin");

        if (mListProjectTemp.size() > mListProject.size()) {
            mListProject.addAll(mListProjectTemp);
            mAdapterProject.notifyDataSetChanged();
        }

        return view;
    }

    private class GetProjectItem extends AsyncTask<String, String, Void> {

        @Override
        protected Void doInBackground(String... params) {
            String temp = params[0] + params[1];
            String result = new MyServices().get(temp, null);
            publishProgress(result);
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            String s = values[0].substring(values[0].indexOf("{"));
            MGetProject mGetProject = new Gson().fromJson(s, MGetProject.class);
            mListProjectTemp.clear();
            mListProjectTemp.addAll(mGetProject.getData());
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }


}

