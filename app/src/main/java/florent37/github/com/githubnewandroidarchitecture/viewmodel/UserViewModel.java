package florent37.github.com.githubnewandroidarchitecture.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import florent37.github.com.githubnewandroidarchitecture.model.User;
import florent37.github.com.githubnewandroidarchitecture.repository.GithubRepository;

/**
 * Created by florentchampigny on 18/05/2017.
 */

public class UserViewModel extends ViewModel {

    private final GithubRepository githubRepository;

    @Inject
    public UserViewModel(GithubRepository githubRepository) {
        this.githubRepository = githubRepository;
    }

    public LiveData<User> getUser(String userName) {
        //userLiveData will be notified when the user is fetched
        return githubRepository.getUser(userName);
    }
}
