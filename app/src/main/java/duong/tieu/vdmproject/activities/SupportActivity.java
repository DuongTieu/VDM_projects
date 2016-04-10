package duong.tieu.vdmproject.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import duong.tieu.vdmproject.R;
import duong.tieu.vdmproject.adapter.SupportAdapter;
import duong.tieu.vdmproject.models.DSupport;
import duong.tieu.vdmproject.models.MSupport;
import duong.tieu.vdmproject.models.Models;
import duong.tieu.vdmproject.services.MyServices;

/**
 * Created by Elitebook on 4/10/2016.
 */
public class SupportActivity extends AppCompatActivity{


    private List<DSupport> mListView = new ArrayList<>();
    private SupportAdapter mSupportAdapter;
    @Bind(R.id.lv_support)    ListView mListViewSupport;
    private ProgressDialog mProgressDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        ButterKnife.bind(this);


        initWidgets();
        showProgressDialog();
        new GetSupport().execute(Models.URL_GET_SUPPORT);
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1000);
                hideProgressDialog();
            }
        }).start();

    }

    private void initWidgets() {
        mSupportAdapter = new SupportAdapter(this, R.layout.row_item_news, mListView);
        mListViewSupport.setAdapter(mSupportAdapter);
    }


    private class GetSupport extends AsyncTask<String, String, String> {

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
            MSupport mSupport = new Gson().fromJson(s, MSupport.class);
            mListView.clear();
            mListView.addAll(mSupport.getData());
            mSupportAdapter.notifyDataSetChanged();
            Log.i("Tag", mListView.size() + "");
        }
    }

    private void showProgressDialog(){
        if (mProgressDialog == null){
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setTitle("Loading");
            mProgressDialog.setMessage("Wait Loading data");
        }
        mProgressDialog.show();
    }

    private void hideProgressDialog(){
        if (mProgressDialog != null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }


}
