package duong.tieu.vdmproject.Message;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import duong.tieu.vdmproject.R;

/**
 * Created by duong on 07/04/2016.
 */
public class Send_Mess_Activity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_mess_activity);

        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("user_position");

        Toast.makeText(Send_Mess_Activity.this, "dsfnds " + bundle.getInt("position"), Toast.LENGTH_SHORT).show();
    }
}
