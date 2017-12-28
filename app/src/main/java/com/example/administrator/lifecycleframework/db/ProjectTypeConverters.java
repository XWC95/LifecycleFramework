package com.example.administrator.lifecycleframework.db;

import android.arch.persistence.room.TypeConverter;

import java.util.ArrayList;
import java.util.List;

public class ProjectTypeConverters {
    @TypeConverter
    public static List<String> stringToStringList(String data) {
        String[] split = data.split(",");
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            datas.add(split[i]);
        }
        return datas;
    }

    @TypeConverter
    public static String stringListToString(List<String> ints) {
        String s = "";
        for (String str : ints) {
            s += str + ",";
        }
        return s;
    }


}