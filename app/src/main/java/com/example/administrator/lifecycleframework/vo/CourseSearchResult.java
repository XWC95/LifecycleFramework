package com.example.administrator.lifecycleframework.vo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.administrator.lifecycleframework.db.ProjectTypeConverters;

import java.util.List;

@Entity(primaryKeys = {"token"})
@TypeConverters(ProjectTypeConverters.class)
public class CourseSearchResult {
    @NonNull
    public final String token;
    public final List<String> repoIds;
    public final int totalCount;
    @Nullable
    public final Integer next;

    public CourseSearchResult(String token, List<String> repoIds, int totalCount,
                              Integer next) {
        this.token = token;
        this.repoIds = repoIds;
        this.totalCount = totalCount;
        this.next = next;
    }
}