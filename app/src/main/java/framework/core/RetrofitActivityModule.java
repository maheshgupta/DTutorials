package framework.core;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import framework.DemoApplication;
import service.retrofit.jsonplaceholder.wrapper.UsersServiceWrapper;


public class RetrofitActivityModule extends AppCompatActivity {

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    UsersServiceWrapper usersServiceWrapper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.addInjections();
    }


    private void addInjections() {
        ((DemoApplication) getApplication()).getApplicationComponent().inject(this);
    }



    public SharedPreferences getSharedPreferencesModule(){
        return this.sharedPreferences;
    }

    public UsersServiceWrapper getUserServiceWrapper() {
        return this.usersServiceWrapper;
    }

}
