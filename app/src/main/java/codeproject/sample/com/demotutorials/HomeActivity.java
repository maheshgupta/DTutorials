package codeproject.sample.com.demotutorials;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import framework.DemoApplication;
import framework.core.BaseActivity;
import model.service.jsonplaceholder.user.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import service.core.ServiceResponseListener;
import service.retrofit.jsonplaceholder.services.UsersService;
import service.retrofit.jsonplaceholder.wrapper.UsersServiceWrapper;

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
