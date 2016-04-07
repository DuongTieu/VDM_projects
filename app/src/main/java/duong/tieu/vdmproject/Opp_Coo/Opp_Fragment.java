package duong.tieu.vdmproject.Opp_Coo;

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

import duong.tieu.vdmproject.Core.Opp_Adapter;
import duong.tieu.vdmproject.News_Activity;
import duong.tieu.vdmproject.R;

/**
 * Created by duong on 06/04/2016.
 */
public class Opp_Fragment extends Fragment {

    String [] contents = {"duong","afaaaa","aaafaa","aaaa4a","aaaaad"};
    int [] img = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e };
    String [] minute = {"1", "2", "3", "4", "5"};
    String [] name = {"Duyen", "Duyen",  "Trang",  "Van",  "Mai" };

    ListView lv_opp;


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.opp_fragment, container, false);
        List<OppEle> listOpp = new ArrayList<>();

        for(int i = 0; i < name.length; i++){
            OppEle item = new OppEle();

            item.setName(name[i]);
            item.setContents(contents[i]);
            item.setMinute(minute[i]);
            item.setAva(img[i]);

            listOpp.add(item);

        }

        lv_opp = (ListView) view.findViewById(R.id.lv_opp_fragment);
        Opp_Adapter adapter = new Opp_Adapter(getContext(), R.layout.opp_row_item, listOpp);
        lv_opp.setAdapter(adapter);

        lv_opp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //go to news activity
                Intent senData = new Intent(getActivity(), News_Activity.class);
                Bundle data = new Bundle();
                data.putInt("position", position);
                senData.putExtra("user_position", data);
                startActivity(senData);

            }
        });

        return view;
    }
}
