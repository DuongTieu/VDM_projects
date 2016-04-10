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
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import duong.tieu.vdmproject.R;
import duong.tieu.vdmproject.adapter.AdapterProject;
import duong.tieu.vdmproject.models.DGetProject;
import duong.tieu.vdmproject.models.MGetProject;
import duong.tieu.vdmproject.models.Models;
import duong.tieu.vdmproject.services.MyServices;

/**
 * Created by duong on 06/04/2016.
 */
public class InterestFragment extends Fragment {

    AdapterProject mAdapterProject;
    private ListView mLV_interst;
    private ArrayList<DGetProject> mListProject = new ArrayList<>();
    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_opp, container, false);
        mLV_interst = (ListView) mView.findViewById(R.id.lv_opp_fragment);

        mAdapterProject = new AdapterProject(getContext(), R.layout.row_item_opp, mListProject);
        mLV_interst.setAdapter(mAdapterProject);

        mLV_interst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "postion is: " + position, Toast.LENGTH_SHORT).show();
            }
        });

        new GetProject().execute(Models.URL_GET_PROJECT_CARE, "admin");


        return mView;
    }

    private class GetProject extends AsyncTask<String, String, String> {

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
            mListProject.clear();
            mListProject.addAll(mGetProject.getData());
            mAdapterProject.notifyDataSetChanged();
        }
    }

}

