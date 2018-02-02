package florent37.github.com.githubnewandroidarchitecture.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "user_table")
public class UserLocal {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "avatar_url")
    private String avatarUrl;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "login")
    private String login;

    public UserLocal(String login, String name, String avatarUrl) {
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public User toUser(){
        final User user = new User();
        user.setName(getName());
        user.setAvatarUrl(getAvatarUrl());
        return user;
    }
}
