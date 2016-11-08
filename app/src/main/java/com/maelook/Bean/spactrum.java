package com.maelook.Bean;

/**
 * Created by Andrew on 2016/11/5.
 */


//TODO 写关于光谱数据内容的解析和注释
public class spactrum {

    private float[] spactrals;

    public spactrum(float[] spactrals) {
        this.spactrals = spactrals;
    }

    @Override
    public String toString() {
        String res = "";
        for(float a:spactrals){
            res = res+ a +"\n";
        }
        return res;
    }

    public float[] paresingSpactrals(String s){
        float[] res = new float[440];
        int[] flag = new int[440];
        int j = 0;
        byte[] source = s.getBytes();
        for(int i=0;i<source.length;i++){
            if (source[i] == '\n'){
                flag[j++] = i;
            }
        }

        for (int i=0;i<res.length;i++){
            byte[] a = null;
            int length;
            if (i==0) {
                length = flag[i];
                a = new byte[length];

                System.arraycopy(source,0,a,0,length);
                res[i] = Float.parseFloat(new String(a));
                continue;
            }else{
                length = flag[i] - flag[i-1] - 1;
                a = new byte[length];
            }
            System.arraycopy(source,flag[i-1]+1,a,0,length);
            res[i] = Float.parseFloat(new String(a));
        }
        return res;
    }

}
