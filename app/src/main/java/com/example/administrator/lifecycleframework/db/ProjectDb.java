package com.example.administrator.lifecycleframework.db;

/**
 * Created by Administrator on 2017/9/13.
 */

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.administrator.lifecycleframework.vo.TechBean;


/**
 * Main database description.
 */
@Database(entities = {TechBean.class}, version = 5, exportSchema = false)
public abstract class ProjectDb extends RoomDatabase {

    abstract public TechDao techDao();

    abstract public UserDao userDao();
}
