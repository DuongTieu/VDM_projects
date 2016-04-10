package duong.tieu.vdmproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import duong.tieu.vdmproject.R;

/**
 * Created by Elitebook on 4/9/2016.
 */
public class ViewItemNewActivity extends AppCompatActivity {

    private TextView _tv_title;
    private TextView _tv_intro;
    private TextView _tv_content;
    private TextView _tv_count_like;
    private TextView _tv_count_comment;
    private ImageView _img_picture;
    private LinearLayout _bnt_like;
    private LinearLayout _bnt_comment;
    private LinearLayout _bnt_more;
    private LinearLayout bnt_like;
    private LinearLayout bnt_comment;
    private LinearLayout bnt_more;
    private LinearLayout mLayout2;

    private Bundle mBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.row_item_news);

        _tv_title = (TextView)  findViewById(R.id.tv_item_row_title);
        _tv_intro = (TextView)  findViewById(R.id.tv_row_item_intro);
        _tv_content = (TextView)  findViewById(R.id.tv_item_row_content);
        _tv_count_like = (TextView) findViewById(R.id.tv_item_row_count_like);
        _tv_count_comment = (TextView)findViewById(R.id.tv_item_row_count_comment);

        _img_picture = (ImageView)  findViewById(R.id.img_item_row_picture);

        bnt_like = (LinearLayout) findViewById(R.id.btn_item_row_like);
        bnt_comment = (LinearLayout)  findViewById(R.id.btn_item_row_comment);
        bnt_more = (LinearLayout) findViewById(R.id.btn_item_row_more);
        mLayout2 = (LinearLayout) findViewById(R.id.layout2);

        Intent i = getIntent();
        mBundle = i.getBundleExtra("news");
        if(mBundle != null) {

            _tv_content.setText(mBundle.getString("title"));
            _tv_intro.setText(mBundle.getString("intro"));
            _tv_title.setText(mBundle.getString("id"));

//            Log.v("user_name", mBundle.getString("id"));
//            Log.v("car_id", mBundle.getString("car_id"));
//            Log.v("intro", mBundle.getString("intro"));
//            Log.v("contents", mBundle.getString("content"));
//            Log.v("like", mBundle.getString("like"));
//            Log.v("ublike", mBundle.getString("unlike"));
//            Log.v("date", mBundle.getString("date"));

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent goToNewActivity = new Intent(getBaseContext(), LayoutNewsActivity.class);
        finish();
    }
}
