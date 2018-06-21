package com.omar.mvvm.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.omar.mvvm.BuildConfig;
import com.omar.mvvm.data.AppDataManager;
import com.omar.mvvm.data.DataManager;
import com.omar.mvvm.data.local.db.AppDatabase;
import com.omar.mvvm.data.local.db.AppDbHelper;
import com.omar.mvvm.data.local.db.DbHelper;
import com.omar.mvvm.data.local.prefs.PreferenceHelper;
import com.omar.mvvm.data.local.prefs.SharedPreferenceUtils;
import com.omar.mvvm.di.ApiInfo;
import com.omar.mvvm.di.ApplicationContext;
import com.omar.mvvm.di.DatabaseInfo;
import com.omar.mvvm.di.PreferenceInfo;
import com.omar.mvvm.utils.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module(includes = ApiServiceModule.class)
public class ApplicationModule {

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Application context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName).build();
    }

    @Provides
    @Singleton
    PreferenceHelper providePreferencesHelper(SharedPreferenceUtils appPreferencesHelper) {
        return appPreferencesHelper;
    }


    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }


    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }


    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }
}
