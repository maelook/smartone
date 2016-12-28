package com.maelook.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by andrew on 2016/12/27.
 */

public class dataBiulderAndPraser {

    private ArrayList<String> content;
    private String buffer;
    private ArrayList<double[]> data;

    public dataBiulderAndPraser() {
        this.buffer = "";
    }

    public ArrayList<double[]> pareserFromSQLMulti(String content) {
        this.content = divider(content);
        this.data = getDoubleArrays(this.content);
        return this.data;
    }

    public double[] praserFromSQLSingle(String content){
        String[] s = content.split(",");
        double[] res = new double[s.length];
        for (int i=0;i <res.length;i++){
            res[i] = Double.parseDouble(s[i]);
        }
        return res;
    }

    private ArrayList<String> divider(String temp) {
        String[] s = temp.split("#");
        ArrayList<String> res = new ArrayList<>();
        for (int i = 0; i < s.length; i++) {
            res.add(s[i]);
        }
        return res;
    }

    private ArrayList<double[]> getDoubleArrays(ArrayList<String> content) {
        String[] split = new String[0];
        ArrayList<double[]> res = new ArrayList<>();
        for (int i = 0; i < content.size(); i++) {
            split = content.get(i).split(",");
            double[] t = new double[split.length];
            for (int z = 0; z < split.length; z++) {
                t[z] = Double.parseDouble(split[z]);
            }
            res.add(t);
        }
        return res;
    }

    //用于拼接多组数据便于放入数据库的时候的使用
    public String builderContent(String s ,double[] array) {
        for (int i = 0; i < array.length; i++) {
            if (s == null){
                s = array[i] + ",";
                continue;
            }
            if (i == array.length - 1) {
                s = s + array[i] + "#";
                continue;
            }
            s = s + array[i] + ",";
        }
        return s;
    }

    public void dataToFile(ArrayList<double[]> arrayList, File file) {
        try {
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < arrayList.size(); i++) {
                for (int a = 0; a < arrayList.get(i).length; a++) {
                    writer.write("" + (a+380) + " " + arrayList.get(i)[a] + "\n");
                    writer.flush();
                }
                writer.write("__________next one__________\n");
                writer.flush();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dataToFile(double[] arrayList, File file) {
        try {
            FileWriter writer = new FileWriter(file);
            for (int a = 0; a < arrayList.length; a++) {
                writer.write("" + (a+380) + " " + arrayList[a] + "\n");
                writer.flush();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public double[] praserOneFromFile(File file){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String temp = "";
            int i=0;
            double[] res = new double[401];
            while((temp = reader.readLine()) != null){
                String t = temp.substring(4,temp.length());
                res[i++] = Double.parseDouble(t);
            }
            return res;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<double[]> praserMultiFromFile(File file){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String temp = "";
            int i=0;
            double[] resTemp = new double[401];
            ArrayList<double[]> array = new ArrayList<>();
            while((temp = reader.readLine()) != null){
                if (temp.equals("__________next one__________")){
                    array.add(resTemp);
                    resTemp = new double[401];
                    continue;
                }
                String t = temp.substring(4,temp.length());
                resTemp[i++] = Double.parseDouble(t);
            }
            return array;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
