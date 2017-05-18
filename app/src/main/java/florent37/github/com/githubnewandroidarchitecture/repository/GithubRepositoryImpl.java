package florent37.github.com.githubnewandroidarchitecture.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import florent37.github.com.githubnewandroidarchitecture.GithubAPI;
import florent37.github.com.githubnewandroidarchitecture.model.Repo;
import florent37.github.com.githubnewandroidarchitecture.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by florentchampigny on 18/05/2017.
 */

public class GithubRepositoryImpl implements GithubRepository{

    private final GithubAPI githubAPI;

    public GithubRepositoryImpl(GithubAPI githubAPI) {
        this.githubAPI = githubAPI;
    }

    @Override
    public LiveData<User> getUser(String userName){
        final MutableLiveData<User> liveData = new MutableLiveData<>();

        githubAPI.user(userName).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()) {
                    liveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        return liveData;
    }

    @Override
    public LiveData<List<Repo>> getRepos(String userName){
        final MutableLiveData<List<Repo>> liveData = new MutableLiveData<>();

        githubAPI.listRepos(userName).enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                if(response.isSuccessful()){
                    liveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {

            }
        });

        return liveData;
    }
}
