package model;

public class Bill {
    private User author;
    private User buyer;
    private Model model;
    private int amount;

    public Bill(){

    }

    public Bill(User author, User buyer, Model model, int amount) {
        this.author = author;
        this.buyer = buyer;
        this.model = model;
        this.amount = amount;
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
}
