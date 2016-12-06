package com.maelook.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.maelook.Adapter.dataComparedAdapter;
import com.maelook.R;

public class dataComparedActivity extends AppCompatActivity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_compared);

        this.list = (ListView) findViewById(R.id.View_List);
        this.list.setAdapter(new dataComparedAdapter(this));



    }
}
