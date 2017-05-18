package florent37.github.com.githubnewandroidarchitecture.model;

import java.util.List;

/**
 * Created by florentchampigny on 31/07/15.
 */
public class User {

    String login;
    String avatar_url;
    String url;
    String name;
    String email;
    int followers;
    int following;

    private List<Repo> repos;

    public User(String name, String email, String avatar_url) {
        this.avatar_url = avatar_url;
        this.name = name;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }

    public String getEmail() {
        return email;
    }

    public void setRepos(List<Repo> repos) {
        this.repos = repos;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", followers=" + followers +
                ", following=" + following +
                '}';
    }
}
