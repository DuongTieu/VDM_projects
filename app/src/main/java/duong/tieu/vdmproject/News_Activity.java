package duong.tieu.vdmproject;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by duong on 06/04/2016.
 */
public class News_Activity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.news_activity);


        Bundle get_user_position = getIntent().getExtras();

        Toast.makeText(News_Activity.this, "   " + get_user_position.getInt("position"), Toast.LENGTH_SHORT).show();
        Log.d("aaaa", " "+ get_user_position.getInt("position"));
    }
}
