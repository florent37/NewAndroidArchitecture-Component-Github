package florent37.github.com.githubnewandroidarchitecture.repository;

import android.arch.lifecycle.LiveData;

import florent37.github.com.githubnewandroidarchitecture.model.User;

public interface UserRepository {
    LiveData<User> searchUser(String name);
}
