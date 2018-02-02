package florent37.github.com.githubnewandroidarchitecture.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import florent37.github.com.githubnewandroidarchitecture.model.UserLocal;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserLocal user);

    @Query("DELETE FROM user_table")
    void deleteAll();

    @Query("SELECT * from user_table WHERE login LIKE :login")
    LiveData<UserLocal> getUser(String login);
}
