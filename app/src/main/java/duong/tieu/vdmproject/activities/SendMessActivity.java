package duong.tieu.vdmproject.activities;

/**
 * Created by duong on 07/04/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import duong.tieu.vdmproject.R;

/**
 * Created by duong on 07/04/2016.
 */
public class SendMessActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mess);

        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("user_position");

        Toast.makeText(SendMessActivity.this, "dsfnds " + bundle.getInt("position"), Toast.LENGTH_SHORT).show();
    }
}