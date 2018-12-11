package model;

public class User {

    private long userId;
    private String userName;
    private int followersCount;
    private int modelsCount;
    private int rating;
    private long account;

    public User(long id, String nik, int folowCount, int modelCount, int rating, long account){
        this.account = account;
        this.rating = rating;
        this.modelsCount = modelCount;
        this.followersCount = folowCount;
        this.userId = id;
        this.userName = nik;
    }
    public String getUserName(){
        return userName;
    }
    public void setUserName(String n){
        userName =n;
    }
    public long getId(){
        return userId;
    }
    public void setId(long id){
        userId =id;
    }
    public int getFollowersCount(){
        return followersCount;
    }
    public void setFollowersCount(int folow){
        followersCount =folow;
    }
    public int getModelCount(){
        return modelsCount;
    }
    public void setModelCount(int mod){
        modelsCount =mod;
    }
    public int getRating(){
        return rating;
    }
    public void setRating(int r){
        rating =r;
    }
    public long getAccount(){
        return account;
    }
    public void setAccount(long ac){
        account=ac;
    }
}
