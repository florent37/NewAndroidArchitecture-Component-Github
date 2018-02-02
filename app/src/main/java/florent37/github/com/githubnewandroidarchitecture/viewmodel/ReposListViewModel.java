package florent37.github.com.githubnewandroidarchitecture.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import florent37.github.com.githubnewandroidarchitecture.MainApplication;
import florent37.github.com.githubnewandroidarchitecture.model.Repo;
import florent37.github.com.githubnewandroidarchitecture.repository.RepoRepository;

/**
 * Created by florentchampigny on 18/05/2017.
 */

public class ReposListViewModel extends AndroidViewModel {

    @Inject
    RepoRepository repoRepository;

    public ReposListViewModel(@NonNull Application application) {
        super(application);
        ((MainApplication) application).getAppComponent().inject(this);
    }

    public LiveData<List<Repo>> getRepos(String userName) {
        return repoRepository.getRepos(userName);
    }
}
