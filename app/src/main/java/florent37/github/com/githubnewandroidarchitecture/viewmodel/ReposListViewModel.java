package florent37.github.com.githubnewandroidarchitecture.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import florent37.github.com.githubnewandroidarchitecture.model.Repo;
import florent37.github.com.githubnewandroidarchitecture.repository.GithubRepository;

/**
 * Created by florentchampigny on 18/05/2017.
 */

public class ReposListViewModel extends ViewModel {

    private final GithubRepository githubRepository;

    @Inject
    public ReposListViewModel(GithubRepository githubRepository) {
        this.githubRepository = githubRepository;
    }

    public LiveData<List<Repo>> getRepos(String userName) {
        return githubRepository.getRepos(userName);
    }
}
