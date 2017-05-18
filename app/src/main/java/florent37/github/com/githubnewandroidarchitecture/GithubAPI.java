package florent37.github.com.githubnewandroidarchitecture;

import java.util.List;

import florent37.github.com.githubnewandroidarchitecture.model.Repo;
import florent37.github.com.githubnewandroidarchitecture.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by florentchampigny on 30/07/15.
 */
public interface GithubAPI {

    String URL = "https://api.github.com/";

    @GET("/users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);

    @GET("/users/{user}")
    Call<User> user(@Path("user") String user);

}
