package framework.core;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import framework.DemoApplication;
import retrofit2.Retrofit;
import service.retrofit.jsonplaceholder.wrapper.UsersServiceWrapper;

public class CoreModule extends RetrofitActivityModule {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void writeToSharedPreferences(@NonNull String pKey, String value) {
        SharedPreferences.Editor editor = getSharedPreferencesModule().edit();
        editor.putString(pKey, value);
        editor.commit();
    }


    public String getFromSharedPreferences(@NonNull String pKey, String pDefaultValue) {
        return this.getSharedPreferencesModule().getString(pKey, pDefaultValue);
    }


}
