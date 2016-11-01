package com.example;

import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class maeGenerator {

    public static void main(String[] args) throws Exception{
        Schema schema = new Schema(1,"com.maelook.dao");

        addSingleRecord(schema);
    }

    private static void addSingleRecord(Schema schema) {
        Entity test = schema.addEntity("test");
        test.addIdProperty();
        test.addStringProperty("name").notNull();
    }


}
