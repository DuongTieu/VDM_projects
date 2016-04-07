package duong.tieu.vdmproject.Message;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import duong.tieu.vdmproject.R;

/**
 * Created by duong on 06/04/2016.
 */
public class Mess_Fragment extends Fragment {

    private ListView lv_messae;

    public Mess_Fragment() {
        // Required empty public constructor
    }

    String [] userName = {"a", "b","c","d","e","f","g","h","k"};
    String [] userMess = {"1", "2","3","4","5","6","7","8","9"};
//    int defaulUserAva = R.drawable.g;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.mess_fragment, container, false);
        List<MessEle> mListMessEle = new ArrayList<MessEle>();

        for(int i = 0;  i <userMess.length; i++){
            MessEle item = new MessEle();
            item.setUserName(userName[i]);
            item.setUserText(userMess[i]);

            mListMessEle.add(item);
        }

        lv_messae = (ListView) view.findViewById(R.id.lv_message);
        MessAdapter adapter = new MessAdapter(getContext(), R.layout.mess_row_item, mListMessEle);
        lv_messae.setAdapter(adapter);

        lv_messae.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent send_mess = new Intent(getActivity().getBaseContext(), Send_Mess_Activity.class);
                Bundle data = new Bundle();
                data.putInt("position", position);
                send_mess.putExtra("user_position", data);
                startActivity(send_mess);

            }
        });
        return view;

    }
}