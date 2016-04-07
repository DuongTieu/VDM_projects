package duong.tieu.vdmproject.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import duong.tieu.vdmproject.R;
import duong.tieu.vdmproject.adapter.OppAdapter;
import duong.tieu.vdmproject.models.OppEle;

/**
 * Created by duong on 06/04/2016.
 */
public class InterestFragment extends Fragment {

    String [] contents = {"duong","afaaaa","aaafaa","aaaa4a","aaaaad"};
    int [] img = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e };
    String [] minute = {"1", "2", "3", "4", "5"};
    String [] name = {"Duyen", "Duyen",  "Trang",  "Van",  "Mai" };

    ListView lv_opp;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_opp, container, false);
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
        OppAdapter adapter = new OppAdapter(getContext(), R.layout.row_item_opp, listOpp);
        lv_opp.setAdapter(adapter);

        lv_opp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "postion is: " + position, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}

