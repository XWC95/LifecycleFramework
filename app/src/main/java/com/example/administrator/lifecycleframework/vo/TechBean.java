package com.example.administrator.lifecycleframework.vo;

import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;


/**
 * Created by Administrator on 2018/1/2.
 */
@Entity(primaryKeys = {"_id"})
public class TechBean {
    @NonNull
    public String _id;
    public String createdAt;
    public String desc;
    public String publishedAt;
    public String source;
    public String type;
    public String url;
    public boolean used;
    public String who;
    public int page;
}
