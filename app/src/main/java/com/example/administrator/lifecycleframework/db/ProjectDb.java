package com.example.administrator.lifecycleframework.db;

/**
 * Created by Administrator on 2017/9/13.
 */

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.administrator.lifecycleframework.vo.CourseSearchResult;


/**
 * Main database description.
 */
@Database(entities = {CourseSearchResult.class}, version = 5,exportSchema = false)
public abstract class ProjectDb extends RoomDatabase {

    abstract public HomeDao homeDao();

    abstract public UserDao userDao();
}
