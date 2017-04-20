package dagger;


import dagger.modules.AppModule;
import dagger.modules.NetworkModule;
import framework.core.CoreModule;

@Component(modules = {
        AppModule.class,
        NetworkModule.class
})
public interface ApplicationComponent {
    void inject(CoreModule module);
}
