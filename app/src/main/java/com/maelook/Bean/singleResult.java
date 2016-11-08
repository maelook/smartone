package com.maelook.Bean;

/**
 * Created by Andrew on 2016/11/5.
 */


//summurypage中single Result的类

public class singleResult {

    private String name;
    private String result;

    public singleResult() {
    }

    public singleResult(String name, String result) {
        this.name = name;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public singleResult setName(String name) {
        this.name = name;
        return this;
    }

    public String getResult() {
        return result;
    }

    public singleResult setResult(String result) {
        this.result = result;
        return this;
    }
}
