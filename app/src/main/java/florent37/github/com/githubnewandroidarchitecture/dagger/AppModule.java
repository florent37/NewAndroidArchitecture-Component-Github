package florent37.github.com.githubnewandroidarchitecture.dagger;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import florent37.github.com.githubnewandroidarchitecture.local.UserRoomDatabase;
import florent37.github.com.githubnewandroidarchitecture.model.UserLocal;
import florent37.github.com.githubnewandroidarchitecture.network.GithubAPI;
import florent37.github.com.githubnewandroidarchitecture.network.RepoRepositoryNetwork;
import florent37.github.com.githubnewandroidarchitecture.repository.RepoRepository;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by florentchampigny on 18/05/2017.
 */

@Module(includes = RoomModule.class)
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    public Context providesAppContext() {
        return application;
    }

    @Provides
    @Singleton
    public GithubAPI providesGithubApi() {
        return new Retrofit.Builder()
                .baseUrl(GithubAPI.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubAPI.class);
    }

    @Provides
    @Singleton
    public RepoRepository providesGithubRepository(GithubAPI githubAPI) {
        return new RepoRepositoryNetwork(githubAPI);
    }
}
