package dagger;


import codeproject.sample.com.demotutorials.HomeActivity;
import dagger.modules.AppModule;
import dagger.modules.NetworkModule;
import framework.core.CoreModule;
import framework.core.RetrofitActivityModule;
import service.core.AppApiService;

@Component(modules = {
        AppModule.class,
        NetworkModule.class
})
public interface ApplicationComponent {
    void inject(RetrofitActivityModule module);

    void inject(AppApiService appApiService);

//    void inject(HomeActivity activity);
}
