package duong.tieu.vdmproject.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import duong.tieu.vdmproject.R;
import duong.tieu.vdmproject.adapter.SeeCommentAdapter;
import duong.tieu.vdmproject.models.SeeCommentEle;

/**
 * Created by duong on 14/04/2016.
 */
public class SeeCommentActivity extends AppCompatActivity {


    @Bind(R.id.tv_see_comment_activity)
    TextView tv_see_comment_activity;

    @Bind(R.id.tv_content_see_comment)
    TextView tv_content_see_comment;

    @Bind(R.id.lv_see_comment_activity)
    ListView lv_see_comment_activity;

    @Bind(R.id.edt_see_comment_activity)
    EditText edt_see_comment_activity;

    @Bind(R.id.btn_send_see_comment)
    Button btn_send_see_comment;

    //    sub commetn
    String arrSub[] = {"zsdf", "sdf", "csfsdf"};
    int arrsubAva[] = {R.drawable.a, R.drawable.e, R.drawable.b};
    List<SeeCommentEle> obj;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.see_comment_activity);

        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("comment");
        if (bundle != null) {
            tv_content_see_comment.setText(bundle.getString("userName") + "Đã trả lời một comment");
            tv_content_see_comment.setText(bundle.getString("content"));
        }

//        nếu ko có comment thì listview sub commnet sẽ ẩn

        if (arrSub != null) {
            for (int ii = 0; ii < arrSub.length; ii++) {
                SeeCommentEle item = new SeeCommentEle();
                item.setUserAvaResouce(arrsubAva[ii]);
                item.setUserText(arrSub[ii]);

                obj.add(item);
            }

            SeeCommentAdapter adapter = new SeeCommentAdapter(getApplicationContext(), R.layout.item_row_see_comment_activity, obj);
            lv_see_comment_activity.setAdapter(adapter);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                lv_see_comment_activity.setForegroundGravity(View.GONE);
            }
        }

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //go to main activity

        Intent goMain = new Intent(getBaseContext(), MainActivity.class);
        startActivity(goMain);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
