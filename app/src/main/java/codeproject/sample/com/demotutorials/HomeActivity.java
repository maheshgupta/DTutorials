package codeproject.sample.com.demotutorials;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import java.util.List;

import framework.DemoApplication;
import framework.core.BaseActivity;
import model.service.jsonplaceholder.user.User;
import service.core.ServiceResponseListener;

public class HomeActivity extends BaseActivity {

    @Override
    public int getContentViewID() {
        return R.layout.activity_home;
    }

    @Override
    public String getLogTag() {
        return "HomeActivity";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((DemoApplication) getApplication()).getApplicationComponent().inject(this);
    }

    public void showAlbumsActivity(View view) {
        Intent intent = new Intent(this, AlbumsActivity.class);
        startActivity(intent);
    }

    private void test() {
        if (getUserServiceWrapper() != null) {
            this.getUserServiceWrapper().getUsers(new ServiceResponseListener<List<User>>() {
                @Override
                public void onSuccess(List<User> response) {
                    Log.i(TAG, "onSuccess: Reponse" + response.toString());
                }

                @Override
                public void onError(String errMessage) {
                    Log.e(TAG, "onError: " + errMessage);
                }
            });
        } else {
            Log.e(TAG, "test: No User Services Injected");
        }
    }

}
