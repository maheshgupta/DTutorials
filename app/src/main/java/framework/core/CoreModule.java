package framework.core;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import framework.DemoApplication;

public class CoreModule extends AppCompatActivity {

    @Inject
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.addInjections();
    }

    private void addInjections() {
        ((DemoApplication) getApplication()).getApplicationComponent().inject(this);
    }


    public void writeToSharedPreferences(@NonNull String pKey, String value) {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putString(pKey, value);
        editor.commit();
    }


    public String getFromSharedPreferences(@NonNull String pKey, String pDefaultValue) {
        return this.sharedPreferences.getString(pKey, pDefaultValue);
    }


}
