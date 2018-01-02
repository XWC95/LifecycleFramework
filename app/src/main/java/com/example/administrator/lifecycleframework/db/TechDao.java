package com.example.administrator.lifecycleframework.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.administrator.lifecycleframework.vo.TechBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/2.
 */
@Dao
public abstract class TechDao {


    @Query("SELECT * FROM TechBean WHERE page = :page")
    public abstract LiveData<List<TechBean>> findTechList(int page);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertTechs(List<TechBean> repositories);

}
