package florent37.github.com.githubnewandroidarchitecture.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
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

    private final MutableLiveData<UserSearchResult> userLiveData = new MutableLiveData<>();

    public LiveData<UserSearchResult> getUserLiveData() {
        return userLiveData;
    }

    public void searchOnline(String userName) {
        //userLiveData will be notified when the user is fetched
        final LiveData<User> userLiveDataNetwork = userRepositoryNetwork.searchUser(userName);
        userLiveDataNetwork.observeForever(new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                UserViewModel.this.userLiveData.postValue(new UserSearchResult(user));
                userLiveDataNetwork.removeObserver(this);
            }
        });
    }

    public void searchLocally(String userName) {
        //userLiveData will be notified when the user is fetched
        final LiveData<User> userLiveDataLocal = userRepositoryLocal.searchUser(userName);
        userLiveDataLocal.observeForever(new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                //success
                UserViewModel.this.userLiveData.postValue(new UserSearchResult(user));
                userLiveDataLocal.removeObserver(this);
            }
        });

    }
}
