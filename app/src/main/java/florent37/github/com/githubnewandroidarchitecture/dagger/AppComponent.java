package florent37.github.com.githubnewandroidarchitecture.dagger;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Component;
import florent37.github.com.githubnewandroidarchitecture.MainApplication;
import florent37.github.com.githubnewandroidarchitecture.MainFragment;
import florent37.github.com.githubnewandroidarchitecture.viewmodel.ReposListViewModel;
import florent37.github.com.githubnewandroidarchitecture.viewmodel.UserViewModel;

/**
 * Created by florentchampigny on 18/05/2017.
 */

@Component(modules = AppModule.class)
@Singleton
public abstract class AppComponent {

    public static AppComponent from(@NonNull Context context){
        return ((MainApplication) context.getApplicationContext()).getAppComponent();
    }

    public abstract void inject(MainFragment mainFragment);
}
