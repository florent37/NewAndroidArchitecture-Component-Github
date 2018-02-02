# NewAndroidArchitecture-Github


<a href="https://goo.gl/WXW8Dc">
  <img alt="Android app on Google Play" src="https://developer.android.com/images/brand/en_app_rgb_wo_45.png" />
</a>


Sample project based on the new Android Component Architecture 

Lifecycle, LiveData, MVVM, ViewModels, DataBinding, Dagger, Retrofit

https://developer.android.com/topic/libraries/architecture/guide.html

![Alt sample](https://raw.githubusercontent.com/florent37/NewAndroidArchitecture-Github/master/media/screen_small.png)

# LiveData as Observables !

LiveDatas works like RxJava's Observables,
they will notify the observer when the data is Available

```java
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
```

# LifeCycle Owner

Use Support Fragment and AppCompatActivity to be attached to the application's state

```java
public class MainFragment extends Fragment {
```

# DataBinding and ViewHolders

<b>MainFragment.java</b>
```java
reposAdapter = new ReposAdapter();
viewDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
viewDataBinding.recyclerView.setAdapter(reposAdapter);

AppComponent.from(getContext()).inject(this);
//inject the viewmodel responding to User
//inject the viewmodel responding to List<Repo>

//fetch the user from the datasource
userViewModel.getUser("florent37")
                .observe(this, new Observer<User>() {
                    @Override
                    public void onChanged(@Nullable User user) {
                        viewDataBinding.setUser(user);
                    }
                });

//fetch the repos from the datasource
reposListViewModel.getRepos("florent37")
                .observe(this, new Observer<List<Repo>>() {
                    @Override
                    public void onChanged(@Nullable List<Repo> repos) {
                        //when available, send it to the recyclerview
                        reposAdapter.setRepos(repos);
                    }
                });
```

# Dagger and Repository

```java
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
```


<a href="https://goo.gl/WXW8Dc">
  <img alt="Android app on Google Play" src="https://developer.android.com/images/brand/en_app_rgb_wo_45.png" />
</a>

