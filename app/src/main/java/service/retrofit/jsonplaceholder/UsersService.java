package service.retrofit.jsonplaceholder;


import java.util.List;

import model.service.jsonplaceholder.user.User;
import retrofit2.Call;
import retrofit2.http.GET;

public interface UsersService {

    @GET("users")
    Call<List<User>> getUsers();

}
