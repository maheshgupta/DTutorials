package service.retrofit.jsonplaceholder.wrapper;


import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import model.service.jsonplaceholder.user.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import service.core.AppApiService;
import service.core.ServiceResponseListener;
import service.retrofit.jsonplaceholder.services.UsersService;

public class UsersServiceWrapper extends AppApiService {

    public UsersServiceWrapper(Context context) {
        super(context);
    }


    public void getUsers(@NonNull final ServiceResponseListener<List<User>> listener) {
        UsersService userService = getRetrofit().create(UsersService.class);
        Call<List<User>> response = userService.getUsers();
        response.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }
}
