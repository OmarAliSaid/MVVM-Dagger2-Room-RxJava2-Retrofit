package com.omar.mvvm.di.component;

import android.app.Application;

import com.omar.mvvm.MvvmExampleApp;
import com.omar.mvvm.di.builder.ActivityBuilder;
import com.omar.mvvm.di.module.ApiServiceModule;
import com.omar.mvvm.di.module.ApplicationModule;
import com.omar.mvvm.di.module.OkHttpClientModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {ApplicationModule.class ,  AndroidInjectionModule.class , ActivityBuilder.class})
public interface ApplicationComponent {

    void inject(MvvmExampleApp daggerExampleApp);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        Builder AppModule(ApplicationModule module);

        Builder ApiService(ApiServiceModule module);

        Builder httpModule(OkHttpClientModule module);

        ApplicationComponent build();
    }
}
