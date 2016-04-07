package duong.tieu.vdmproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by duong on 06/04/2016.
 */
public class News_Activity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);


        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("user_position");

        Toast.makeText(getApplicationContext(), "   " + bundle.getInt("position"), Toast.LENGTH_SHORT).show();
        Log.d("aaaa", " "+ bundle.getInt("position"));
    }
}
