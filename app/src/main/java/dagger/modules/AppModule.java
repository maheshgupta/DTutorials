package dagger.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import constants.AppConstants;
import dagger.Module;
import dagger.Provides;
import framework.Sample;

@Module
public class AppModule {

    private Application application;


    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    public Context provideContext() {
        return this.application;
    }

    @Provides
    public SharedPreferences providesSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

}
