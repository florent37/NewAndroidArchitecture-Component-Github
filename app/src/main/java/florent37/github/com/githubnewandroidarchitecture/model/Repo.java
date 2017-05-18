package florent37.github.com.githubnewandroidarchitecture.model;

/**
 * Created by florentchampigny on 03/06/15.
 */
public class Repo implements Comparable<Repo> {
    int id;
    String name;
    String full_name;
    String html_url;
    int forks_count;
    int stargazers_count;
    private int newStarsCount;
    private int newForksCount;

    //getters & setters

    public String getName() {
        return name;
    }

    public int getForks_count() {
        return forks_count;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(int stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public void setForks_count(int forks_count) {
        this.forks_count = forks_count;
    }

    public String getForks() {
        return String.valueOf(forks_count);
    }

    public String getStars() {
        return String.valueOf(stargazers_count);
    }

    public String getNewForks() {
        return String.valueOf(newForksCount);
    }

    public String getNewStars() {
        if (newStarsCount > 0)
            return "+" + String.valueOf(newStarsCount);
        else
            return String.valueOf(newStarsCount);
    }

    @Override
    public String toString() {
        return "Repo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", forks_count=" + forks_count +
                ", stargazers_count=" + stargazers_count +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Repo)) return false;

        Repo repo = (Repo) o;

        return id == repo.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    public void setNewStarsCount(int newStarsCount) {
        this.newStarsCount = newStarsCount;
    }

    public int getNewStarsCount() {
        return newStarsCount;
    }

    public void setNewForksCount(int newForksCount) {
        this.newForksCount = newForksCount;
    }

    public int getNewForksCount() {
        return newForksCount;
    }


    @Override
    public int compareTo(Repo another) {
        if (this.getNewStarsCount() > another.getNewStarsCount())
            return -1;
        else if (this.getNewStarsCount() < another.getNewStarsCount())
            return 1;
        else{
            if(this.getStargazers_count() > another.getStargazers_count())
                return -1;
            else if(this.getStargazers_count() < another.getStargazers_count())
                return 1;
        }
        return 0;
    }
}
