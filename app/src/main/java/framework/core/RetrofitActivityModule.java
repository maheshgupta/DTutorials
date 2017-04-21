package framework.core;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import framework.DemoApplication;
import service.retrofit.jsonplaceholder.wrapper.PhotosServiceWrapper;
import service.retrofit.jsonplaceholder.wrapper.UsersServiceWrapper;


/**
 * Class, that provides the list of Inject modules from Dagger2 Injection Service.
 * This has the full list of Inject items and their getters so that sub-modules
 * can easily fetch the injected modules. This is made because Dagger2 does not provide
 * the injected modules to sub-classes.
 */

public class RetrofitActivityModule extends AppCompatActivity {

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    UsersServiceWrapper usersServiceWrapper;

    @Inject
    PhotosServiceWrapper photosServiceWrapper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.addInjections();
    }


    private void addInjections() {
        ((DemoApplication) getApplication()).getApplicationComponent().inject(this);
    }

    /**
     * Get a copy of the Shared Preferences.
     *
     * @return
     */
    public SharedPreferences getSharedPreferencesModule() {
        return this.sharedPreferences;
    }


    /**
     * Get the Copy of the Users Services Wrapper to connect REST Api.
     *
     * @return
     */
    public UsersServiceWrapper getUserServiceWrapper() {
        return this.usersServiceWrapper;
    }


    public PhotosServiceWrapper getPhotosServiceWrapper() {
        return this.photosServiceWrapper;
    }

}
