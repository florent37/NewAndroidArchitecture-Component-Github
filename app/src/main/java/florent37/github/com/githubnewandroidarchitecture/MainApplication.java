package florent37.github.com.githubnewandroidarchitecture;

import android.app.Application;

import com.facebook.stetho.Stetho;

import javax.inject.Inject;

import florent37.github.com.githubnewandroidarchitecture.dagger.AppComponent;
import florent37.github.com.githubnewandroidarchitecture.dagger.AppModule;
import florent37.github.com.githubnewandroidarchitecture.dagger.DaggerAppComponent;
import florent37.github.com.githubnewandroidarchitecture.dagger.RoomModule;
import florent37.github.com.githubnewandroidarchitecture.local.UserDao;
import florent37.github.com.githubnewandroidarchitecture.model.UserLocal;

/**
 * Created by florentchampigny on 18/05/2017.
 */

public class MainApplication extends Application {

    private AppComponent appComponent;

    @Inject
    UserDao userDao;

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);

        this.appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .roomModule(new RoomModule(this))
                .build();

        appComponent.inject(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                //called first time created
                userDao.deleteAll();
                userDao.insert(new UserLocal("kevin", "kévin", ""));
                userDao.insert(new UserLocal("florent37", "flo", ""));
            }
        }).start();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
