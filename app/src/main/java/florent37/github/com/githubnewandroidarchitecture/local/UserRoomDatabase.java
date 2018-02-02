package florent37.github.com.githubnewandroidarchitecture.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import javax.inject.Singleton;

import florent37.github.com.githubnewandroidarchitecture.model.UserLocal;

@Singleton
@Database(entities = {UserLocal.class}, version = 1)
public abstract class UserRoomDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
