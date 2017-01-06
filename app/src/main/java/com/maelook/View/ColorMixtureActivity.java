package com.maelook.View;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.maelook.R;
import com.maelook.Widget.maeChartFragment.colorMixView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.maelook.app.maelookApp.appDocument;

public class ColorMixtureActivity extends Activity {
    private colorMixView fog;
    private TextView show;
    private int w;
    private int b;
    private int g;
    private int r;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_mixture);
        fog = (colorMixView) findViewById(R.id.fog);
        show = (TextView) findViewById(R.id.show);
        Button res = (Button) findViewById(R.id.res);
        double[] data = new double[401];

        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r = fog.getR();
                g = fog.getG();
                b = fog.getB();
                w = fog.getA();
                show.setBackgroundColor(Color.argb(w,r,g,b));

            }
        });

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(appDocument+ File.separator+"data1.txt")));
            String line = "";
            int i= 0;
            while((line = reader.readLine()) != null){
                data[i] = Float.parseFloat(line);

                i++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int blendColor(int fg, int bg) {
        int scr = Color.red(fg);
        int scg = Color.green(fg);
        int scb = Color.blue(fg);
        int sa = fg >>> 24;
        int dcr = Color.red(bg);
        int dcg = Color.green(bg);
        int dcb = Color.blue(bg);
        int color_r = dcr * (0xff - sa) / 0xff + scr * sa / 0xff;
        int color_g = dcg * (0xff - sa) / 0xff + scg * sa / 0xff;
        int color_b = dcb * (0xff - sa) / 0xff + scb * sa / 0xff;
        return ((color_r << 16) + (color_g << 8) + color_b) | (0xff000000);
    }

    public void setBitmap(Bitmap b) {



    }
    public void mix_back(View view){
        finish();
    }
    public void mix_home_datamap(View view){
        Intent intent=new Intent(ColorMixtureActivity.this,FirstActivity.class);
        startActivity(intent);
    }
}
