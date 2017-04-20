package codeproject.sample.com.demotutorials;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import framework.core.BaseActivity;
import model.service.jsonplaceholder.user.User;
import retrofit2.Call;
import retrofit2.Retrofit;
import service.retrofit.jsonplaceholder.UsersService;

public class HomeActivity extends BaseActivity {

    @Inject
    Retrofit retrofit;

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
        this.test();
    }


    private void test() {
        UsersService userService = retrofit.create(UsersService.class);
        Call<List<User>> response = userService.getUsers();
        Log.i(TAG, "test: ");
    }

}
