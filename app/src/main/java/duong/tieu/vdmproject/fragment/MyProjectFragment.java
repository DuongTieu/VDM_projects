package duong.tieu.vdmproject.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

import duong.tieu.vdmproject.R;
import duong.tieu.vdmproject.activities.LayoutOpportunityItem;
import duong.tieu.vdmproject.adapter.AdapterProject;
import duong.tieu.vdmproject.models.DGetProject;
import duong.tieu.vdmproject.models.MGetProject;
import duong.tieu.vdmproject.models.Models;
import duong.tieu.vdmproject.services.MyServices;

/**
 * Created by duong on 06/04/2016.
 */
public class MyProjectFragment extends Fragment {

    AdapterProject mAdapterProject;
    private ListView mLv_myProject;
    private ArrayList<DGetProject> mListProject = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_opp, container, false);
        mLv_myProject = (ListView) view.findViewById(R.id.lv_opp_fragment);

        mAdapterProject = new AdapterProject(getContext(), R.layout.row_item_opp, mListProject);
        mLv_myProject.setAdapter(mAdapterProject);

        mLv_myProject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent senData = new Intent(getActivity(), LayoutOpportunityItem.class);
                Bundle data = new Bundle();
                data.putString("userName", mListProject.get(position).getTitle());
                data.putString("time", mListProject.get(position).getFrom_date());
                data.putString("content", mListProject.get(position).getContent());
                senData.putExtra("fromOppFragment", data);
                startActivity(senData);
            }
        });

        new GetProjectItem().execute(Models.URL_GET_PROJECT_ITEM, "admin");

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
            mListProject.clear();
            mListProject.addAll(mGetProject.getData());
            mAdapterProject.notifyDataSetChanged();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }


}

