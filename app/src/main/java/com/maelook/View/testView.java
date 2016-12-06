package com.maelook.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.maelook.R;

public class testView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyAppCompat);
        setContentView(R.layout.colorrenderinglayout);

//        float[] spc = new float[440];
//
//        ListV]iew list = (ListView) findViewById(R.id.summury_page_list);
//        ArrayList<singleResult> data = new ArrayList<>();
//        singleResult a1 = new singleResult("Foot Candle","53.4 fc");
//        singleResult a2 = new singleResult("p","540 nm");
//        singleResult a3 = new singleResult("D","519 nm");
//        singleResult a4 = new singleResult("Purity(Pe)","6%");
//        singleResult a5 = new singleResult("Duv","0.0175");
//        singleResult a6 = new singleResult("SP Ratio","2.1");
//        singleResult a7 = new singleResult("GAI","69.2");
//        data.add(a1);
//        data.add(a2);
//        data.add(a3);
//        data.add(a4);
//        data.add(a5);
//        data.add(a6);
//        data.add(a7);
//        summuryPageListViewAdapter adapter = new summuryPageListViewAdapter(data,this);
//        list.setAdapter(adapter);
//
//        File file = new File(appDocument+File.separator+"data.txt");
//        try {
//            FileInputStream in = new FileInputStream(file);
//            BufferedReader br = new BufferedReader(new InputStreamReader(in));
//            String line = "";
//            int i = 0;
//            while( (line = br.readLine() ) != null){
//                spc[i++] = Float.parseFloat(line);
//                Log.e("TAG",""+spc[i-1]);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        spactrum write = new spactrum(spc);
//        new recordToText().write(write.toString());
//
//        spactrum test = new spactrum(write.paresingSpactrals(write.toString()));
//        try {
//            new Thread().sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        new recordToText().write(test.toString());


    }
}
