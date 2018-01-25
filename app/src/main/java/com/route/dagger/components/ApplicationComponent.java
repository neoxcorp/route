package com.route.dagger.components;

import android.content.Context;

import com.route.AndroidApplication;
import com.route.dagger.modules.ApplicationModule;
import com.route.dagger.modules.RestApiModule;
import com.route.view.activities.MainActivity;
import com.route.view.activities.PlacesActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton
@Component(modules = {ApplicationModule.class, RestApiModule.class})
public interface ApplicationComponent {

    void inject(AndroidApplication application);

    void inject(MainActivity activity);
    void inject(PlacesActivity activity);

    //Exposed to sub-graphs.
    Context context();
}

