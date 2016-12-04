package com.maelook.Widget.maeChartFragment.testFragment;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.maelook.Adapter.summuryPageListViewAdapter;
import com.maelook.Bean.singleResult;
import com.maelook.R;

import java.util.ArrayList;

/**
 * Created by Andrew on 2016/11/8.
 */

public class summuryPage extends Activity{

    public summuryPage() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summury_page_listview);

        ListView list = (ListView) findViewById(R.id.summury_page_list);
        ArrayList<singleResult> data = new ArrayList<>();
        singleResult a1 = new singleResult("illumiance","574 lux");
        singleResult a2 = new singleResult("Foot Candle","53.4 fc");
        singleResult a3 = new singleResult("~p","540 nm");
        singleResult a4 = new singleResult("~D","519 nm");
        singleResult a5 = new singleResult("Purity(Pe)","6 %");
        singleResult a6 = new singleResult("Duv","0.0175");
        singleResult a7 = new singleResult("SP Ratio","2.1");
        singleResult a8 = new singleResult("PPFD(380-780nm)","8.13 UMOL/M^2S");
        singleResult a9 = new singleResult("GAI","69.2");

        data.add(a1);
        data.add(a2);
        data.add(a3);
        data.add(a4);
        data.add(a5);
        data.add(a6);
        data.add(a7);
        data.add(a8);
        data.add(a9);

        summuryPageListViewAdapter adapter = new summuryPageListViewAdapter(data ,this);
        list.setAdapter(adapter);

    }
}
