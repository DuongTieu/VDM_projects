package duong.tieu.vdmproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.Bind;
import duong.tieu.vdmproject.R;

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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.see_comment_activity);

//        TODO
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
