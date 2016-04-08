package duong.tieu.vdmproject.fragment;

import android.content.Intent;
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
import duong.tieu.vdmproject.adapter.AdapterProject;
import duong.tieu.vdmproject.getproject.DGetProject;
import duong.tieu.vdmproject.getproject.MGetProject;
import duong.tieu.vdmproject.models.Models;
import duong.tieu.vdmproject.services.MyServices;

/**
 * Created by duong on 06/04/2016.
 */
public class OppFragment extends Fragment {
    AdapterProject mAdapterProject;
    private ListView mLv_opp;
    private ArrayList<DGetProject> mListProject = new ArrayList<>();
    private ArrayList<DGetProject> mListProjectTemp = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_opp, container, false);
        mLv_opp = (ListView) view.findViewById(R.id.lv_opp_fragment);


        mAdapterProject = new AdapterProject(getContext(), R.layout.row_item_opp, mListProject);
        mLv_opp.setAdapter(mAdapterProject);

        mLv_opp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent senData = new Intent(getActivity(), NewsActivity.class);
                Bundle data = new Bundle();
                data.putInt("position", position);
                senData.putExtra("user_position", data);
                startActivity(senData);
            }
        });

        new GetProject().execute(Models.URL_GET_PROJECT, "admin");

        if (mListProjectTemp.size() > mListProject.size()) {
            mListProject.addAll(mListProjectTemp);
            mAdapterProject.notifyDataSetChanged();
        }

        return view;
    }


    private class GetProject extends AsyncTask<String, String, String> {

//        private Interfaces mInterfaces;

        @Override
        protected String doInBackground(String... params) {
            String temp = params[0] + params[1];
            String result = new MyServices().get(temp, null);
            publishProgress(result);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            s = s.substring(s.indexOf("{"));
            MGetProject mGetProject = new Gson().fromJson(s, MGetProject.class);
            Log.i("count ", mGetProject.getData().size() + "");
            mListProjectTemp.clear();
            mListProjectTemp.addAll(mGetProject.getData());
            Log.e("Size ", mListProjectTemp.size() + "");
        }
    }


}
