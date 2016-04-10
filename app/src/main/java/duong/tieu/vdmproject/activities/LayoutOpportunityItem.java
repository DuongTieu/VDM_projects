package duong.tieu.vdmproject.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import duong.tieu.vdmproject.R;

/**
 * Created by Elitebook on 4/10/2016.
 */
public class LayoutOpportunityItem extends AppCompatActivity {

    @Bind(R.id.tv_activity_opportunity_item_user_name) TextView mTitle;
    @Bind(R.id.tv_activity_opportunity_item_time)   TextView mDate;
    @Bind(R.id.tv_activity_opportunity_content)     TextView mContent;
    @Bind(R.id.btn_opp_activity_care)   LinearLayout mBtncare;
    @Bind(R.id.btn_opp_activity_cancel) LinearLayout mBtnCancel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opportunity_item);
        ButterKnife.bind(this);



        getData();

        mBtncare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showlog("đã bấm vào nút quan tâm");

            }
        });
    }

    private void showlog(String s) {
        Toast.makeText(getBaseContext(), s , Toast.LENGTH_SHORT).show();
    }

    private void getData(){
        Bundle bundle  = getIntent().getBundleExtra("fromOppFragment");
        if(bundle != null){
            mTitle.setText(bundle.getString("userName"));
            mContent.setText(bundle.getString("content"));
            mDate.setText(bundle.getString("time"));
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
