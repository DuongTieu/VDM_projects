package duong.tieu.vdmproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import duong.tieu.vdmproject.R;

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
        if(!bundle.isEmpty()) {
            Toast.makeText(getApplicationContext(), "   " + bundle.getInt("position"), Toast.LENGTH_SHORT).show();
            Log.d("aaaa", " " + bundle.getInt("position"));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent main = new Intent(getBaseContext(), MainActivity.class);
        startActivity(main);
    }
}
