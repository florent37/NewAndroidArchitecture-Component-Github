package florent37.github.com.githubnewandroidarchitecture.local;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;

import javax.inject.Inject;

import florent37.github.com.githubnewandroidarchitecture.model.User;
import florent37.github.com.githubnewandroidarchitecture.model.UserLocal;
import florent37.github.com.githubnewandroidarchitecture.repository.UserRepository;

public class UserRepositoryLocal implements UserRepository {

    private final UserDao userDao;

    @Inject
    public UserRepositoryLocal(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public LiveData<User> searchUser(String userName){
        return Transformations.map(userDao.getUser(userName), new Function<UserLocal, User>() {
            @Override
            public User apply(UserLocal input) {
                if (input != null) {
                    return input.toUser();
                } else {
                    return null;
                }
            }
        });
    }
}
