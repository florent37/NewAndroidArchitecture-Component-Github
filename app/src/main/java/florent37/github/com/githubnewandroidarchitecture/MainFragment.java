package florent37.github.com.githubnewandroidarchitecture;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import florent37.github.com.githubnewandroidarchitecture.databinding.FragmentMainBinding;
import florent37.github.com.githubnewandroidarchitecture.model.Repo;
import florent37.github.com.githubnewandroidarchitecture.model.UserSearchResult;
import florent37.github.com.githubnewandroidarchitecture.viewmodel.ReposListViewModel;
import florent37.github.com.githubnewandroidarchitecture.viewmodel.UserViewModel;

public class MainFragment extends Fragment {

    UserViewModel userViewModel;
    ReposListViewModel reposListViewModel;

    private FragmentMainBinding viewDataBinding;
    private ReposAdapter reposAdapter;

    public static Fragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //get the databinding from the layout
        this.viewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        return viewDataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        reposListViewModel = ViewModelProviders.of(this).get(ReposListViewModel.class);

        reposAdapter = new ReposAdapter();
        viewDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewDataBinding.recyclerView.setAdapter(reposAdapter);

        //Will change the UI when userLiveData will have
        userViewModel.getUserLiveData().observe(this, new Observer<UserSearchResult>() {
            @Override
            public void onChanged(@Nullable UserSearchResult userSearchResult) {
                if (userSearchResult.success()) {
                    viewDataBinding.setUser(userSearchResult.getUser());
                } else {

                }
            }
        });

        //fetch the repos from the datasource, and update whe UI
        reposListViewModel.getRepos("florent37")
                .observe(this, new Observer<List<Repo>>() {
                    @Override
                    public void onChanged(@Nullable List<Repo> repos) {
                        //when available, send it to the recyclerview
                        reposAdapter.setRepos(repos);
                    }
                });

        viewDataBinding.local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //fetch the user from the datasource
                userViewModel.searchLocally("florent37");
            }
        });
        viewDataBinding.network.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //fetch the user from the datasource
                userViewModel.searchOnline("florent37");
            }
        });
    }
}
