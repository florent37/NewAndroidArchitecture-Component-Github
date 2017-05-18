package florent37.github.com.githubnewandroidarchitecture;

import android.app.Application;

import florent37.github.com.githubnewandroidarchitecture.dagger.AppComponent;
import florent37.github.com.githubnewandroidarchitecture.dagger.AppModule;
import florent37.github.com.githubnewandroidarchitecture.dagger.DaggerAppComponent;

/**
 * Created by florentchampigny on 18/05/2017.
 */

public class MainApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        this.appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
