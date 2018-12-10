package model;

import java.sql.Date;

public class Purchase {
    private User author;
    private User buyer;
    private Model model;
    private int amount;
    private Date date;

    public Purchase(){

    }

    public Purchase(User author, User buyer, Model model, int amount, Date date) {
        this.author = author;
        this.buyer = buyer;
        this.model = model;
        this.amount = amount;
        this.date = date;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
