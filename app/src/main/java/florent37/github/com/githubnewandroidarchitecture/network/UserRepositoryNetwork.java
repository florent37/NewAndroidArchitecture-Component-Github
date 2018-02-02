package florent37.github.com.githubnewandroidarchitecture.network;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;

import javax.inject.Inject;

import florent37.github.com.githubnewandroidarchitecture.model.UserNetwork;
import florent37.github.com.githubnewandroidarchitecture.network.GithubAPI;
import florent37.github.com.githubnewandroidarchitecture.model.User;
import florent37.github.com.githubnewandroidarchitecture.repository.UserRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepositoryNetwork implements UserRepository {

    private final GithubAPI githubAPI;

    @Inject
    public UserRepositoryNetwork(GithubAPI githubAPI) {
        this.githubAPI = githubAPI;
    }

    @Override
    public LiveData<User> searchUser(String userName){
        final MutableLiveData<UserNetwork> liveData = new MutableLiveData<>();

        githubAPI.user(userName).enqueue(new Callback<UserNetwork>() {
            @Override
            public void onResponse(Call<UserNetwork> call, Response<UserNetwork> response) {
                if(response.isSuccessful()) {
                    liveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<UserNetwork> call, Throwable t) {

            }
        });

        return Transformations.map(liveData, new Function<UserNetwork, User>() {
            @Override
            public User apply(UserNetwork input) {
                return input.toUser();
            }
        });
    }
}
