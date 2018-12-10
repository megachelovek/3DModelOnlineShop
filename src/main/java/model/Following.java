package model;

public class Following {
    private User follow;
    private User idol;

    public Following(User fol, User idol){
        this.follow=fol;
        this.idol=idol;
    }

    public User getFollowing() {
        return follow;
    }

    public User getIdol() {
        return idol;
    }

    public void setFollow(User follow) {
        this.follow = follow;
    }

    public void setIdol(User idol) {
        this.idol = idol;
    }
}
