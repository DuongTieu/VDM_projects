package duong.tieu.vdmproject.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import duong.tieu.vdmproject.R;

/**
 * Created by Elitebook on 4/10/2016.
 */
public class NotNow extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_now);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
