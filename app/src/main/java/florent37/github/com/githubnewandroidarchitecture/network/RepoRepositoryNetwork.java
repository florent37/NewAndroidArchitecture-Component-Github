package florent37.github.com.githubnewandroidarchitecture.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import florent37.github.com.githubnewandroidarchitecture.network.GithubAPI;
import florent37.github.com.githubnewandroidarchitecture.model.Repo;
import florent37.github.com.githubnewandroidarchitecture.repository.RepoRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by florentchampigny on 18/05/2017.
 */

public class RepoRepositoryNetwork implements RepoRepository {

    private final GithubAPI githubAPI;

    public RepoRepositoryNetwork(GithubAPI githubAPI) {
        this.githubAPI = githubAPI;
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
