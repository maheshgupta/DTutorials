package service.core;

import android.app.Application;
import android.content.Context;

import javax.inject.Inject;

import dagger.ApplicationComponent;
import framework.DemoApplication;
import retrofit2.Retrofit;

public class AppApiService {


    @Inject
    Retrofit retrofit;

    public AppApiService(Context context) {
        ApplicationComponent applicationComponent = ((DemoApplication) (context.getApplicationContext())).getApplicationComponent();
        applicationComponent.inject(this);
    }


    protected Retrofit getRetrofit() {
        return retrofit;
    }
}
