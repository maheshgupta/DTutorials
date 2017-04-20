package framework;

import android.app.Application;

import dagger.ApplicationComponent;
import dagger.DaggerApplicationComponent;
import dagger.modules.AppModule;
import dagger.modules.NetworkModule;


public class DemoApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initDagger();
    }

    private void initDagger() {
        this.applicationComponent = DaggerApplicationComponent.builder().
                appModule(new AppModule(this)).
                networkModule(new NetworkModule()).
                build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }


}
