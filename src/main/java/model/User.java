package model;

public class User {

    private long id_user;
    private String nik;
    private int folowCount;
    private int modelCount;
    private int raiting;
    private long account;

    public User(long id_user,String nik, int folowCount, int modelCount, int raiting, long account){
        this.account=account;
        this.raiting=raiting;
        this.modelCount=modelCount;
        this.folowCount=folowCount;
        this.id_user=id_user;
        this.nik=nik;
    }
    public String getNik(){
        return nik;
    }
    public void setNik(String n){
        nik=n;
    }
    public long getId(){
        return id_user;
    }
    public void setId(long id){
        id_user=id;
    }
    public int getFolowCount(){
        return folowCount;
    }
    public void setFolowCount(int folow){
        folowCount=folow;
    }
    public int getModelCount(){
        return modelCount;
    }
    public void setModelCount(int mod){
        modelCount=mod;
    }
    public int getRaiting(){
        return raiting;
    }
    public void setRaiting(int r){
        raiting=r;
    }
    public long getAccount(){
        return account;
    }
    public void setAccount(long ac){
        account=ac;
    }
}
