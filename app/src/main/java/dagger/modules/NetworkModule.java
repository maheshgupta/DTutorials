package dagger.modules;


import javax.inject.Singleton;

import constants.AppConstants;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.http.POST;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public Retrofit providesRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.JSON_PLACEHOLDER_SERVER_BASEURL)
                .build();
        return retrofit;
    }

}
