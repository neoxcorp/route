package com.route;

import android.app.Application;
import android.content.Context;

import com.route.dagger.components.ApplicationComponent;
import com.route.dagger.components.DaggerApplicationComponent;
import com.route.dagger.modules.ApplicationModule;

public class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        this.applicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

    public static AndroidApplication get(Context context) {
        return (AndroidApplication) context.getApplicationContext();
    }

}
