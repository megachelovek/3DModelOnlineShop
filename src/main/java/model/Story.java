package model;

import java.util.Date;

public class Story {
    private User author;
    private User buyer;
    private Model model;
    private Date date;

    public Story(User author, User buyer, Model model,Date date){
        this.author=author;
        this.buyer=buyer;
        this.date=date;
        this.model=model;
    }
    public Story(Sale sale, Date date){
        this.author=sale.getAuthor();
        this.buyer=sale.getBuyer();
        this.model=sale.getModel();
        this.date=date;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getAuthor() {
        return author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
