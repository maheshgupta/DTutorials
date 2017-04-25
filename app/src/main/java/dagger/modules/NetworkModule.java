package dagger.modules;


import android.content.Context;

import javax.inject.Singleton;

import constants.AppConstants;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import service.retrofit.jsonplaceholder.wrapper.PhotosServiceWrapper;
import service.retrofit.jsonplaceholder.wrapper.UsersServiceWrapper;

@Module
public class NetworkModule {

    @Provides
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder().build();
    }

    @Provides
    public Retrofit providesRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.JSON_PLACEHOLDER_SERVER_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }


    @Provides
    public UsersServiceWrapper providesUsersSerivceWrapper(Context context) {
        return new UsersServiceWrapper(context);
    }

    @Provides
    public PhotosServiceWrapper providesPhotosServiceWrapper(Context context) {
        return new PhotosServiceWrapper(context);
    }

}
