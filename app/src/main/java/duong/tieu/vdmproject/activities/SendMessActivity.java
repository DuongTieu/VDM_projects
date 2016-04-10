package duong.tieu.vdmproject.activities;

/**
 * Created by duong on 07/04/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import duong.tieu.vdmproject.R;

/**
 * Created by duong on 07/04/2016.
 */
public class SendMessActivity extends AppCompatActivity {

    Toolbar mToolBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess);
        mToolBar = (Toolbar) findViewById(R.id.tbMessages);

        mToolBar.setTitle("Dương");
        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("message");
        if (!bundle.isEmpty()) {
            System.out.println("test" + "sdhmfb");
        }
    }
}