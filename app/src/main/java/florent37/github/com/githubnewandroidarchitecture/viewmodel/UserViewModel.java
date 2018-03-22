package florent37.github.com.githubnewandroidarchitecture.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import florent37.github.com.githubnewandroidarchitecture.MainApplication;
import florent37.github.com.githubnewandroidarchitecture.model.User;
import florent37.github.com.githubnewandroidarchitecture.model.UserSearchResult;
import florent37.github.com.githubnewandroidarchitecture.repository.RepoRepository;
import florent37.github.com.githubnewandroidarchitecture.local.UserRepositoryLocal;
import florent37.github.com.githubnewandroidarchitecture.network.UserRepositoryNetwork;

/**
 * Created by florentchampigny on 18/05/2017.
 */

public class UserViewModel extends AndroidViewModel {

    @Inject
    RepoRepository repoRepository;

    @Inject
    UserRepositoryNetwork userRepositoryNetwork;

    @Inject
    UserRepositoryLocal userRepositoryLocal;

    public UserViewModel(@NonNull Application application) {
        super(application);
        ((MainApplication) application).getAppComponent().inject(this);
    }

    private final MediatorLiveData<UserSearchResult> userLiveData = new MediatorLiveData<>();

    public LiveData<UserSearchResult> getUserLiveData() {
        return userLiveData;
    }

    public void searchOnline(@Nullable final String userName) {
        if (userName != null) {
            //userLiveData will be notified when the user is fetched
            final LiveData<User> userLiveDataNetwork = userRepositoryNetwork.searchUser(userName);
            userLiveData.addSource(userLiveDataNetwork, new Observer<User>() {
                @Override
                public void onChanged(@Nullable User user) {
                    userLiveData.postValue(new UserSearchResult(user));
                    userLiveData.removeSource(userLiveDataNetwork);
                }
            });
        }
    }

    public void searchLocally(@Nullable final String userName) {
        if (userName != null) {
            //userLiveData will be notified when the user is fetched
            final LiveData<User> userLiveDataLocal = userRepositoryLocal.searchUser(userName);
            userLiveData.addSource(userLiveDataLocal, new Observer<User>() {
                @Override
                public void onChanged(@Nullable User user) {
                    userLiveData.postValue(new UserSearchResult(user));
                    userLiveData.removeSource(userLiveDataLocal);
                }
            });
        }
    }
}
