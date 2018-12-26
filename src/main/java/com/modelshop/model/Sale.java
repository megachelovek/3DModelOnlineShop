package com.modelshop.model;

public class Sale {
    private User author;
    private User buyer;
    private Model model;
    private int count;

    public Sale(User author, User buyer, Model model, int count){
        this.author=author;
        this.buyer=buyer;
        this.count=count;
        this.model=model;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }
}
