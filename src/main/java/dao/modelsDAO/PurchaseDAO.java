package dao.modelsDAO;

import dao.DAO;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDAO {

    private DAO dao;
    PurchaseDAO(DAO dao){this.dao = dao;}

    private final String PURCHASE = "purchase";
    private final String AUTHOR = "author_id";
    private final String BUYER = "buyer_id";
    private final String MODEL_ID = "model_id";
    private final String AMOUNT = "amount";
    private final String DATE = "date";


    public void addPurchase(Purchase purchase) {
        String query = "INSERT into " + PURCHASE + " values(?,?,?,?,?)";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, purchase.getAuthor().getId());
            preparedStatement.setLong(2, purchase.getBuyer().getId());
            preparedStatement.setLong(3, purchase.getModel().getIdModel());
            preparedStatement.setInt(4, purchase.getAmount());
            preparedStatement.setDate(5, purchase.getDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            dao.sendMessage("Query updating error");
        }
    }


    public List<Purchase> getPurchases() {
        List<Purchase> purchases = new ArrayList<>();
        String query = "SELECT * FROM " + PURCHASE + " ORDER BY " + AUTHOR;
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            extractPurchasesFromQuery(purchases, resultSet);
        } catch (SQLException e) {
            dao.sendMessage("Query executing error");
        }
        return purchases;
    }


    public List<Purchase> getPurchasesByModel(long modelId) {
        List<Purchase> purchases = new ArrayList<>();
        String query = "SELECT * FROM " + PURCHASE + " WHERE " + MODEL_ID + " = ?";
        setParamsForSearch(modelId, purchases, query);
        return purchases;
    }

    public List<Purchase> getPurchasesByAuthor(long authorId) {
        List<Purchase> purchases = new ArrayList<>();
        String query = "SELECT * FROM " + PURCHASE + " WHERE " + AUTHOR + " = ?";
        setParamsForSearch(authorId, purchases, query);
        return purchases;
    }

    public List<Purchase> getPurchasesByBuyer(long buyerId) {
        List<Purchase> purchases = new ArrayList<>();
        String query = "SELECT * FROM " + PURCHASE + " WHERE " + AUTHOR + " = ?";
        setParamsForSearch(buyerId, purchases, query);
        return purchases;
    }

    private void setParamsForSearch(long authorId, List<Purchase> purchases, String query) {
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,authorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            extractPurchasesFromQuery(purchases, resultSet);
        } catch (SQLException e) {
            dao.sendMessage("Query executing error");
        }
    }

    private void extractPurchasesFromQuery(List<Purchase> purchases, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            long modelId = resultSet.getLong(MODEL_ID);
            long authorId = resultSet.getLong(AUTHOR);
            long buyerId = resultSet.getLong(BUYER);
            int amount = resultSet.getInt(AMOUNT);
            Date date = resultSet.getDate(DATE);
            UserDAO userDAO = new UserDAO(dao);
            User author = userDAO.getUser(authorId);
            User buyer = userDAO.getUser(buyerId);
            ModelDAO modelDAO = new ModelDAO(dao);
            Model model = modelDAO.getModel(modelId);
            purchases.add(new Purchase(author,buyer,model,amount, date));
        }
    }
}
