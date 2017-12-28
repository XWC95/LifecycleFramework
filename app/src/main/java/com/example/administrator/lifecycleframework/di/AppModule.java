
package com.example.administrator.lifecycleframework.di;

import android.app.Application;
import android.arch.persistence.room.Room;


import com.example.administrator.lifecycleframework.App;
import com.example.administrator.lifecycleframework.api.OnlyService;
import com.example.administrator.lifecycleframework.db.HomeDao;
import com.example.administrator.lifecycleframework.db.ProjectDb;
import com.example.administrator.lifecycleframework.db.UserDao;
import com.example.administrator.lifecycleframework.util.LiveDataCallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
class AppModule {

    @Singleton
    @Provides
    OnlyService provideGithubService() {
        return new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(OnlyService.class);
    }

    @Singleton
    @Provides
    ProjectDb provideDb(Application app) {
        return Room.databaseBuilder(app, ProjectDb.class, "ProjectDb.db").build();
    }

    @Singleton
    @Provides
    HomeDao provideHomeDao(ProjectDb db) {
        return db.homeDao();
    }


    @Singleton
    @Provides
    UserDao provideUserDao(ProjectDb db) {
        return db.userDao();
    }
}
