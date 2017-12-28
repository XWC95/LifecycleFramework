package com.example.administrator.lifecycleframework.db;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * Created by Administrator on 2017/12/4.
 */

public class DbHelper {

    private static ProjectDb db;

    public static ProjectDb getDb(Context ctx) {
        if (db == null) {
            db = Room.databaseBuilder(ctx, ProjectDb.class, "ProjectDb.db").build();
        }
        return db;
    }

}
