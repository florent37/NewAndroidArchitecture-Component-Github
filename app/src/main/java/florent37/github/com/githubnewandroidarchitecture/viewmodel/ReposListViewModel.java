package florent37.github.com.githubnewandroidarchitecture.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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

    private MediatorLiveData<List<Repo>> reposLiveData = new MediatorLiveData<>();

    public ReposListViewModel(@NonNull Application application) {
        super(application);
        ((MainApplication) application).getAppComponent().inject(this);
    }

    public void searchRepos(@Nullable final String userName) {
        if (userName != null) {
            final LiveData<List<Repo>> repoLiveData = repoRepository.getRepos(userName);
            reposLiveData.addSource(repoLiveData, new Observer<List<Repo>>() {
                @Override
                public void onChanged(@Nullable List<Repo> repos) {
                    reposLiveData.setValue(repos);
                    reposLiveData.removeSource(repoLiveData);
                }
            });
        }
    }

    public LiveData<List<Repo>> getReposLiveData() {
        return reposLiveData;
    }
}
