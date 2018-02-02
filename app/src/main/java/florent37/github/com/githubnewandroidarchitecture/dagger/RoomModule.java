package florent37.github.com.githubnewandroidarchitecture.dagger;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import florent37.github.com.githubnewandroidarchitecture.local.UserDao;
import florent37.github.com.githubnewandroidarchitecture.local.UserRoomDatabase;
import florent37.github.com.githubnewandroidarchitecture.model.UserLocal;

@Module
public class RoomModule {

    private final UserRoomDatabase database;

    public RoomModule(Application application) {
        database = Room.databaseBuilder(application, UserRoomDatabase.class, "user_database")
                .build();
    }

    @Provides
    @Singleton
    public UserRoomDatabase providesUserRoomDatabase(Context context) {
        return database;
    }

    @Singleton
    @Provides
    public UserDao providesProductDao(UserRoomDatabase userRoomDatabase) {
        return userRoomDatabase.userDao();
    }
}
