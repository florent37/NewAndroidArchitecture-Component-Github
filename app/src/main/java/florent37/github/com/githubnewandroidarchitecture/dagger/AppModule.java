package florent37.github.com.githubnewandroidarchitecture.dagger;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import florent37.github.com.githubnewandroidarchitecture.GithubAPI;
import florent37.github.com.githubnewandroidarchitecture.repository.GithubRepository;
import florent37.github.com.githubnewandroidarchitecture.repository.GithubRepositoryImpl;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by florentchampigny on 18/05/2017.
 */

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public GithubAPI providesGithubApi(){
        return new Retrofit.Builder()
                .baseUrl(GithubAPI.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubAPI.class);
    }

    @Provides
    @Singleton
    public GithubRepository providesGithubRepository(GithubAPI githubAPI){
        return new GithubRepositoryImpl(githubAPI);
    }
}
